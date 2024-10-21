import java.util.Arrays;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.Assert;
import assembler.Tokenizer;


public class TestTokenizer {

    @Test
    public void testTokenizeNull() {
        Assert.assertThrows(NullPointerException.class, () -> Tokenizer.tokenize(null));
    }

    @Test
    public void testTokenizeNothing() {
        Assert.assertNull(Tokenizer.tokenize(""));
    }

    @Test
    public void testTokenizeComment() {
        for (String comment : new String[] {"//", "// comment", "////", "  ", " // ", "\t", "\n"}) {
            Assert.assertEquals(Tokenizer.tokenize(comment), null);
        }
    }

    @Test
    public void testTokenizeSingleToken() {
        for (int i = 0; i < TestMain.RANDOM_TEST_AMOUNT; i++) {
            String token = TestMain.randomString(TestMain.RANDOM_DIS.nextInt(100) + 1);
            Assert.assertEquals(Tokenizer.tokenize(token), Arrays.asList(new String[] {token}));
        }
    }

    @Test
    public void testTokenizeMultiTokenWithComma() {
        for (int i = 0; i < TestMain.RANDOM_TEST_AMOUNT; i++) {
            int numTokens = TestMain.RANDOM_DIS.nextInt(9) + 2;
            String tokens = TestMain.randomTokenStream(numTokens, ", ");
            Assert.assertEquals(Tokenizer.tokenize(tokens).size(), numTokens);
        }
    }

    @Test
    public void testTokenizeMultiTokenWithWhitespace() {
        for (int i = 0; i < TestMain.RANDOM_TEST_AMOUNT; i++) {
            int numTokens = TestMain.RANDOM_DIS.nextInt(9) + 2;
            String tokens = TestMain.randomTokenStream(numTokens, " ");
            Assert.assertEquals(Tokenizer.tokenize(tokens).size(), numTokens);
        }
    }

    @Test
    public void testClassifyNull() {
        Assert.assertThrows(NullPointerException.class, () -> Tokenizer.classify(null));
    }

    @Test
    public void testClassifyNothing() {
        Assert.assertThrows(IllegalArgumentException.class,
                            () -> Tokenizer.classify(new ArrayList<>()));
    }

    @Test
    public void testClassifyLabel() {
        for (int i = 0; i < TestMain.RANDOM_TEST_AMOUNT; i++) {
            String token = "a" + TestMain.randomString(TestMain.RANDOM_DIS.nextInt(100) + 1) + ":";
            Assert.assertEquals(Tokenizer.classify(Arrays.asList(new String[] {token})),
                                Tokenizer.LineType.LABEL);
        }

        for (String token : new String[] {"_:", "a:", "A:", "a0:", "A0:", "_0:"}) {
            Assert.assertEquals(Tokenizer.classify(Arrays.asList(new String[] {token})),
                                Tokenizer.LineType.LABEL);
        }
    }

    @Test
    public void testClassifyDirective() {
        for (int i = 0; i < TestMain.RANDOM_TEST_AMOUNT; i++) {
            String token = ".a" + TestMain.randomString(TestMain.RANDOM_DIS.nextInt(100) + 1);
            Assert.assertEquals(Tokenizer.classify(Arrays.asList(new String[] {token})),
                                Tokenizer.LineType.DIRECTIVE);
        }

        for (String token : new String[] {"._", ".a", ".A", ".a0", ".A0", "._0"}) {
            Assert.assertEquals(Tokenizer.classify(Arrays.asList(new String[] {token})),
                                Tokenizer.LineType.DIRECTIVE);
        }
    }

    @Test
    public void testClassifyOther() {
        for (int i = 0; i < TestMain.RANDOM_TEST_AMOUNT; i++) {
            String token = TestMain.randomString(TestMain.RANDOM_DIS.nextInt(100) + 1);
            Assert.assertEquals(Tokenizer.classify(Arrays.asList(new String[] {token})),
                                Tokenizer.LineType.INSTRUCTION);
        }
    }

}
