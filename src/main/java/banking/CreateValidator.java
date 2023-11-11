package banking;

public class CreateValidator {
	private Bank bank;

	public CreateValidator(Bank bank) {
		this.bank = bank;
	}

	public String[] parse(String inputCommand) {
		String lowercaseInputCommand = inputCommand.toLowerCase();
		return lowercaseInputCommand.split(" ");
	}

	public boolean arrayHasFourElements(String[] parsedCommand) {
		return parsedCommand.length == 4;
	}

	public boolean arrayHasFiveElements(String[] parsedCommand) {
		return parsedCommand.length == 5;
	}

	public boolean idIsValid(String arrayElement) {
		try {
			int id = Integer.parseInt(arrayElement);
			boolean accountExists = bank.getAccounts().containsKey(id);
			return (arrayElement.length() == 8) && (id >= 0) && (!accountExists);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean aprIsValid(String arrayElement) {
		try {
			double apr = Double.parseDouble(arrayElement);
			return (0 <= apr) && (apr <= 10);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean balanceIsValid(String arrayElement) {
		try {
			double balance = Double.parseDouble(arrayElement);
			return (1000 <= balance) && (balance <= 10000);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean commandIsValid(String inputCommand) {
		String[] parsedCommand = parse(inputCommand);
		if (parsedCommand[0].equals("create")) {
			if (parsedCommand[1].equals("checking") || (parsedCommand[1].equals("savings"))) {
				if (arrayHasFourElements(parsedCommand)) {
					return (idIsValid(parsedCommand[2])) && (aprIsValid(parsedCommand[3]));
				} else {
					return false;
				}
			} else if (parsedCommand[1].equals("cd")) {
				if (arrayHasFiveElements(parsedCommand)) {
					return (idIsValid(parsedCommand[2])) && (aprIsValid(parsedCommand[3]))
							&& (balanceIsValid(parsedCommand[4]));
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
