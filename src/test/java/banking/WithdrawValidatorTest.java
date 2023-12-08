package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WithdrawValidatorTest {
	public static final String VALID_WITHDRAW_COMMAND_1 = "Withdraw 12345678 10";
	public static final String VALID_WITHDRAW_COMMAND_2 = "WITHDRAW 12345678 10";
	Bank bank;
	WithdrawValidator withdrawValidator;

	@BeforeEach
	public void setUp() {
		bank = new Bank();
		withdrawValidator = new WithdrawValidator(bank);
	}

	@Test
	public void validator_yields_array_of_correct_length_for_valid_withdraw_command() {
		String[] inputArray = withdrawValidator.parse(VALID_WITHDRAW_COMMAND_1);
		assertEquals(3, inputArray.length);
	}

	@Test
	public void validator_converts_input_command_string_to_all_lowercase_for_valid_withdraw_command() {
		String[] expectedArray = { "withdraw", "12345678", "10" };
		String[] inputArray = withdrawValidator.parse(VALID_WITHDRAW_COMMAND_2);

		for (int i = 0; i < expectedArray.length; i++) {
			assertEquals(expectedArray[i], inputArray[i]);
		}
	}

}
