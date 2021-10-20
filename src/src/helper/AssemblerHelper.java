package helper;


public class AssemblerHelper {

    public static void asmAssert(boolean assertion, String failureMessage, String... extraArgs) throws Exception {
        if (!assertion) {
            String err = "ERROR: MCasm Assertion Failed; " + failureMessage + ((extraArgs.length > 0) ? " -\n\t" + String.join("\n\t", extraArgs) : "");

            System.out.println(err);
            throw new Exception("MCasm Assertion Failed: " + failureMessage);
        }
    }

}
