package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AccountTest {
	public static final Integer ACCOUNT_ID = 12345678;
	public static final Double APR = 9.0;
	public static final Double BALANCE = 100.0;

	@Test
	public void checking_account_created_with_initial_balance_of_zero() {
		Account checkingAccount = new CheckingAccount(ACCOUNT_ID, APR);
		double actual = checkingAccount.getBalance();
		assertEquals(0, actual);
	}

	@Test
	public void cd_account_created_with_specified_balance() {
		Account cdAccount = new CdAccount(ACCOUNT_ID, APR, BALANCE);
		double actual = cdAccount.getBalance();
		assertEquals(100, actual);
	}

	@Test
	public void checking_account_created_with_specified_APR_value() {
		Account checkingAccount = new CheckingAccount(ACCOUNT_ID, APR);
		double actual = checkingAccount.getAPR();
		assertEquals(9.0, actual);
	}

	@Test
	public void deposit_100_dollars() {
		Account checkingAccount = new CheckingAccount(ACCOUNT_ID, APR);
		checkingAccount.deposit(100);
		double actual = checkingAccount.getBalance();
		assertEquals(100, actual);
	}

	@Test
	public void deposit_100_dollars_twice() {
		Account checkingAccount = new CheckingAccount(ACCOUNT_ID, APR);
		checkingAccount.deposit(100);
		checkingAccount.deposit(100);
		double actual = checkingAccount.getBalance();
		assertEquals(200, actual);
	}

	@Test
	public void withdraw_20_dollars() {
		Account checkingAccount = new CheckingAccount(ACCOUNT_ID, APR);
		checkingAccount.deposit(100);
		checkingAccount.withdraw(20);
		double actual = checkingAccount.getBalance();
		assertEquals(80, actual);
	}

	@Test
	public void withdraw_20_dollars_twice() {
		Account checkingAccount = new CheckingAccount(ACCOUNT_ID, APR);
		checkingAccount.deposit(100);
		checkingAccount.withdraw(20);
		checkingAccount.withdraw(20);
		double actual = checkingAccount.getBalance();
		assertEquals(60, actual);
	}

	@Test
	public void withdraw_100_dollars_from_80_dollar_balance() {
		Account checkingAccount = new CheckingAccount(ACCOUNT_ID, APR);
		checkingAccount.deposit(80);
		checkingAccount.withdraw(100);
		double actual = checkingAccount.getBalance();
		assertEquals(0, actual);
	}

	@Test
	public void withdraw_100_dollars_from_100_dollar_balance() {
		Account checkingAccount = new CheckingAccount(ACCOUNT_ID, APR);
		checkingAccount.deposit(100);
		checkingAccount.withdraw(100);
		double actual = checkingAccount.getBalance();
		assertEquals(0, actual);
	}
}
