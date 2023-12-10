package banking;

import java.util.ArrayList;

public abstract class CommandStorage {
	private ArrayList<String> commands;

	public CommandStorage() {
		commands = new ArrayList<String>();
	}

	public ArrayList<String> getCommands() {
		return commands;
	}

	public void addCommand(String command) {
		commands.add(command);
	}
}
