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
		masterControl = new MasterControl(new CreateValidator(bank), new DepositValidator(bank),
				new CommandProcessor(bank), new CommandStorage());
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
	void invalid_to_create_accounts_with_same_ID() {
		input.add("create checking 12345678 1.0");
		input.add("create checking 12345678 1.0");

		List<String> actual = masterControl.start(input);

		assertSingleCommand("create checking 12345678 1.0", actual);
	}

	@Test
	void invalid_to_create_checking_account_with_specified_balance() {
		input.add("create checking 12345678 1.0 100");

		List<String> actual = masterControl.start(input);

		assertEquals("create checking 12345678 1.0 100", actual.get(0));
	}

	@Test
	void invalid_to_create_savings_account_with_specified_balance() {
		input.add("create savings 12345678 1.0 100");

		List<String> actual = masterControl.start(input);

		assertEquals("create savings 12345678 1.0 100", actual.get(0));
	}

	@Test
	void invalid_to_create_cd_account_without_specified_balance() {
		input.add("create cd 12345678 1.0");

		List<String> actual = masterControl.start(input);

		assertSingleCommand("create cd 12345678 1.0", actual);
	}

	@Test
	void invalid_to_deposit_negative_dollars() {
		input.add("create checking 12345678 1.0");
		input.add("deposit 12345678 -1");

		List<String> actual = masterControl.start(input);

		assertSingleCommand("deposit 12345678 -1", actual);
	}

	@Test
	void invalid_to_deposit_more_than_max_amount() {
		input.add("create checking 12345678 1.0");
		input.add("create savings 12345679 1.0");
		input.add("deposit 12345678 3000");
		input.add("deposit 12345679 3000");

		List<String> actual = masterControl.start(input);

		assertEquals(2, actual.size());
		assertEquals("deposit 12345678 3000", actual.get(0));
		assertEquals("deposit 12345679 3000", actual.get(1));
	}

	@Test
	void no_commands_returned_when_all_are_valid() {
		input.add("create checking 12345678 1.0");
		input.add("create savings 12345679 1.0");
		input.add("deposit 12345678 1000");
		input.add("deposit 12345679 1000");

		List<String> actual = masterControl.start(input);

		assertEquals(0, actual.size());

	}

}
