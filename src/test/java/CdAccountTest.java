import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CdAccountTest {
	public static final Integer ACCOUNT_ID = 12345678;
	public static final Double APR = 9.0;
	public static final Double BALANCE = 100.0;
	CdAccount cdAccount;

	@BeforeEach
	public void setUp() {
		cdAccount = new CdAccount(ACCOUNT_ID, APR, BALANCE);
	}

	@Test
	public void cd_account_created_with_specified_balance() {
		double actual = cdAccount.getBalance();
		assertEquals(100, actual);
	}

	@Test
	public void checking_account_created_with_specified_APR_value() {
		double actual = cdAccount.getAPR();
		assertEquals(9.0, actual);
	}

	@Test
	public void deposit_100_dollars() {
		cdAccount.deposit(100);
		double actual = cdAccount.getBalance();
		assertEquals(200, actual);
	}

	@Test
	public void deposit_100_dollars_twice() {
		cdAccount.deposit(100);
		cdAccount.deposit(100);
		double actual = cdAccount.getBalance();
		assertEquals(300, actual);
	}

	@Test
	public void withdraw_20_dollars() {
		cdAccount.withdraw(20);
		double actual = cdAccount.getBalance();
		assertEquals(80, actual);
	}

	@Test
	public void withdraw_20_dollars_twice() {
		cdAccount.withdraw(20);
		cdAccount.withdraw(20);
		double actual = cdAccount.getBalance();
		assertEquals(60, actual);
	}

	@Test
	public void withdraw_120_dollars_from_100_dollar_balance() {
		cdAccount.withdraw(120);
		double actual = cdAccount.getBalance();
		assertEquals(0, actual);
	}
}
