package assembler;


import java.util.List;


public class Immediate {

    public enum Type {
        HEX,
        DEC,
        DEFAULT
    }


    private Type type;


    public Immediate(List<String> tokens) {
        if (tokens == null) {
            throw new NullPointerException("tokens cannot be null");
        }
        if (tokens.size() != 1) {
            Tracer.INSTANCE.addError("incorrect number of arguments for .imm");
            return;
        }

        try {
            this.type = Type.valueOf(tokens.get(0).toUpperCase());
        }
        catch (IllegalArgumentException e) {
            Tracer.INSTANCE.addError("unexpected .imm type");
            return;
        }
    }


    public Type getType() {
        return this.type;
    }


    public String toAssembledString() {
        return ".pragma imm " + this.type.toString().toLowerCase();
    }


    public String toAssemblyString() {
        return null;
    }

}
