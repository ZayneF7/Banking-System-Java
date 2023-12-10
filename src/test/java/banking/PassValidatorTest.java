package banking;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassValidatorTest {
	public static final String VALID_PASS_COMMAND_1 = "Pass 1";
	public static final String VALID_PASS_COMMAND_2 = "PASS 1";
	PassValidator passValidator;
	Bank bank;

	@BeforeEach
	public void setUp() {
		passValidator = new PassValidator();
		bank = new Bank();
	}

	@Test
	public void validator_yields_array_of_correct_length_for_valid_pass_command() {
		String[] inputArray = passValidator.parse(VALID_PASS_COMMAND_1);
		assertEquals(2, inputArray.length);
	}

	@Test
	public void validator_converts_input_command_string_to_all_lowercase_for_valid_pass_command() {
		String[] expectedArray = { "pass", "1" };
		String[] inputArray = passValidator.parse(VALID_PASS_COMMAND_2);

		for (int i = 0; i < expectedArray.length; i++) {
			assertEquals(expectedArray[i], expectedArray[i]);
		}
	}

	@Test
	public void validator_accepts_1_as_valid_number_of_months() {
		assertTrue(passValidator.numOfMonthsIsValid("1"));
	}

	@Test
	public void validator_accepts_2_as_valid_number_of_months() {
		assertTrue(passValidator.numOfMonthsIsValid("2"));
	}

	@Test
	public void validator_accepts_59_as_valid_number_of_months() {
		assertTrue(passValidator.numOfMonthsIsValid("59"));
	}

	@Test
	public void validator_accepts_60_as_valid_number_of_months() {
		assertTrue(passValidator.numOfMonthsIsValid("60"));
	}

	@Test
	public void validator_denies_0_as_valid_number_of_months() {
		assertFalse(passValidator.numOfMonthsIsValid("0"));
	}

	@Test
	public void validator_denies_61_as_valid_number_of_months() {
		assertFalse(passValidator.numOfMonthsIsValid("61"));
	}

	@Test
	public void validator_denies_neg1_as_valid_number_of_months() {
		assertFalse(passValidator.numOfMonthsIsValid("-1"));
	}

	@Test
	public void validator_denies_decimal_value_as_valid_number_of_months() {
		assertFalse(passValidator.numOfMonthsIsValid("6.0"));
	}

	@Test
	public void Pass_1_is_a_valid_pass_command() {
		assertTrue(passValidator.commandIsValid(VALID_PASS_COMMAND_1));
	}

	@Test
	public void pASs_1_is_a_valid_pass_command() {
		assertTrue(passValidator.commandIsValid("pASs 1"));
	}

	@Test
	public void PASS_1_is_a_valid_pass_command() {
		assertTrue(passValidator.commandIsValid(VALID_PASS_COMMAND_2));
	}

	@Test
	public void Pass_2_is_a_valid_pass_command() {
		assertTrue(passValidator.commandIsValid("Pass 2"));
	}

	@Test
	public void Pass_59_is_a_valid_pass_command() {
		assertTrue(passValidator.commandIsValid("Pass 59"));
	}

	@Test
	public void Pass_60_is_a_valid_pass_command() {
		assertTrue(passValidator.commandIsValid("Pass 60"));
	}

	@Test
	public void Pass__1_is_an_invalid_pass_command() {
		assertFalse(passValidator.commandIsValid("Pass  1"));
	}

	@Test
	public void _Pass_1_is_an_invalid_pass_command() {
		assertFalse(passValidator.commandIsValid(" Pass 1"));
	}

	@Test
	public void Pass_1_foo_is_an_invalid_pass_command() {
		assertFalse(passValidator.commandIsValid("Pass 1 foo"));
	}

	@Test
	public void _1_is_an_invalid_pass_command() {
		assertFalse(passValidator.commandIsValid("1"));
	}

	@Test
	public void pass_is_an_invalid_pass_command() {
		assertFalse(passValidator.commandIsValid("Pass"));
	}

	@Test
	public void pass1_is_an_invalid_pass_command() {
		assertFalse(passValidator.commandIsValid("pass1"));
	}

	@Test
	public void pass_1_pass_1_is_an_invalid_pass_command() {
		assertFalse(passValidator.commandIsValid("pass 1 pass 1"));
	}

	@Test
	public void Pass_0_is_an_invalid_pass_command() {
		assertFalse(passValidator.commandIsValid("Pass 0"));
	}

	@Test
	public void Pass_61_is_an_invalid_pass_command() {
		assertFalse(passValidator.commandIsValid("Pass 61"));
	}

	@Test
	public void Pass_neg1_is_an_invalid_pass_command() {
		assertFalse(passValidator.commandIsValid("Pass -1"));
	}

	@Test
	public void Pass_600_is_an_invalid_pass_command() {
		assertFalse(passValidator.commandIsValid("Pass 600"));
	}

	@Test
	public void Pass_one_is_an_invalid_pass_command() {
		assertFalse(passValidator.commandIsValid("Pass one"));
	}

	@Test
	public void Pass_1_point_0_is_an_invalid_pass_command() {
		assertFalse(passValidator.commandIsValid("Pass 1.0"));
	}

	@Test
	public void Pass_60_point_0_is_an_invalid_pass_command() {
		assertFalse(passValidator.commandIsValid("Pass 60.0"));
	}

	@Test
	public void Pass_1_point_is_an_invalid_pass_command() {
		assertFalse(passValidator.commandIsValid("Pass 1."));
	}

	@Test
	public void Pass_1_point_60_is_an_invalid_pass_command() {
		assertFalse(passValidator.commandIsValid("Pass 1.60"));
	}
}