package banking;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DepositValidatorTest {
	public static final String VALID_DEPOSIT_COMMAND = "Deposit 12345678 2500";
	public static final String VALID_DEPOSIT_COMMAND_2 = "Deposit 12345678 100";
	public static final String INVALID_DEPOSIT_COMMAND = "Deposit 12345678 2500 50";
	DepositValidator depositValidator;
	Bank bank;

	@BeforeEach
	public void setUp() {
		bank = new Bank();
		depositValidator = new DepositValidator(bank);
	}

	@Test
	public void validator_yields_array_of_expected_length_from_deposit_command() {
		String[] inputArray = depositValidator.parse(VALID_DEPOSIT_COMMAND);
		assertEquals(3, inputArray.length);
	}

	@Test
	public void validator_determines_that_command_with_3_elements_is_valid_for_deposit_command() {
		String[] inputArray = depositValidator.parse(VALID_DEPOSIT_COMMAND);
		assertEquals(3, inputArray.length);
	}

	@Test
	public void validator_determines_that_command_with_4_elements_is_invalid_for_deposit_command() {
		String[] inputArray = depositValidator.parse(INVALID_DEPOSIT_COMMAND);
		assertFalse(inputArray.length == 3);
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
		bank.addAccount("checking", 12345678, 3, 0);
		assertTrue(depositValidator.accountIdExists(inputArray[1]));
	}

	@Test
	public void validator_determines_that_account_is_checking_type_from_valid_id() {
		bank.addAccount("checking", 12345678, 3, 0);
		assertTrue(depositValidator.isCheckingType("12345678"));
	}

	@Test
	public void validator_determines_that_account_is_not_checking_type_from_valid_id() {
		bank.addAccount("savings", 12345678, 3, 0);
		assertFalse(depositValidator.isCheckingType("12345678"));
	}

	@Test
	public void validator_determines_that_account_is_savings_type_from_valid_id() {
		bank.addAccount("savings", 12345678, 3, 0);
		assertTrue(depositValidator.isSavingsType("12345678"));
	}

	@Test
	public void validator_determines_that_account_is_not_savings_type_from_valid_id() {
		bank.addAccount("cd", 12345678, 3, 0);
		assertFalse(depositValidator.isSavingsType("12345678"));
	}

	@Test
	public void validator_determines_deposit_amount_of_100_is_valid_for_checking_type() {
		String[] inputArray = depositValidator.parse(VALID_DEPOSIT_COMMAND_2);
		assertTrue(depositValidator.validDepositForChecking(inputArray[2]));
	}

	@Test
	public void validator_determines_deposit_amount_of_1000_is_valid_for_checking_type() {
		assertTrue(depositValidator.validDepositForChecking("1000"));
	}

	@Test
	public void validator_determines_deposit_amount_of_0_is_valid_for_checking_type() {
		assertTrue(depositValidator.validDepositForChecking("0"));
	}

	@Test
	public void validator_determines_deposit_amount_of_negative_1_is_invalid_for_checking_type() {
		assertFalse(depositValidator.validDepositForChecking("-1"));
	}

	@Test
	public void validator_determines_deposit_amount_of_1500_is_invalid_for_checking_type() {
		assertFalse(depositValidator.validDepositForChecking("1500"));
	}

	@Test
	public void validator_determines_deposit_amount_of_onethousand_is_invalid_for_checking_type() {
		assertFalse(depositValidator.validDepositForChecking("onethousand"));
	}

	@Test
	public void validator_determines_deposit_amount_of_100_is_valid_for_savings_type() {
		assertTrue(depositValidator.validDepositForSavings("100"));
	}

	@Test
	public void validator_determines_deposit_amount_of_2500_is_valid_for_savings_type() {
		assertTrue(depositValidator.validDepositForSavings("2500"));
	}

	@Test
	public void validator_determines_deposit_amount_of_0_is_valid_for_savings_type() {
		assertTrue(depositValidator.validDepositForSavings("0"));
	}

	@Test
	public void validator_determines_deposit_amount_of_negative_1_is_invalid_for_savings_type() {
		assertFalse(depositValidator.validDepositForSavings("-1"));
	}

	@Test
	public void validator_determines_deposit_amount_of_3000_is_invalid_for_checking_type() {
		assertFalse(depositValidator.validDepositForSavings("3000"));
	}

	@Test
	public void validator_determines_deposit_amount_of_onethousand_is_invalid_for_savings_type() {
		assertFalse(depositValidator.validDepositForSavings("onethousand"));
	}

	@Test
	public void Deposit_12345678_100_is_valid_for_checking_type_with_same_id() {
		bank.addAccount("checking", 12345678, 3, 0);
		assertTrue(depositValidator.commandIsValid("Deposit 12345678 100"));
	}

	@Test
	public void DEPOSIT_12345678_100_is_valid_for_checking_type_with_same_id() {
		bank.addAccount("checking", 12345678, 3, 0);
		assertTrue(depositValidator.commandIsValid("DEPOSIT 12345678 100"));
	}

	@Test
	public void Deposit_12345678_0_is_valid_for_checking_type_with_same_id() {
		bank.addAccount("checking", 12345678, 3, 0);
		assertTrue(depositValidator.commandIsValid("Deposit 12345678 0"));
	}

	@Test
	public void Deposit_12345678_1000_is_valid_for_checking_type_with_same_id() {
		bank.addAccount("checking", 12345678, 3, 0);
		assertTrue(depositValidator.commandIsValid("Deposit 12345678 1000"));
	}

	@Test
	public void Deposit_12345678_100_is_valid_for_savings_type_with_same_id() {
		bank.addAccount("savings", 12345678, 3, 0);
		assertTrue(depositValidator.commandIsValid("Deposit 12345678 100"));
	}

	@Test
	public void Deposit_12345678_2500_is_valid_for_savings_type_with_same_id() {
		bank.addAccount("savings", 12345678, 3, 0);
		assertTrue(depositValidator.commandIsValid("Deposit 12345678 2500"));
	}

	@Test
	public void Deposit_12345678_0_is_valid_for_savings_type_with_same_id() {
		bank.addAccount("savings", 12345678, 3, 0);
		assertTrue(depositValidator.commandIsValid("Deposit 12345678 0"));
	}

	@Test
	public void Deposit_12345678_negative_1_is_invalid_for_checking_type_with_same_id() {
		bank.addAccount("checking", 12345678, 3, 0);
		assertFalse(depositValidator.commandIsValid("Deposit 12345678 -1"));
	}

	@Test
	public void Deposit_12345678_2000_is_invalid_for_checking_type_with_same_id() {
		bank.addAccount("checking", 12345678, 3, 0);
		assertFalse(depositValidator.commandIsValid("Deposit 12345678 2000"));
	}

	@Test
	public void Deposit_12345678_negative_1_is_invalid_for_savings_type_with_same_id() {
		bank.addAccount("savings", 12345678, 3, 0);
		assertFalse(depositValidator.commandIsValid("Deposit 12345678 -1"));
	}

	@Test
	public void Deposit_12345678_3000_is_invalid_for_savings_type_with_same_id() {
		bank.addAccount("savings", 12345678, 3, 0);
		assertFalse(depositValidator.commandIsValid("Deposit 12345678 3000"));
	}

	@Test
	public void Deposit_12345678_100_is_invalid_for_cd_type_with_same_id() {
		bank.addAccount("cd", 12345678, 3, 0);
		assertFalse(depositValidator.commandIsValid("Deposit 12345678 100"));
	}

	@Test
	public void Deposit_12345678_is_invalid() {
		bank.addAccount("savings", 12345678, 3, 0);
		assertFalse(depositValidator.commandIsValid("Deposit 12345678"));
	}

	@Test
	public void Deposit__12345678__100_is_invalid() {
		bank.addAccount("savings", 12345678, 3, 0);
		assertFalse(depositValidator.commandIsValid("Deposit  12345678  100"));
	}

	@Test
	public void Deposit_is_invalid() {
		bank.addAccount("savings", 12345678, 3, 0);
		assertFalse(depositValidator.commandIsValid("Deposit"));
	}

	@Test
	public void Deposit_12345678_100_1900_is_invalid() {
		bank.addAccount("savings", 12345678, 3, 0);
		assertFalse(depositValidator.commandIsValid("Deposit 12345678 100 1900"));
	}

	@Test
	public void Deposit_12345678_100_is_invalid_for_nonexistent_id() {
		bank.addAccount("savings", 11111111, 3, 0);
		assertFalse(depositValidator.commandIsValid("Deposit 12345678 100"));
	}
}
