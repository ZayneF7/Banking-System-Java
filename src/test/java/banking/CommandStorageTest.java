package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandStorageTest {
	CommandStorage commandStorage;

	@BeforeEach
	public void setUp() {
		commandStorage = new CommandStorage();
	}

	@Test
	public void storage_is_created_with_no_commands() {
		assertEquals(0, commandStorage.getCommands().size());
	}

	@Test
	public void storage_has_one_command_after_one_command_is_added() {
		commandStorage.addCommand("Foo");
		assertEquals(1, commandStorage.getCommands().size());
	}

	@Test
	public void storage_has_two_commands_after_two_commands_is_added() {
		commandStorage.addCommand("Foo");
		commandStorage.addCommand("Foobar");
		assertEquals(2, commandStorage.getCommands().size());
	}
}
