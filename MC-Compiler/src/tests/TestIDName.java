import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.misc.ParseCancellationException;


@RunWith(Parameterized.class)
public class TestIDName {

    @Parameters
    public static Collection<Object[]> data() {
		// Define a list of test cases
		List<Object[]> tests = new ArrayList<>();

		// Single character successes
		char[] validChars = new char[53];
		for (char c = 65; c <= 90; c++) {
			tests.add(new Object[] {true, Character.toString(c)});
			validChars[(int) c - 65] = c;
		}
		for (char c = 97; c <= 122; c++) {
			tests.add(new Object[] {true, Character.toString(c)});
			validChars[(int) c - 97 + 26] = c;
		}
		tests.add(new Object[] {true, "_"});
		validChars[52] = '_';

		// Multi character successes
		for (int length = 2; length < 1000; length++) {
			String input = "";
			for (int i = 0; i < length; i++)
				input += Character.toString(validChars[i % validChars.length]);
			tests.add(new Object[] {true, input});
		}

		// Single character failures
		for (char c = 0; c <= 64; c++)
			tests.add(new Object[] {false, Character.toString(c)});
		for (char c = 91; c <= 96; c++) {
			if (c == 95)
				continue; // Skip "_", which is valid
			tests.add(new Object[] {false, Character.toString(c)});
		}
		for (char c = 123; c <= 127; c++)
			tests.add(new Object[] {false, Character.toString(c)});

		// Multi character failures
		for (char c = 0; c <= 64; c++) {
			if (Character.isWhitespace(c))
				continue;
			tests.add(new Object[] {false, Character.toString(c) + "some_valid_text"});
		}
		for (char c = 91; c <= 96; c++) {
			if (c == 95 || Character.isWhitespace(c))
				continue; // Skip "_", which is valid
			tests.add(new Object[] {false, Character.toString(c) + "some_valid_text"});
		}
		for (char c = 123; c <= 127; c++) {
			if (Character.isWhitespace(c))
				continue;
			tests.add(new Object[] {false, Character.toString(c) + "some_valid_text"});
		}

		// Return test cases
		return tests;
    }


	private boolean isValid;
	private String input;
	

	public TestIDName(boolean isValid, String input) {
		this.isValid = isValid;
		this.input = input;
	}
	

	@Test
	public void testValid() {
		Assume.assumeTrue(this.isValid);
		
		ConfTest.setUpParser(this.input);
		RuleContext r = ConfTest.parser.idName();
		Assert.assertEquals("failed on input='" + this.input + "'",
							r.getText(),
							this.input);
	}


	@Test
	public void testInvalid() {
		Assume.assumeFalse(this.isValid);
		
		ConfTest.setUpParser(this.input);
		Assert.assertThrows("failed on input='" + this.input + "'",
							ParseCancellationException.class,
							() -> ConfTest.parser.idName());
	}

}
