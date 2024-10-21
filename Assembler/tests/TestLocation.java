import java.util.Arrays;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.Assert;
import assembler.Location;
import assembler.Tracer;


public class TestLocation {

    @Test
    public void testNullTokens() {
        Assert.assertThrows(NullPointerException.class, () -> new Location(null, 0));
    }

    @Test
    public void testIncorrectNumberOfTokens() {
        new Location(Arrays.asList(new String[] {}), 0);
        Assert.assertTrue(Tracer.INSTANCE.hasNewErrors());
        Assert.assertFalse(Tracer.INSTANCE.hasNewErrors());

        new Location(Arrays.asList(new String[] {"too", "many", "args"}), 0);
        Assert.assertTrue(Tracer.INSTANCE.hasNewErrors());
        Assert.assertFalse(Tracer.INSTANCE.hasNewErrors());
    }

    @Test
    public void testInvalidNumberFormat() {
        for (String argument : new String[] {"0xSOMETHING_INVALID", "SOMETHING_INVALID"}) {
            new Location(Arrays.asList(new String[] {argument}), 0);
            Assert.assertTrue(Tracer.INSTANCE.hasNewErrors());
            Assert.assertFalse(Tracer.INSTANCE.hasNewErrors());
        }
    }

    @Test
    public void testNegativeProgramCounter() {
        for (int pc = -100; pc < 0; pc++) {
            new Location(Arrays.asList(new String[] {Integer.toString(pc)}), 0);
            Assert.assertTrue(Tracer.INSTANCE.hasNewErrors());
            Assert.assertFalse(Tracer.INSTANCE.hasNewErrors());
        }
    }

    @Test
    public void testInvalidPcChronology() {
        for (int pc = 0; pc < 1000; pc++) {
            new Location(Arrays.asList(new String[] {Integer.toString(pc)}), pc);
            Assert.assertTrue(Tracer.INSTANCE.hasNewErrors());
            Assert.assertFalse(Tracer.INSTANCE.hasNewErrors());

            new Location(Arrays.asList(new String[] {Integer.toString(pc - 1)}), pc);
            Assert.assertTrue(Tracer.INSTANCE.hasNewErrors());
            Assert.assertFalse(Tracer.INSTANCE.hasNewErrors());
        }
    }

    @Test
    public void testValidConstruction() {
        Tracer.INSTANCE.hasNewErrors();
        for (int i = 0; i < TestMain.RANDOM_TEST_AMOUNT; i++) {
            int pc1 = TestMain.RANDOM_DIS.nextInt(10000);
            int pc2 = TestMain.RANDOM_DIS.nextInt(10000);
            int targetPc = Math.max(pc1, pc2);
            int currentPc = Math.min(pc1, pc2);
            if (targetPc == currentPc) {
                targetPc++;
            }

            new Location(Arrays.asList(new String[] {Integer.toString(targetPc)}), currentPc);
            Assert.assertFalse(Tracer.INSTANCE.hasNewErrors());
        }
    }

}
