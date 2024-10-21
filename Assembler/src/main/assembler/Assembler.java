package assembler;


import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;


@Command(name = "asm")
public class Assembler implements Runnable {

    @Parameters(paramLabel = "file")
    private String inputPath;
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this message and exit.")
    private boolean help;
    @Option(names = {"-o"}, paramLabel = "<file>", description = "Write output to <file>.")
    private String outputPath;


    private int pc;
    private Immediate.Type immediateType;


    private Assembler() {}


    @Override
    public void run() {
        this.pc = 0;
        this.immediateType = Immediate.Type.HEX;

        File inputFile = new File(this.inputPath);
        List<String> assembly = Tokenizer.loadLines(inputFile);
        if (assembly == null) {
            System.err.println("error: cannot load file '" + this.inputPath + "'");
            System.exit(1);
        }

        List<String> assembled = this.assemble(assembly);
        if (Tracer.INSTANCE.getNumErrors() > 0) {
            Tracer.INSTANCE.dumpErrors();
            System.exit(1);
        }

        if (this.outputPath != null) {
            File outputFile = new File(this.outputPath);
            int linesWritten = Tokenizer.saveLines(assembled, outputFile);
            if (linesWritten == -1) {
                System.err.println("error: cannot save file '" + this.outputPath + "'");
                System.exit(1);
            }
        }
        else {
            for (String line : assembled) {
                System.out.println(line);
            }
        }
    }


    private List<String> assemble(List<String> assembly) {
        List<String> assembled = new ArrayList<>();
        this.contextualize(assembly, assembled);

        this.pc = 0x00;
        for (int i = 0; i < assembly.size(); i++) {
            List<String> tokens = Tokenizer.tokenize(assembly.get(i));
            if (tokens == null) {
                continue;
            }
            Tracer.INSTANCE.setLineIndex(i);
            Tracer.INSTANCE.setTokens(tokens);

            Tokenizer.LineType lineType = Tokenizer.classify(tokens);
            switch (lineType) {
            case DIRECTIVE:
                assembled.addAll(this.assembleDirective(tokens));
                break;
            case LABEL:
                continue;
            case INSTRUCTION:
                assembled.addAll(this.assembleInstruction(tokens));
                this.pc++;
                break;
            }
        }

        return assembled;
    }


    private void contextualize(List<String> assembly, List<String> assembled) {
        this.pc = 0x00;
        for (int i = 0; i < assembly.size(); i++) {
            List<String> tokens = Tokenizer.tokenize(assembly.get(i));
            if (tokens == null) {
                continue;
            }
            Tracer.INSTANCE.setLineIndex(i);
            Tracer.INSTANCE.setTokens(tokens);

            Tokenizer.LineType lineType = Tokenizer.classify(tokens);
            switch (lineType) {
            case DIRECTIVE:
                assembled.addAll(this.assembleDirective(tokens));
                break;
            case LABEL:
                assembled.addAll(this.assembleLabel(tokens));
                break;
            case INSTRUCTION:
                this.pc++;
                break;
            }
        }
    }


    private List<String> assembleLabel(List<String> tokens) {
        String name = tokens.get(0);
        Tracer.INSTANCE.incrementToken();
        if (tokens.size() > 1) {
            Tracer.INSTANCE.addError("expected newline following label");
        }

        Label label = new Label(name, this.pc);

        List<String> assembled = new ArrayList<>();
        if (!Tracer.INSTANCE.hasNewErrors()) {
            assembled.add(label.toAssembledString());
        }
        return assembled;
    }


    private List<String> assembleDirective(List<String> tokens) {
        String type = tokens.get(0);

        List<String> assembled = new ArrayList<>();
        switch (type) {
        case ".loc":
            Location loc = new Location(tokens.subList(1, tokens.size()), this.pc);
            if (!Tracer.INSTANCE.hasNewErrors()) {
                this.pc = loc.getTargetPc();
                assembled.add(loc.toAssembledString());
            }
            break;
        case ".pragma":
            Tracer.INSTANCE.incrementToken();
            Immediate imm = new Immediate(tokens.subList(1, tokens.size()));
            if (!Tracer.INSTANCE.hasNewErrors()) {
                assembled.add(imm.toAssembledString());
            }
            break;
        default:
            Tracer.INSTANCE.addError("unexpected directive type: " + type);
            break;
        }
        return assembled;
    }


    private List<String> assembleInstruction(List<String> tokens) {
        List<String> assembled = new ArrayList<>();

        String opcode = tokens.get(0);
        List<String> arguments = tokens.subList(1, tokens.size());
        Instruction instruction = new Instruction(opcode);
        if (Tracer.INSTANCE.hasNewErrors()) {
            return assembled;
        }

        instruction.setArguments(arguments);
        if (Tracer.INSTANCE.hasNewErrors()) {
            return assembled;
        }

        String immediateStr = this.updateImmediateType(instruction);
        if (immediateStr != null) {
            assembled.add(immediateStr);
        }

        String pcStr = "0x" + String.format("%1$02X", this.pc);
        String instructionStr = pcStr + ": " + instruction.toAssembledString();
        if (Tracer.INSTANCE.hasNewErrors()) {
            assembled.clear();
            return assembled;
        }
        assembled.add(instructionStr);
        return assembled;
    }


    private String updateImmediateType(Instruction instruction) {
        if (!instruction.isImmediate()) {
            return null;
        }

        String immediateValueStr = instruction.getImmediateValue();
        Immediate.Type immediateValueType = immediateValueStr.startsWith("0x") ?
            Immediate.Type.HEX :
            Immediate.Type.DEC;
        Immediate.Type defaultType = instruction.getImmediateDefaultType();

        boolean instructionTypeIsDefaultType = immediateValueType == defaultType;
        boolean currentTypeIsDefault = this.immediateType == Immediate.Type.DEFAULT;
        boolean currentTypeIsInstructionType = this.immediateType == immediateValueType;

        boolean noChangeFromDefault =
            instructionTypeIsDefaultType && (currentTypeIsInstructionType || currentTypeIsDefault);
        boolean noChangeFromCurrent =
            !instructionTypeIsDefaultType && currentTypeIsInstructionType;

        if (!noChangeFromDefault && !noChangeFromCurrent) {
            this.immediateType = immediateValueType;
            String immediateTypeStr = this.immediateType.toString().toLowerCase();
            Immediate imm = new Immediate(Arrays.asList(immediateTypeStr));
            return imm.toAssembledString();
        }
        return null;
    }

}
