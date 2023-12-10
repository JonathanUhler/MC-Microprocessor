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
public class TestExprVarDef {

    @Parameters
    public static Collection<Object[]> data() {
		// Define a list of test cases
		List<Object[]> tests = new ArrayList<>();

		// Testing variables names
		// Single character name successes
		char[] validChars = new char[53];
		for (char c = 65; c <= 90; c++) {
			tests.add(new Object[] {true, "bool " + Character.toString(c) + " = true"});
			validChars[(int) c - 65] = c;
		}
		for (char c = 97; c <= 122; c++) {
			tests.add(new Object[] {true, "bool " + Character.toString(c) + " = true"});
			validChars[(int) c - 97 + 26] = c;
		}
		tests.add(new Object[] {true, "bool _ = true"});
		validChars[52] = '_';

		// Multi character name successes
		for (int length = 2; length < 1000; length++) {
			String input = "";
			for (int i = 0; i < length; i++)
				input += Character.toString(validChars[i % validChars.length]);
			tests.add(new Object[] {true, "bool " + input + " = true"});
		}

		// Single character name failures
		for (char c = 0; c <= 64; c++)
			tests.add(new Object[] {false, "bool " + Character.toString(c) + " = true"});
		for (char c = 91; c <= 96; c++) {
			if (c == 95)
				continue; // Skip "_", which is valid for variable names
			tests.add(new Object[] {false, "bool " + Character.toString(c) + " = true"});
		}
		for (char c = 123; c <= 127; c++)
			tests.add(new Object[] {false, "bool " + Character.toString(c) + " = true"});

		// Multi character name failures
		for (char c = 0; c <= 64; c++) {
			if (Character.isWhitespace(c))
				continue;
			tests.add(new Object[] {false, "bool " + Character.toString(c) + "valid_text = true"});
		}
		for (char c = 91; c <= 96; c++) {
			if (c == 95 || Character.isWhitespace(c))
				continue; // Skip "_", which is valid
			tests.add(new Object[] {false, "bool " + Character.toString(c) + "valid_text = true"});
		}
		for (char c = 123; c <= 127; c++) {
			if (Character.isWhitespace(c))
				continue;
			tests.add(new Object[] {false, "bool " + Character.toString(c) + "valid_text = true"});
		}

		// Testing data types
		tests.add(new Object[] {true, "bool x = true"});
		tests.add(new Object[] {true, "bool x = false"});
		tests.add(new Object[] {true, "bool x = a_variable_name"});
		for (int i = -1000; i <= 1000; i++) {
			if (i >= 0)
				tests.add(new Object[] {true, "uint8 x = " + i});
			else
				tests.add(new Object[] {false, "uint8 x = " + i});
			tests.add(new Object[] {false, "bool x = " + i});
		}

		// Return test cases
		return tests;
    }


	private boolean isValid;
	private String input;
	

	public TestExprVarDef(boolean isValid, String input) {
		this.isValid = isValid;
		this.input = input;
	}
	

	@Test
	public void testValid() {
		Assume.assumeTrue(this.isValid);
		
		ConfTest.setUpParser(this.input);
		RuleContext r = ConfTest.parser.exprVarDef();
		Assert.assertEquals("failed on input='" + this.input + "'",
							this.input.replaceAll("[ \t\r\n]+", ""),
							r.getText().replaceAll("[ \t\r\n]+", ""));
	}


	@Test
	public void testInvalid() {
		Assume.assumeFalse(this.isValid);
		
		ConfTest.setUpParser(this.input);
		Assert.assertThrows("failed on input='" + this.input + "'",
							ParseCancellationException.class,
							() -> ConfTest.parser.exprVarDef());
	}

}
