package assembler;


import java.util.Map;
import java.util.HashMap;


public class Register {

    public enum Type {
        WRITE(16),
        READ_A(12),
        READ_B(8),
        READ_IMM(0),
        LABEL(0);

        public final int lsb;

        private Type(int lsb) {
            this.lsb = lsb;
        }
    }


    private static Map<String, Integer> names = new HashMap<>() {{
            put("r0", 0);
            put("r1", 1);
            put("r2", 2);
            put("r3", 3);
            put("r4", 4);
            put("r5", 5);
            put("r6", 6);
            put("r7", 7);
            put("r8", 8);
            put("r9", 9);
            put("r10", 10);
            put("r11", 11);
            put("r12", 12);
            put("r13", 13);
            put("r14", 14);
            put("r15", 15);
        }};


    private Type type;
    private String value;


    public Register(Type type) {
        this.type = type;
        this.value = null;
    }


    public static boolean exists(String name) {
        return Register.names.containsKey(name);
    }


    public static int getAddress(String name) {
        if (!Register.exists(name)) {
            Tracer.INSTANCE.addError("register does not exist");
            return 0;
        }
        return Register.names.get(name);
    }


    public static String getName(int address) {
        for (String name : Register.names.keySet()) {
            if (Register.getAddress(name) == address) {
                return name;
            }
        }
        return null;
    }


    public Type getType() {
        return this.type;
    }


    public String toAssemblyString() {
        return this.value;
    }


    public int toAssembledString() {
        switch (this.type) {
        case WRITE:
        case READ_A:
        case READ_B:
            return Register.getAddress(value);
        case READ_IMM:
            if (this.value.matches("[0-9]+")) {
                return Integer.parseInt(this.value);
            }
            else if (this.value.matches("0x[0-9a-fA-F]+")) {
                return Integer.parseInt(this.value.substring(2), 16);
            }
            else {
                Tracer.INSTANCE.addError("invalid immediate value");
                return 0;
            }
        case LABEL:
            return Label.getPcValue(value);
        default:
            throw new IllegalStateException("unknown register type: " + this.type);
        }
    }


    public void setValue(String value) {
        this.value = value;
    }

}
