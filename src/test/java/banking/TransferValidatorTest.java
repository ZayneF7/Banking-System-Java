package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransferValidatorTest {
	Bank bank;
	TransferValidator transferValidator;

	@BeforeEach
	public void setUp() {
		bank = new Bank();
		transferValidator = new TransferValidator(bank);
	}

	@Test
	public void checking_to_checking_account_is_valid() {
		bank.addAccount("checking", 11111111, 3.0, 0);
		bank.addAccount("checking", 22222222, 3.0, 0);
		assertTrue(transferValidator.toFromAccountsValid("11111111", "22222222"));
	}

	@Test
	public void checking_to_savings_account_is_valid() {
		bank.addAccount("checking", 11111111, 3.0, 0);
		bank.addAccount("savings", 22222222, 3.0, 0);
		assertTrue(transferValidator.toFromAccountsValid("11111111", "22222222"));
	}

	@Test
	public void savings_to_savings_account_is_valid() {
		bank.addAccount("savings", 11111111, 3.0, 0);
		bank.addAccount("savings", 22222222, 3.0, 0);
		assertTrue(transferValidator.toFromAccountsValid("11111111", "22222222"));
	}

	@Test
	public void savings_to_checking_account_is_valid() {
		bank.addAccount("savings", 11111111, 3.0, 0);
		bank.addAccount("checking", 22222222, 3.0, 0);
		assertTrue(transferValidator.toFromAccountsValid("11111111", "22222222"));
	}

	@Test
	public void checking_to_checking_with_same_id_is_invalid() {
		bank.addAccount("checking", 11111111, 3.0, 0);
		assertFalse(transferValidator.toFromAccountsValid("11111111", "11111111"));
	}

	@Test
	public void savings_to_savings_with_same_id_is_invalid() {
		bank.addAccount("savings", 11111111, 3.0, 0);
		assertFalse(transferValidator.toFromAccountsValid("11111111", "11111111"));
	}

	@Test
	public void checking_to_nonexistent_account_is_invalid() {
		bank.addAccount("checking", 11111111, 3.0, 0);
		assertFalse(transferValidator.toFromAccountsValid("11111111", "22222222"));
	}

	@Test
	public void savings_to_nonexistent_account_is_invalid() {
		bank.addAccount("savings", 11111111, 3.0, 0);
		assertFalse(transferValidator.toFromAccountsValid("11111111", "22222222"));
	}

	@Test
	public void nonexistent_account_to_checking_is_invalid() {
		bank.addAccount("checking", 22222222, 3.0, 0);
		assertFalse(transferValidator.toFromAccountsValid("11111111", "22222222"));
	}

	@Test
	public void nonexistent_account_to_savings_is_invalid() {
		bank.addAccount("savings", 22222222, 3.0, 0);
		assertFalse(transferValidator.toFromAccountsValid("11111111", "22222222"));
	}

	@Test
	public void nonexistent_account_to_nonexistent_account_is_invalid() {
		assertFalse(transferValidator.toFromAccountsValid("11111111", "22222222"));
	}

	@Test
	public void checking_to_cd_account_is_invalid() {
		bank.addAccount("checking", 11111111, 3.0, 0);
		bank.addAccount("cd", 22222222, 3.0, 100);
		assertFalse(transferValidator.toFromAccountsValid("11111111", "22222222"));
	}

	@Test
	public void savings_to_cd_account_is_invalid() {
		bank.addAccount("savings", 11111111, 3.0, 0);
		bank.addAccount("cd", 22222222, 3.0, 100);
		assertFalse(transferValidator.toFromAccountsValid("11111111", "22222222"));
	}

	@Test
	public void cd_to_checking_account_is_invalid() {
		bank.addAccount("cd", 11111111, 3.0, 100);
		bank.addAccount("checking", 22222222, 3.0, 0);
		assertFalse(transferValidator.toFromAccountsValid("11111111", "22222222"));
	}

	@Test
	public void cd_to_savings_account_is_invalid() {
		bank.addAccount("cd", 11111111, 3.0, 100);
		bank.addAccount("savings", 22222222, 3.0, 0);
		assertFalse(transferValidator.toFromAccountsValid("11111111", "22222222"));
	}

	@Test
	public void cd_to_cd_account_is_invalid() {
		bank.addAccount("cd", 11111111, 3.0, 100);
		bank.addAccount("cd", 22222222, 3.0, 100);
		assertFalse(transferValidator.toFromAccountsValid("11111111", "22222222"));
	}

	@Test
	public void Transfer_11111111_22222222_0_is_valid_for_checking_to_checking() {
		bank.addAccount("checking", 11111111, 3.0, 0);
		bank.addAccount("checking", 22222222, 3.0, 0);
		assertTrue(transferValidator.commandIsValid("Transfer 11111111 22222222 0"));
	}

	@Test
	public void TRANSFER_11111111_22222222_0_is_valid_for_checking_to_checking() {
		bank.addAccount("checking", 11111111, 3.0, 0);
		bank.addAccount("checking", 22222222, 3.0, 0);
		assertTrue(transferValidator.commandIsValid("TRANSFER 11111111 22222222 0"));
	}

	@Test
	public void transfer_11111111_22222222_0_is_valid_for_checking_to_checking() {
		bank.addAccount("checking", 11111111, 3.0, 0);
		bank.addAccount("checking", 22222222, 3.0, 0);
		assertTrue(transferValidator.commandIsValid("transfer 11111111 22222222 0"));
	}

	@Test
	public void tRANSfEr_11111111_22222222_0_is_valid_for_checking_to_checking() {
		bank.addAccount("checking", 11111111, 3.0, 0);
		bank.addAccount("checking", 22222222, 3.0, 0);
		assertTrue(transferValidator.commandIsValid("tRANSfEr 11111111 22222222 0"));
	}

	@Test
	public void Transfer_11111111_22222222_0___is_valid_for_checking_to_checking() {
		bank.addAccount("checking", 11111111, 3.0, 0);
		bank.addAccount("checking", 22222222, 3.0, 0);
		assertTrue(transferValidator.commandIsValid("Transfer 11111111 22222222 0  "));
	}

	@Test
	public void Transfer_11111111_22222222_0_is_valid_for_checking_to_savings() {
		bank.addAccount("checking", 11111111, 3.0, 0);
		bank.addAccount("savings", 22222222, 3.0, 0);
		assertTrue(transferValidator.commandIsValid("Transfer 11111111 22222222 0"));
	}

	@Test
	public void Transfer_11111111_22222222_0_is_valid_for_savings_with_1_month_to_savings() {
		bank.addAccount("savings", 11111111, 3.0, 0);
		bank.getAccounts().get(11111111).increaseNumOfMonths(1);
		bank.addAccount("savings", 22222222, 3.0, 0);
		assertTrue(transferValidator.commandIsValid("Transfer 11111111 22222222 0"));
	}

	@Test
	public void Transfer_11111111_22222222_0_is_valid_for_savings_with_1_month_to_checking() {
		bank.addAccount("savings", 11111111, 3.0, 0);
		bank.getAccounts().get(11111111).increaseNumOfMonths(1);
		bank.addAccount("checking", 22222222, 3.0, 0);
		assertTrue(transferValidator.commandIsValid("Transfer 11111111 22222222 0"));
	}

	@Test
	public void Transfer_11111111_22222222_100_point_5_is_valid_for_checking_to_checking() {
		bank.addAccount("checking", 11111111, 3.0, 0);
		bank.addAccount("checking", 22222222, 3.0, 0);
		assertTrue(transferValidator.commandIsValid("Transfer 11111111 22222222 100.5"));
	}

	@Test
	public void Transfer_11111111_22222222_100_point_5_is_valid_for_checking_to_savings() {
		bank.addAccount("checking", 11111111, 3.0, 0);
		bank.addAccount("savings", 22222222, 3.0, 0);
		assertTrue(transferValidator.commandIsValid("Transfer 11111111 22222222 100.5"));
	}

	@Test
	public void Transfer_11111111_22222222_100_point_5_is_valid_for_savings_with_1_month_to_savings() {
		bank.addAccount("savings", 11111111, 3.0, 0);
		bank.getAccounts().get(11111111).increaseNumOfMonths(1);
		bank.addAccount("savings", 22222222, 3.0, 0);
		assertTrue(transferValidator.commandIsValid("Transfer 11111111 22222222 100.5"));
	}

	@Test
	public void Transfer_11111111_22222222_100_point_5_is_valid_for_savings_with_1_month_to_checking() {
		bank.addAccount("savings", 11111111, 3.0, 0);
		bank.getAccounts().get(11111111).increaseNumOfMonths(1);
		bank.addAccount("checking", 22222222, 3.0, 0);
		assertTrue(transferValidator.commandIsValid("Transfer 11111111 22222222 100.5"));
	}

	@Test
	public void Transfer_11111111_22222222_400_is_valid_for_checking_to_checking() {
		bank.addAccount("checking", 11111111, 3.0, 0);
		bank.addAccount("checking", 22222222, 3.0, 0);
		assertTrue(transferValidator.commandIsValid("Transfer 11111111 22222222 400"));
	}

	@Test
	public void Transfer_11111111_22222222_400_is_valid_for_checking_to_savings() {
		bank.addAccount("checking", 11111111, 3.0, 0);
		bank.addAccount("savings", 22222222, 3.0, 0);
		assertTrue(transferValidator.commandIsValid("Transfer 11111111 22222222 400"));
	}

	@Test
	public void Transfer_11111111_22222222_1000_is_valid_for_savings_with_1_month_to_savings() {
		bank.addAccount("savings", 11111111, 3.0, 0);
		bank.getAccounts().get(11111111).increaseNumOfMonths(1);
		bank.addAccount("savings", 22222222, 3.0, 0);
		assertTrue(transferValidator.commandIsValid("Transfer 11111111 22222222 1000"));
	}

	@Test
	public void Transfer_11111111_22222222_1000_is_valid_for_savings_with_1_month_to_checking() {
		bank.addAccount("savings", 11111111, 3.0, 0);
		bank.getAccounts().get(11111111).increaseNumOfMonths(1);
		bank.addAccount("checking", 22222222, 3.0, 0);
		assertTrue(transferValidator.commandIsValid("Transfer 11111111 22222222 1000"));
	}

	@Test
	public void Transfer_is_invalid() {
		assertFalse(transferValidator.commandIsValid("Transfer"));
	}

	@Test
	public void Transfer_11111111_100_is_invalid() {
		bank.addAccount("checking", 11111111, 3.0, 0);
		bank.addAccount("checking", 22222222, 3.0, 0);
		assertFalse(transferValidator.commandIsValid("Transfer 11111111 100"));
	}

	@Test
	public void Transfer_22222222_100_is_invalid() {
		bank.addAccount("checking", 11111111, 3.0, 0);
		bank.addAccount("checking", 22222222, 3.0, 0);
		assertFalse(transferValidator.commandIsValid("Transfer 22222222 100"));
	}

	@Test
	public void Transfer__11111111__22222222__100_invalid() {
		bank.addAccount("checking", 11111111, 3.0, 0);
		bank.addAccount("checking", 22222222, 3.0, 0);
		assertFalse(transferValidator.commandIsValid("Transfer  11111111  22222222  100"));
	}

	@Test
	public void __Transfer_11111111_22222222_100_invalid() {
		bank.addAccount("checking", 11111111, 3.0, 0);
		bank.addAccount("checking", 22222222, 3.0, 0);
		assertFalse(transferValidator.commandIsValid("  Transfer 11111111 22222222 100"));
	}

	@Test
	public void Transfer_11111111_22222222_500_is_invalid_for_from_a_checking_account() {
		bank.addAccount("checking", 11111111, 3.0, 0);
		bank.addAccount("checking", 22222222, 3.0, 0);
		assertFalse(transferValidator.commandIsValid("Transfer 11111111 22222222 500"));
	}

	@Test
	public void Transfer_11111111_22222222_100_is_invalid_for_from_a_savings_account_with_0_months() {
		bank.addAccount("savings", 11111111, 3.0, 0);
		bank.addAccount("checking", 22222222, 3.0, 0);
		assertFalse(transferValidator.commandIsValid("Transfer 11111111 22222222 100"));
	}

	@Test
	public void Transfer_11111111_22222222_1100_is_invalid_for_savings_from_account_with_1_month() {
		bank.addAccount("savings", 11111111, 3.0, 0);
		bank.getAccounts().get(11111111).increaseNumOfMonths(1);
		bank.addAccount("checking", 22222222, 3.0, 0);
		assertFalse(transferValidator.commandIsValid("Transfer 11111111 22222222 1100"));
	}

	@Test
	public void Transfer_11111111_22222222_100_is_invalid_for_nonexistent_to_account() {
		bank.addAccount("checking", 11111111, 3.0, 0);
		assertFalse(transferValidator.commandIsValid("Transfer 11111111 22222222 100"));
	}

	@Test
	public void Transfer_11111111_22222222_100_is_invalid_for_nonexistent_from_account() {
		bank.addAccount("checking", 22222222, 3.0, 0);
		assertFalse(transferValidator.commandIsValid("Transfer 11111111 22222222 100"));
	}

	@Test
	public void Transfer_11111111_22222222_100_is_invalid_for_nonexistent_accounts() {
		assertFalse(transferValidator.commandIsValid("Transfer 11111111 22222222 100"));
	}

	@Test
	public void Transfer_11111111_11111111_100_is_invalid() {
		bank.addAccount("checking", 11111111, 3.0, 0);
		assertFalse(transferValidator.commandIsValid("Transfer 11111111 22222222 100"));
	}

	@Test
	public void Transfer_11111111_22222222_neg_1_is_invalid() {
		bank.addAccount("checking", 11111111, 3.0, 0);
		bank.addAccount("checking", 22222222, 3.0, 0);
		assertFalse(transferValidator.commandIsValid("Transfer 11111111 22222222 -1"));
	}

	@Test
	public void foobar_11111111_22222222_100_is_invalid() {
		bank.addAccount("checking", 11111111, 3.0, 0);
		bank.addAccount("checking", 22222222, 3.0, 0);
		assertFalse(transferValidator.commandIsValid("foobar 11111111 22222222 100"));
	}

	@Test
	public void Transfer_11111111_22222222_100_is_invalid_for_cd_from_account() {
		bank.addAccount("cd", 11111111, 3.0, 100);
		bank.addAccount("checking", 22222222, 3.0, 0);
		assertFalse(transferValidator.commandIsValid("Transfer 11111111 22222222 100"));
	}

	@Test
	public void Transfer_11111111_22222222_100_is_invalid_for_cd_to_account() {
		bank.addAccount("checking", 11111111, 3.0, 0);
		bank.addAccount("cd", 22222222, 3.0, 100);
		assertFalse(transferValidator.commandIsValid("Transfer 11111111 22222222 100"));
	}

	@Test
	public void Transfer_11111111_22222222_100_is_invalid_for_cd_to_and_from_accounts() {
		bank.addAccount("cd", 11111111, 3.0, 100);
		bank.addAccount("cd", 22222222, 3.0, 100);
		assertFalse(transferValidator.commandIsValid("Transfer 11111111 22222222 100"));
	}

}
