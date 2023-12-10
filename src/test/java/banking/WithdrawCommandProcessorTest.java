package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WithdrawCommandProcessorTest {
	Bank bank;
	WithdrawCommandProcessor withdrawCommandProcessor;

	@BeforeEach
	public void setUp() {
		bank = new Bank();
		withdrawCommandProcessor = new WithdrawCommandProcessor(bank);
	}

	@Test
	public void withdraw_12345678_0_does_not_reduce_account_with_100_balance() {
		bank.addAccount("checking", 12345678, 3.0, 0);
		bank.getAccounts().get(12345678).deposit(100);
		withdrawCommandProcessor.withdrawFromAccount("withdraw 12345678 0");
		assertEquals(100, bank.getAccounts().get(12345678).getBalance());
	}

	@Test
	public void withdraw_12345678_0_does_not_reduce_account_with_0_balance() {
		bank.addAccount("checking", 12345678, 3.0, 0);
		withdrawCommandProcessor.withdrawFromAccount("withdraw 12345678 0");
		assertEquals(0, bank.getAccounts().get(12345678).getBalance());
	}

	@Test
	public void withdraw_12345678_10_reduces_account_with_100_balance_to_90() {
		bank.addAccount("checking", 12345678, 3.0, 0);
		bank.getAccounts().get(12345678).deposit(100);
		withdrawCommandProcessor.withdrawFromAccount("withdraw 12345678 10");
		assertEquals(90, bank.getAccounts().get(12345678).getBalance());
	}

	@Test
	public void withdraw_12345678_100_reduces_account_with_100_balance_to_0() {
		bank.addAccount("checking", 12345678, 3.0, 0);
		bank.getAccounts().get(12345678).deposit(100);
		withdrawCommandProcessor.withdrawFromAccount("withdraw 12345678 100");
		assertEquals(0, bank.getAccounts().get(12345678).getBalance());
	}

	@Test
	public void withdraw_12345678_110_reduces_account_with_100_balance_to_0() {
		bank.addAccount("checking", 12345678, 3.0, 0);
		bank.getAccounts().get(12345678).deposit(100);
		withdrawCommandProcessor.withdrawFromAccount("withdraw 12345678 110");
		assertEquals(0, bank.getAccounts().get(12345678).getBalance());
	}

	@Test
	public void withdraw_12345678_110_does_not_reduce_account_with_100_balance_to_neg_10() {
		bank.addAccount("checking", 12345678, 3.0, 0);
		bank.getAccounts().get(12345678).deposit(100);
		withdrawCommandProcessor.withdrawFromAccount("withdraw 12345678 110");
		assertFalse(bank.getAccounts().get(12345678).getBalance() == -10);
	}

	@Test
	public void withdraw_12345678_100_does_not_reduce_account_with_0_balance() {
		bank.addAccount("checking", 12345678, 3.0, 0);
		withdrawCommandProcessor.withdrawFromAccount("withdraw 12345678 100");
		assertEquals(0, bank.getAccounts().get(12345678).getBalance());
	}

	@Test
	public void withdraw_12345678_100_reduces_num_of_months_for_savings_account_by_1() {
		bank.addAccount("savings", 12345678, 3.0, 0);
		bank.getAccounts().get(12345678).increaseNumOfMonths(2);
		withdrawCommandProcessor.withdrawFromAccount("withdraw 12345678 100");
		assertEquals(1, bank.getAccounts().get(12345678).getMonths());
	}

	@Test
	public void withdraw_12345678_100_reduces_num_of_months_for_cd_account_by_12() {
		bank.addAccount("cd", 12345678, 3.0, 100);
		bank.getAccounts().get(12345678).increaseNumOfMonths(13);
		withdrawCommandProcessor.withdrawFromAccount("withdraw 12345678 100");
		assertEquals(1, bank.getAccounts().get(12345678).getMonths());
	}

	@Test
	public void withdraw_12345678_100_does_not_reduce_num_of_months_for_checking_account() {
		bank.addAccount("checking", 12345678, 3.0, 100);
		bank.getAccounts().get(12345678).increaseNumOfMonths(12);
		withdrawCommandProcessor.withdrawFromAccount("withdraw 12345678 100");
		assertEquals(12, bank.getAccounts().get(12345678).getMonths());
	}
}
