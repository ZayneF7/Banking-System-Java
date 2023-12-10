package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CommandStorageTest {

	@Test
	public void storage_is_created_with_no_commands() {
		CommandStorage invalidCommandStorage = new InvalidCommandStorage();
		assertEquals(0, invalidCommandStorage.getCommands().size());
	}

	@Test
	public void storage_has_one_command_after_one_command_is_added() {
		CommandStorage invalidCommandStorage = new InvalidCommandStorage();
		invalidCommandStorage.addCommand("Foo");
		assertEquals(1, invalidCommandStorage.getCommands().size());
	}

	@Test
	public void storage_has_two_commands_after_two_commands_is_added() {
		CommandStorage validCommandStorage = new ValidCommandStorage();
		validCommandStorage.addCommand("Foo");
		validCommandStorage.addCommand("Foobar");
		assertEquals(2, validCommandStorage.getCommands().size());
	}
}
