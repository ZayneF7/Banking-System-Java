package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DepositCommandProcessorTest {
	DepositCommandProcessor depositCommandProcessor;
	Bank bank;

	@BeforeEach
	public void setUp() {
		bank = new Bank();
		bank.addAccount("checking", 12345678, 3.0, 0);
		depositCommandProcessor = new DepositCommandProcessor(bank);
	}

	@Test
	public void deposit_12345678_100_makes_account_with_0_dollars_have_a_new_balance_of_100_dollars() {
		depositCommandProcessor.depositIntoAccount("deposit 12345678 100");
		assertEquals(100, bank.getAccounts().get(12345678).getBalance());
	}

	@Test
	public void deposit_12345678_100_makes_account_with_100_dollars_have_a_new_balance_of_200_dollars() {
		bank.getAccounts().get(12345678).deposit(100);
		depositCommandProcessor.depositIntoAccount("deposit 12345678 100");
		assertEquals(200, bank.getAccounts().get(12345678).getBalance());
	}
}
