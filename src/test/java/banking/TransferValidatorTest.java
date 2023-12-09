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
}
