package banking;

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
}
