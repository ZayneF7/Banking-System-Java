package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheckingAccountTest {
	public static final Integer ACCOUNT_ID = 12345678;
	public static final Double APR = 9.0;
	CheckingAccount checkingAccount;

	@BeforeEach
	public void setUp() {
		checkingAccount = new CheckingAccount(ACCOUNT_ID, APR);
	}

	@Test
	public void checking_account_created_with_initial_balance_of_zero() {
		double actual = checkingAccount.getBalance();
		assertEquals(0, actual);
	}

	@Test
	public void checking_account_created_with_specified_APR_value() {
		double actual = checkingAccount.getAPR();
		assertEquals(9.0, actual);
	}

	@Test
	public void deposit_100_dollars() {
		checkingAccount.deposit(100);
		double actual = checkingAccount.getBalance();
		assertEquals(100, actual);
	}

	@Test
	public void deposit_100_dollars_twice() {
		checkingAccount.deposit(100);
		checkingAccount.deposit(100);
		double actual = checkingAccount.getBalance();
		assertEquals(200, actual);
	}

	@Test
	public void withdraw_20_dollars() {
		checkingAccount.deposit(100);
		checkingAccount.withdraw(20);
		double actual = checkingAccount.getBalance();
		assertEquals(80, actual);
	}

	@Test
	public void withdraw_20_dollars_twice() {
		checkingAccount.deposit(100);
		checkingAccount.withdraw(20);
		checkingAccount.withdraw(20);
		double actual = checkingAccount.getBalance();
		assertEquals(60, actual);
	}

	@Test
	public void withdraw_100_dollars_from_80_dollar_balance() {
		checkingAccount.deposit(80);
		checkingAccount.withdraw(100);
		double actual = checkingAccount.getBalance();
		assertEquals(0, actual);
	}
}
