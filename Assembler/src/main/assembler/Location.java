package assembler;


import java.util.List;


public class Location {

    private int targetPc;
    

    public Location(List<String> tokens, int currentPc) {
        if (tokens == null) {
            throw new NullPointerException("tokens cannot be null");
        }
        if (currentPc < 0) {
            throw new IllegalArgumentException("currentPc cannot be negative");
        }

        Tracer.INSTANCE.incrementToken();
        if (tokens.size() != 1) {
            Tracer.INSTANCE.addError("incorrect number of arguments to .loc");
            return;
        }

        String targetPcStr = tokens.get(0);
        try {
            this.targetPc = targetPcStr.startsWith("0x") ?
                Integer.parseInt(targetPcStr.substring(2), 16) :
                Integer.parseInt(targetPcStr);
        }
        catch (NumberFormatException e) {
            Tracer.INSTANCE.addError("unexpected .loc argument");
            return;
        }

        if (this.targetPc < 0) {
            Tracer.INSTANCE.addError("illegal .loc argument");
        }
        if (this.targetPc <= currentPc) {
            Tracer.INSTANCE.addError(".loc argument must be after current program counter value");
        }
    }


    public int getTargetPc() {
        return this.targetPc;
    }


    public String toAssembledString() {
        return null;
    }


    public String toAssemblyString() {
        return ".loc 0x" + String.format("%1$02X", this.targetPc);
    }

}
