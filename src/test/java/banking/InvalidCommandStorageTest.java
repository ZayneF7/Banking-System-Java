package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InvalidCommandStorageTest {
	InvalidCommandStorage invalidCommandStorage;

	@BeforeEach
	public void setUp() {

		invalidCommandStorage = new InvalidCommandStorage();
	}

	@Test
	public void storage_is_created_with_no_commands() {
		assertEquals(0, invalidCommandStorage.getCommands().size());
	}

	@Test
	public void storage_has_one_command_after_one_command_is_added() {
		invalidCommandStorage.addCommand("Foo");
		assertEquals(1, invalidCommandStorage.getCommands().size());
	}

	@Test
	public void storage_has_two_commands_after_two_commands_is_added() {
		invalidCommandStorage.addCommand("Foo");
		invalidCommandStorage.addCommand("Foobar");
		assertEquals(2, invalidCommandStorage.getCommands().size());
	}
}
