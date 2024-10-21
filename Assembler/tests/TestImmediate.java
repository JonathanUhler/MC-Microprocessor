import java.util.Arrays;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.Assert;
import assembler.Immediate;
import assembler.Tracer;


public class TestImmediate {

    @Test
    public void testNullTokens() {
        Assert.assertThrows(NullPointerException.class, () -> new Immediate(null));
    }

    @Test
    public void testIncorrectNumberOfTokens() {
        new Immediate(Arrays.asList(new String[] {}));
        Assert.assertTrue(Tracer.INSTANCE.hasNewErrors());
        Assert.assertFalse(Tracer.INSTANCE.hasNewErrors());

        new Immediate(Arrays.asList(new String[] {"too", "many", "args"}));
        Assert.assertTrue(Tracer.INSTANCE.hasNewErrors());
        Assert.assertFalse(Tracer.INSTANCE.hasNewErrors());
    }

    @Test
    public void testInvalidImmediateType() {
        for (String argument : new String[] {"hexadecimal", "decimal", "def", "abc", "invalid"}) {
            new Immediate(Arrays.asList(new String[] {argument}));
            Assert.assertTrue(Tracer.INSTANCE.hasNewErrors());
            Assert.assertFalse(Tracer.INSTANCE.hasNewErrors());
        }
    }

    @Test
    public void testValidConstruction() {
        for (String argument : new String[] {"hex", "dec", "default",
                                             "HEX", "DEC", "DEFAULT",
                                             "Hex", "Dec", "Default"})
        {
            new Immediate(Arrays.asList(new String[] {argument}));
            Assert.assertFalse(Tracer.INSTANCE.hasNewErrors());
        }
    }

}
