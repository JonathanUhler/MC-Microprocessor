package assembler;


import java.util.Map;
import java.util.HashMap;


public class Label {

    private static Map<String, Integer> pcValues = new HashMap<>();

    private String name;
    private int pcValue;


    public Label(String name, int pcValue) {
        if (name == null) {
            throw new NullPointerException("name cannot be null");
        }
        if (pcValue < 0) {
            throw new IllegalArgumentException("pcValue cannot be negative");
        }

        this.name = name.replaceAll(":", "");
        this.pcValue = pcValue;

        Label.add(this.name, this.pcValue);
    }


    public static boolean exists(String name) {
        return Label.pcValues.containsKey(name);
    }


    public static int getPcValue(String name) {
        if (!Label.exists(name)) {
            Tracer.INSTANCE.addError("label does not exist");
            return 0;
        }
        return Label.pcValues.get(name);
    }


    public static String getName(int pcValue) {
        for (String name : Label.pcValues.keySet()) {
            if (Label.getPcValue(name) == pcValue) {
                return name;
            }
        }
        return null;
    }


    public static void add(String name, int pcValue) {
        if (Label.exists(name)) {
            Tracer.INSTANCE.addError("label already exists");
            return;
        }
        Label.pcValues.put(name, pcValue);
    }


    public String toAssembledString() {
        return ".label " + this.name + " 0x" + String.format("%1$02X", this.pcValue);
    }


    public String toAssemblyString() {
        return this.name + ":";
    }

}
