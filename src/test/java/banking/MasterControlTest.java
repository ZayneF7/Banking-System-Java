package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MasterControlTest {
	List<String> input;
	MasterControl masterControl;

	@BeforeEach
	void setUp() {
		input = new ArrayList<>();
		Bank bank = new Bank();
		InvalidCommandStorage invalidCommandStorage = new InvalidCommandStorage();
		ValidCommandStorage validCommandStorage = new ValidCommandStorage();
		masterControl = new MasterControl(new CreateValidator(bank), new DepositValidator(bank), new PassValidator(),
				new WithdrawValidator(bank), new TransferValidator(bank), new CreateCommandProcessor(bank),
				new DepositCommandProcessor(bank), new PassCommandProcessor(bank), new WithdrawCommandProcessor(bank),
				new TransferCommandProcessor(bank), invalidCommandStorage, validCommandStorage,
				new Output(bank, validCommandStorage, invalidCommandStorage));
	}

	private void assertSingleCommand(String command, List<String> actual) {
		assertEquals(1, actual.size());
		assertEquals(command, actual.get(0));
	}

	@Test
	void typo_in_create_command_is_invalid() {
		input.add("creat checking 12345678 1.0");

		List<String> actual = masterControl.start(input);

		assertSingleCommand("creat checking 12345678 1.0", actual);
	}

	@Test
	void typo_in_deposit_command_is_invalid() {
		input.add("depositt 12345678 100");

		List<String> actual = masterControl.start(input);

		assertSingleCommand("depositt 12345678 100", actual);
	}

	@Test
	void two_typo_commands_both_invalid() {
		input.add("creat checking 12345678 1.0");
		input.add("depositt 12345678 100");

		List<String> actual = masterControl.start(input);

		assertEquals(2, actual.size());
		assertEquals("creat checking 12345678 1.0", actual.get(0));
		assertEquals("depositt 12345678 100", actual.get(1));
	}

	@Test
	void sample_make_sure_this_passes_unchanged_or_you_will_fail() {
		input.add("Create savings 12345678 0.6");
		input.add("Deposit 12345678 700");
		input.add("Deposit 12345678 5000");
		input.add("creAte cHecKing 98765432 0.01");
		input.add("Deposit 98765432 300");
		input.add("Transfer 98765432 12345678 300");
		input.add("Pass 1");
		input.add("Create cd 23456789 1.2 2000");
		List<String> actual = masterControl.start(input);

		assertEquals(5, actual.size());
		assertEquals("Savings 12345678 1000.50 0.60", actual.get(0));
		assertEquals("Deposit 12345678 700", actual.get(1));
		assertEquals("Transfer 98765432 12345678 300", actual.get(2));
		assertEquals("Cd 23456789 2000.00 1.20", actual.get(3));
		assertEquals("Deposit 12345678 5000", actual.get(4));
	}

	@Test
	void test_1() {
		input.add("Create Checking 11111111 3");
		input.add("Deposit 11111111 100");
		input.add("pass 2");
		input.add("deposit 33333333 200");
		input.add("witHDraW 11111111 50");
		List<String> actual = masterControl.start(input);

		assertEquals(4, actual.size());
		assertEquals("Checking 11111111 50.50 3.00", actual.get(0));
		assertEquals("Deposit 11111111 100", actual.get(1));
		assertEquals("witHDraW 11111111 50", actual.get(2));
		assertEquals("deposit 33333333 200", actual.get(3));
	}

}
