import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SavingsAccountTest {
	public static final Integer ACCOUNT_ID = 12345678;
	public static final Double APR = 9.0;
	SavingsAccount savingsAccount;

	@BeforeEach
	public void setUp() {
		savingsAccount = new SavingsAccount(ACCOUNT_ID, APR);
	}

	@Test
	public void savings_account_created_with_initial_balance_of_zero() {
		double actual = savingsAccount.getBalance();
		assertEquals(0, actual);
	}

	@Test
	public void savings_account_created_with_specified_APR_value() {
		double actual = savingsAccount.getAPR();
		assertEquals(9.0, actual);
	}

	@Test
	public void deposit_100_dollars() {
		savingsAccount.deposit(100);
		double actual = savingsAccount.getBalance();
		assertEquals(100, actual);
	}

	@Test
	public void deposit_100_dollars_twice() {
		savingsAccount.deposit(100);
		savingsAccount.deposit(100);
		double actual = savingsAccount.getBalance();
		assertEquals(200, actual);
	}

	@Test
	public void withdraw_20_dollars() {
		savingsAccount.deposit(100);
		savingsAccount.withdraw(20);
		double actual = savingsAccount.getBalance();
		assertEquals(80, actual);
	}

	@Test
	public void withdraw_20_dollars_twice() {
		savingsAccount.deposit(100);
		savingsAccount.withdraw(20);
		savingsAccount.withdraw(20);
		double actual = savingsAccount.getBalance();
		assertEquals(60, actual);
	}

	@Test
	public void withdraw_100_dollars_from_80_dollar_balance() {
		savingsAccount.deposit(80);
		savingsAccount.withdraw(100);
		double actual = savingsAccount.getBalance();
		assertEquals(0, actual);
	}
}
