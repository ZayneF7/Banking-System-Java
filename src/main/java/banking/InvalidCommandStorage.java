package banking;

import java.util.ArrayList;

public class InvalidCommandStorage {
	private ArrayList<String> commands;

	public InvalidCommandStorage() {
		commands = new ArrayList<String>();
	}

	public ArrayList<String> getCommands() {
		return commands;
	}

	public void addCommand(String command) {
		commands.add(command);
	}
}
