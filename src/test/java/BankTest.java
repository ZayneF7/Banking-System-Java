import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankTest {
	public static final Integer ACCOUNT_ID = 12345678;
	public static final Integer ACCOUNT_ID_2 = 12345677;
	public static final Double APR = 9.0;
	public static final Double BALANCE = 100.0;
	Bank bank;

	@BeforeEach
	public void setUp() {
		bank = new Bank();
	}

	@Test
	public void bank_is_created_with_zero_accounts() {
		assertTrue(bank.getAccounts().isEmpty());
	}

	@Test
	public void bank_has_one_account_after_one_account_is_added() {
		bank.addAccount("Checking", ACCOUNT_ID, APR, BALANCE);
		assertEquals(1, bank.getAccounts().size());
	}

	@Test
	public void bank_has_two_accounts_after_two_accounts_are_added() {
		bank.addAccount("Checking", ACCOUNT_ID, APR, BALANCE);
		bank.addAccount("Checking", ACCOUNT_ID_2, APR, BALANCE);
		assertEquals(2, bank.getAccounts().size());
	}

	@Test
	public void correct_account_is_retrieved() {
		bank.addAccount("Checking", ACCOUNT_ID, APR, BALANCE);
		assertEquals(ACCOUNT_ID, bank.getAccounts().get(ACCOUNT_ID).getAccountId());
	}

	@Test
	public void correct_account_has_200_dollars_upon_deposit() {
		bank.addAccount("Cd", ACCOUNT_ID, APR, BALANCE);
		bank.depositIntoAccount(ACCOUNT_ID, 100);
		assertEquals(200, bank.getAccounts().get(ACCOUNT_ID).getBalance());
	}

	@Test
	public void account_has_300_dollars_after_two_deposits() {
		bank.addAccount("Cd", ACCOUNT_ID, APR, BALANCE);
		bank.depositIntoAccount(ACCOUNT_ID, 100);
		bank.depositIntoAccount(ACCOUNT_ID, 100);
		assertEquals(300, bank.getAccounts().get(ACCOUNT_ID).getBalance());
	}

	@Test
	public void correct_account_has_80_dollars_upon_withdrawal() {
		bank.addAccount("Cd", ACCOUNT_ID, APR, BALANCE);
		bank.withdrawFromAccount(ACCOUNT_ID, 20);
		assertEquals(80, bank.getAccounts().get(ACCOUNT_ID).getBalance());
	}

	@Test
	public void account_has_60_dollars_after_two_withdrawals() {
		bank.addAccount("Cd", ACCOUNT_ID, APR, BALANCE);
		bank.getAccounts().get(ACCOUNT_ID).withdraw(20);
		bank.getAccounts().get(ACCOUNT_ID).withdraw(20);
		assertEquals(60, bank.getAccounts().get(ACCOUNT_ID).getBalance());
	}
}
