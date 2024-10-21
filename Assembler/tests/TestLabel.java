import java.util.Arrays;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.Assert;
import assembler.Label;
import assembler.Tracer;


public class TestLabel {

    @Test
    public void testNullName() {
        Assert.assertThrows(NullPointerException.class, () -> new Label(null, 0));
    }

    @Test
    public void testDuplicateName() {
        String label = TestMain.randomString(TestMain.RANDOM_DIS.nextInt(1000) + 1);
        int pcValue = TestMain.RANDOM_DIS.nextInt(10000);

        new Label(label, pcValue);
        Assert.assertFalse(Tracer.INSTANCE.hasNewErrors());

        new Label(label, pcValue);
        Assert.assertTrue(Tracer.INSTANCE.hasNewErrors());
        Assert.assertFalse(Tracer.INSTANCE.hasNewErrors());
    }

    @Test
    public void testLabelAliasing() {
        for (int i = 0; i < TestMain.RANDOM_TEST_AMOUNT / 10; i++) {
            String label = TestMain.randomString(TestMain.RANDOM_DIS.nextInt(1000) + 1);
            int pcValue = TestMain.RANDOM_DIS.nextInt(10000);
            if (Label.exists(label)) {
                continue;
            }

            new Label(label, pcValue);
            Assert.assertTrue(Label.exists(label));
            Assert.assertEquals(Label.getPcValue(label), pcValue);
            Assert.assertEquals(Label.getPcValue(Label.getName(pcValue)), pcValue);
        }
    }

}
