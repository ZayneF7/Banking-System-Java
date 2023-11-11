package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandProcessorTest {
	CommandProcessor commandProcessor;
	Bank bank;

	@BeforeEach
	public void setUp() {
		bank = new Bank();
		commandProcessor = new CommandProcessor(bank);
	}

	@Test
	public void create_checking_12345678_3_makes_a_checking_account_with_id_12345678() {
		commandProcessor.createAccount("create checking 12345678 3");
		assertTrue(bank.getAccounts().get(12345678) instanceof CheckingAccount);
	}

	@Test
	public void create_checking_12345678_3_makes_a_checking_account_with_apr_of_3() {
		commandProcessor.createAccount("create checking 12345678 3");
		assertEquals(3.0, bank.getAccounts().get(12345678).getAPR());
	}

	@Test
	public void create_savings_12345678_3_makes_a_savings_account_with_id_12345678() {
		commandProcessor.createAccount("create savings 12345678 3");
		assertTrue(bank.getAccounts().get(12345678) instanceof SavingsAccount);
	}

	@Test
	public void create_savings_12345678_3_makes_a_savings_account_with_apr_of_3() {
		commandProcessor.createAccount("create checking 12345678 3");
		assertEquals(3.0, bank.getAccounts().get(12345678).getAPR());
	}

	@Test
	public void create_cd_12345678_3_100_makes_a_checking_account_with_id_12345678() {
		commandProcessor.createAccount("create cd 12345678 3 100");
		assertTrue(bank.getAccounts().get(12345678) instanceof CdAccount);
	}

	@Test
	public void create_savings_12345678_3_100_makes_a_cd_account_with_apr_of_3() {
		commandProcessor.createAccount("create cd 12345678 3 100");
		assertEquals(3.0, bank.getAccounts().get(12345678).getAPR());
	}

	@Test
	public void create_savings_12345678_3_100_makes_a_cd_account_with_balance_of_100() {
		commandProcessor.createAccount("create cd 12345678 3 100");
		assertEquals(100, bank.getAccounts().get(12345678).getBalance());
	}

	@Test
	public void deposit_12345678_100_makes_account_with_0_dollars_have_a_new_balance_of_100_dollars() {
		commandProcessor.createAccount("create savings 12345678 3");
		commandProcessor.depositIntoAccount("deposit 12345678 100");
		assertEquals(100, bank.getAccounts().get(12345678).getBalance());
	}

	@Test
	public void deposit_12345678_100_makes_account_with_100_dollars_have_a_new_balance_of_200_dollars() {
		commandProcessor.createAccount("create cd 12345678 3 100");
		commandProcessor.depositIntoAccount("deposit 12345678 100");
		assertEquals(200, bank.getAccounts().get(12345678).getBalance());
	}
}
