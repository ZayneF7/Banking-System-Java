package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ValidCommandStorageTest {
	ValidCommandStorage validCommandStorage;

	@BeforeEach
	public void setUp() {
		validCommandStorage = new ValidCommandStorage();
	}

	@Test
	public void storage_is_created_with_no_commands() {
		assertEquals(0, validCommandStorage.getCommands().size());
	}

	@Test
	public void storage_has_one_command_after_one_command_is_added() {
		validCommandStorage.addCommand("Foo");
		assertEquals(1, validCommandStorage.getCommands().size());
	}

	@Test
	public void storage_has_two_commands_after_two_commands_is_added() {
		validCommandStorage.addCommand("Foo");
		validCommandStorage.addCommand("Foobar");
		assertEquals(2, validCommandStorage.getCommands().size());
	}
}
