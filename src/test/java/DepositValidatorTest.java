import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DepositValidatorTest {
	public static final String VALID_DEPOSIT_COMMAND = "Deposit 12345678 2500";
	public static final String INVALID_DEPOSIT_COMMAND = "Deposit 12345678 2500 50";
	DepositValidator depositValidator;
	Bank bank;

	@BeforeEach
	public void setUp() {
		depositValidator = new DepositValidator();
		bank = new Bank();
	}

	@Test
	public void validator_yields_array_of_expected_length_from_deposit_command() {
		String[] inputArray = depositValidator.parse(VALID_DEPOSIT_COMMAND);
		assertEquals(3, inputArray.length);
	}

	@Test
	public void validator_determines_that_command_with_3_elements_is_valid_for_deposit_command() {
		String[] inputArray = depositValidator.parse(VALID_DEPOSIT_COMMAND);
		assertTrue(depositValidator.arrayHasThreeElements(inputArray));
	}

	@Test
	public void validator_determines_that_command_with_4_elements_is_invalid_for_deposit_command() {
		String[] inputArray = depositValidator.parse(INVALID_DEPOSIT_COMMAND);
		assertFalse(depositValidator.arrayHasThreeElements(inputArray));
	}

	@Test
	public void validator_converts_input_command_string_to_all_lowercase_for_deposit_command() {
		String[] expectedArray = { "deposit", "12345678", "2500" };
		String[] actualArray = depositValidator.parse(VALID_DEPOSIT_COMMAND);

		for (int i = 0; i < expectedArray.length; i++) {
			assertEquals(expectedArray[i], actualArray[i]);
		}
	}

	@Test
	public void validator_confirms_account_id_12345678_exists_from_deposit_command() {
		String[] inputArray = depositValidator.parse(VALID_DEPOSIT_COMMAND);
		bank.addAccount("Checking", 12345678, 3, 0);
		assertTrue(depositValidator.accountIdExists(inputArray[1], bank));
	}

	@Test
	public void validator_determines_that_account_is_checking_type_from_valid_id() {
		bank.addAccount("Checking", 12345678, 3, 0);
		assertTrue(depositValidator.isCheckingType(12345678, bank));
	}

	@Test
	public void validator_determines_that_account_is_savings_type_from_valid_id() {
		bank.addAccount("Savings", 12345678, 3, 0);
		assertTrue(depositValidator.isSavingsType(12345678, bank));
	}

	@Test
	public void validator_determines_that_account_is_cd_type_from_valid_id() {
		bank.addAccount("Cd", 12345678, 3, 0);
		assertTrue(depositValidator.isCdType(12345678, bank));
	}

}
