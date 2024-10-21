import java.util.Arrays;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.Assert;
import assembler.Instruction;
import assembler.Register;
import assembler.Label;
import assembler.Tracer;


public class TestInstruction {

    @Test
    public void testNullOpcode() {
        Assert.assertThrows(NullPointerException.class, () -> new Instruction(null));
    }

    @Test
    public void testInvalidOpcode() {
        new Instruction("invalid opcode");
        Assert.assertTrue(Tracer.INSTANCE.hasNewErrors());
        Assert.assertFalse(Tracer.INSTANCE.hasNewErrors());
    }

    @Test
    public void testCaseSensitivity() {
        for (String opcode : TestMain.ALL_OPCODES) {
            new Instruction(opcode.toUpperCase());
            Assert.assertTrue(Tracer.INSTANCE.hasNewErrors());
            Assert.assertFalse(Tracer.INSTANCE.hasNewErrors());

            new Instruction(opcode);
            Assert.assertFalse(Tracer.INSTANCE.hasNewErrors());
        }
    }

    @Test
    public void testRegisterExists() {
        for (int i = 0; i < 16; i++) {
            Assert.assertTrue(Register.exists("r" + i));
        }
    }

    @Test
    public void testRegisterGetAddress() {
        for (int i = 0; i < 16; i++) {
            Assert.assertEquals(Register.getAddress("r" + i), i);
            Assert.assertFalse(Tracer.INSTANCE.hasNewErrors());
        }

        Register.getAddress("invalid register name");
        Assert.assertTrue(Tracer.INSTANCE.hasNewErrors());
        Assert.assertFalse(Tracer.INSTANCE.hasNewErrors());
    }

    @Test
    public void testRegisterGetName() {
        for (int i = 0; i < 16; i++) {
            Assert.assertEquals(Register.getName(i), "r" + i);
            Assert.assertFalse(Tracer.INSTANCE.hasNewErrors());
        }
    }

    @Test
    public void testRegisterRegToAssembledString() {
        Register w = new Register(Register.Type.WRITE);
        Register ra = new Register(Register.Type.READ_A);
        Register rb = new Register(Register.Type.READ_B);

        Label.add("my_label", 0xff);
        Tracer.INSTANCE.hasNewErrors();

        for (int i = 0; i < 16; i++) {
            String name = "r" + i;
            for (Register register : new Register[] {w, ra, rb}) {
                register.setValue(name);
                Assert.assertEquals(register.toAssembledString(), i);
                Assert.assertFalse(Tracer.INSTANCE.hasNewErrors());

                register.setValue("my_label");
                register.toAssembledString();
                Assert.assertTrue(Tracer.INSTANCE.hasNewErrors());
                Assert.assertFalse(Tracer.INSTANCE.hasNewErrors());

                register.setValue("0");
                register.toAssembledString();
                Assert.assertTrue(Tracer.INSTANCE.hasNewErrors());
                Assert.assertFalse(Tracer.INSTANCE.hasNewErrors());

                register.setValue("0x00");
                register.toAssembledString();
                Assert.assertTrue(Tracer.INSTANCE.hasNewErrors());
                Assert.assertFalse(Tracer.INSTANCE.hasNewErrors());
            }
        }
    }

    @Test
    public void testRegisterImmToAssemblyString() {
        Register register = new Register(Register.Type.READ_IMM);

        Label.add("my_label", 0xff);
        Tracer.INSTANCE.hasNewErrors();

        register.setValue("0");
        Assert.assertEquals(register.toAssembledString(), 0);
        Assert.assertFalse(Tracer.INSTANCE.hasNewErrors());

        register.setValue("0x00");
        Assert.assertEquals(register.toAssembledString(), 0x00);
        Assert.assertFalse(Tracer.INSTANCE.hasNewErrors());

        register.setValue("my_label");
        register.toAssembledString();
        Assert.assertTrue(Tracer.INSTANCE.hasNewErrors());
        Assert.assertFalse(Tracer.INSTANCE.hasNewErrors());

        register.setValue("r0");
        register.toAssembledString();
        Assert.assertTrue(Tracer.INSTANCE.hasNewErrors());
        Assert.assertFalse(Tracer.INSTANCE.hasNewErrors());
    }

    @Test
    public void testRegisterLabelToAssemblyString() {
        Register register = new Register(Register.Type.LABEL);

        Label.add("my_label", 0xff);
        Tracer.INSTANCE.hasNewErrors();

        register.setValue("my_label");
        Assert.assertEquals(register.toAssembledString(), 0xff);
        Assert.assertFalse(Tracer.INSTANCE.hasNewErrors());

        register.setValue("r0");
        register.toAssembledString();
        Assert.assertTrue(Tracer.INSTANCE.hasNewErrors());
        Assert.assertFalse(Tracer.INSTANCE.hasNewErrors());

        register.setValue("0");
        register.toAssembledString();
        Assert.assertTrue(Tracer.INSTANCE.hasNewErrors());
        Assert.assertFalse(Tracer.INSTANCE.hasNewErrors());

        register.setValue("0x00");
        register.toAssembledString();
        Assert.assertTrue(Tracer.INSTANCE.hasNewErrors());
        Assert.assertFalse(Tracer.INSTANCE.hasNewErrors());
    }

    @Test
    public void testSetNullArguments() {
        Instruction instruction = new Instruction("nop");
        Assert.assertThrows(NullPointerException.class, () -> instruction.setArguments(null));
    }

}
