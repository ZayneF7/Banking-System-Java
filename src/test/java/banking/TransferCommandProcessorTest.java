package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransferCommandProcessorTest {
	Bank bank;
	TransferCommandProcessor transferCommandProcessor;

	@BeforeEach
	public void setUp() {
		bank = new Bank();
		transferCommandProcessor = new TransferCommandProcessor(bank);
	}

	@Test
	public void transfer_11111111_22222222_100_withdraws_100_from_1st_account_and_deposits_100_into_2nd_account() {
		bank.addAccount("checking", 11111111, 3.0, 0);
		bank.getAccounts().get(11111111).deposit(200);
		bank.addAccount("checking", 22222222, 3.0, 0);
		bank.getAccounts().get(22222222).deposit(100);
		transferCommandProcessor.transfer("transfer 11111111 22222222 100");
		assertEquals(100, bank.getAccounts().get(11111111).getBalance());
		assertEquals(200, bank.getAccounts().get(22222222).getBalance());
	}

	@Test
	public void transfer_11111111_22222222_0_withdraws_0_from_1st_account_and_deposits_0_into_2nd_account() {
		bank.addAccount("checking", 11111111, 3.0, 0);
		bank.getAccounts().get(11111111).deposit(200);
		bank.addAccount("savings", 22222222, 3.0, 0);
		bank.getAccounts().get(22222222).deposit(100);
		transferCommandProcessor.transfer("transfer 11111111 22222222 0");
		assertEquals(200, bank.getAccounts().get(11111111).getBalance());
		assertEquals(100, bank.getAccounts().get(22222222).getBalance());
	}

	@Test
	public void transfer_11111111_22222222_200_withdraws_100_from_1st_account_and_deposits_100_into_2nd_account() {
		bank.addAccount("checking", 11111111, 3.0, 0);
		bank.getAccounts().get(11111111).deposit(100);
		bank.addAccount("checking", 22222222, 3.0, 0);
		bank.getAccounts().get(22222222).deposit(100);
		transferCommandProcessor.transfer("transfer 11111111 22222222 200");
		assertEquals(0, bank.getAccounts().get(11111111).getBalance());
		assertEquals(200, bank.getAccounts().get(22222222).getBalance());
	}

	@Test
	public void transfer_11111111_22222222_0_reduces_from_savings_account_months_by_1() {
		bank.addAccount("savings", 11111111, 3.0, 0);
		bank.getAccounts().get(11111111).increaseNumOfMonths(10);
		bank.addAccount("checking", 22222222, 3.0, 0);
		transferCommandProcessor.transfer("transfer 11111111 22222222 0");
		assertEquals(9, bank.getAccounts().get(11111111).getMonths());
	}

	@Test
	public void transfer_11111111_22222222_0_does_not_reduce_to_savings_account_months() {
		bank.addAccount("checking", 11111111, 3.0, 0);
		bank.addAccount("savings", 22222222, 3.0, 0);
		bank.getAccounts().get(22222222).increaseNumOfMonths(10);
		transferCommandProcessor.transfer("transfer 11111111 22222222 0");
		assertEquals(10, bank.getAccounts().get(22222222).getMonths());
	}

	@Test
	public void transfer_11111111_22222222_0_does_not_reduce_from_checking_account_months() {
		bank.addAccount("checking", 11111111, 3.0, 0);
		bank.getAccounts().get(11111111).increaseNumOfMonths(10);
		bank.addAccount("checking", 22222222, 3.0, 0);
		transferCommandProcessor.transfer("transfer 11111111 22222222 0");
		assertEquals(10, bank.getAccounts().get(11111111).getMonths());
	}

	@Test
	public void transfer_11111111_22222222_0_does_not_reduce_to_checking_account_months() {
		bank.addAccount("checking", 11111111, 3.0, 0);
		bank.addAccount("checking", 22222222, 3.0, 0);
		bank.getAccounts().get(22222222).increaseNumOfMonths(10);
		transferCommandProcessor.transfer("transfer 11111111 22222222 0");
		assertEquals(10, bank.getAccounts().get(22222222).getMonths());
	}
}
