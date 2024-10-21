import java.util.Random;
import java.util.stream.Stream;


public class TestMain {

    public static final int RANDOM_TEST_AMOUNT = 100000;
    public static final Random RANDOM_DIS = new Random();
    public static final char[] TOKEN_SYMBOLS =
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxym0123456789_".toCharArray();
    public static final String[] REAL_OPCODES = new String[] {
        "or", "and", "not", "cmp", "add", "sub",
        "ori", "andi", "noti", "cmpi", "addi", "subi",
        "brz", "bro", "mf", "ld", "st", "halt"
    };
    public static final String[] PSEUDO_OPCODES = new String[] {
        "li", "mov", "nop", "br"
    };
    public static final String[] ALL_OPCODES =
        Stream.of(TestMain.REAL_OPCODES, TestMain.PSEUDO_OPCODES)
        .flatMap(Stream::of)
        .toArray(String[]::new);


    public static String randomString(int length) {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = TestMain.RANDOM_DIS.nextInt(TestMain.TOKEN_SYMBOLS.length);
            string.append(TestMain.TOKEN_SYMBOLS[index]);
        }
        return string.toString();
    }


    public static String randomTokenStream(int length, String delimiter) {
        StringBuilder stream = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stream.append(TestMain.randomString(length));
            if (i < length - 1) {
                stream.append(delimiter);
            }
        }
        return stream.toString();
    }

}
