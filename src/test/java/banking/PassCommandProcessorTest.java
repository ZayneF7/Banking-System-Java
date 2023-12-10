package banking;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassCommandProcessorTest {
	Bank bank;
	Map<Integer, Account> accounts;
	PassCommandProcessor passCommandProcessor;

	@BeforeEach
	public void setUp() {
		bank = new Bank();
		bank.addAccount("checking", 11111111, 3.0, 0);
		bank.addAccount("savings", 22222222, 3.0, 0);
		bank.addAccount("cd", 33333333, 3.0, 100);
		accounts = bank.getAccounts();
		passCommandProcessor = new PassCommandProcessor(bank);
	}

	@Test
	public void Pass_1_adds_1_month_to_each_account() {
		bank.getAccounts().get(11111111).deposit(100);
		bank.getAccounts().get(22222222).deposit(100);
		passCommandProcessor.passTime("Pass 1");
		for (Map.Entry<Integer, Account> account : accounts.entrySet()) {
			int id = account.getKey();
			assertEquals(1, bank.getAccounts().get(id).getMonths());
		}
	}

	@Test
	public void Pass_60_adds_60_months_to_each_account() {
		bank.getAccounts().get(11111111).deposit(100);
		bank.getAccounts().get(22222222).deposit(100);
		passCommandProcessor.passTime("Pass 60");
		for (Map.Entry<Integer, Account> account : accounts.entrySet()) {
			int id = account.getKey();
			assertEquals(60, bank.getAccounts().get(id).getMonths());
		}
	}

	@Test
	public void Pass_1_removes_accounts_with_0_balance() {
		passCommandProcessor.passTime("Pass 1");
		assertEquals(1, bank.getAccounts().size());
	}

	@Test
	public void Pass_1_removes_all_accounts_with_0_balance() {
		bank.getAccounts().get(33333333).withdraw(100);
		passCommandProcessor.passTime("Pass 1");
		assertEquals(0, bank.getAccounts().size());
	}

	@Test
	public void Pass_1_reduces_account_with_50_balance_to_25_point_0625() {
		bank.getAccounts().get(11111111).deposit(50);
		passCommandProcessor.passTime("Pass 1");
		assertEquals(25.0625, bank.getAccounts().get(11111111).getBalance());
	}

	@Test
	public void Pass_2_reduces_account_with_50_balance() {
		bank.getAccounts().get(11111111).deposit(50);
		passCommandProcessor.passTime("Pass 2");
		assertTrue(bank.getAccounts().get(11111111).getBalance() < 50);
	}

	@Test
	public void Pass_2_does_not_reduce_balance_of_account_with_100_balance() {
		bank.getAccounts().get(11111111).deposit(100);
		passCommandProcessor.passTime("Pass 2");
		assertFalse(bank.getAccounts().get(11111111).getBalance() < 100);
	}

	@Test
	public void Pass_100_closes_account_with_50_balance() {
		bank.getAccounts().get(11111111).deposit(50);
		bank.getAccounts().get(22222222).deposit(100);
		passCommandProcessor.passTime("Pass 100");
		assertEquals(2, bank.getAccounts().size());
	}

	@Test
	public void Pass_1_increases_balance_of_checking_account_with_1000_balance_and_3_apr_to_1002_point_5() {
		bank.getAccounts().get(11111111).deposit(1000);
		passCommandProcessor.passTime("Pass 1");
		assertEquals(1002.5, bank.getAccounts().get(11111111).getBalance());
	}

	@Test
	public void Pass_1_increases_balance_of_savings_account_with_5000_balance_and_point_6_apr_to_5002_point_5() {
		bank.addAccount("savings", 44444444, 0.6, 0);
		bank.getAccounts().get(44444444).deposit(5000);
		passCommandProcessor.passTime("Pass 1");
		assertEquals(5002.5, bank.getAccounts().get(44444444).getBalance());
	}

	@Test
	public void Pass_2_increases_balance_of_checking_account_with_1000_balance_and_3_apr_to_1002_point_5() {
		bank.getAccounts().get(11111111).deposit(1000);
		passCommandProcessor.passTime("Pass 2");
		assertEquals(1005.00625, bank.getAccounts().get(11111111).getBalance());
	}

	@Test
	public void Pass_1_increases_balance_of_cd_account_with_2000_balance_and_2_point_1_apr_to_2014_point_0367928937578() {
		bank.addAccount("cd", 44444444, 2.1, 2000);
		passCommandProcessor.passTime("Pass 1");
		assertEquals(2014.0367928937578, bank.getAccounts().get(44444444).getBalance());
	}
}
