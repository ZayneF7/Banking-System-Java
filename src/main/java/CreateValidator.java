public class CreateValidator {

	public CreateValidator() {
	}

	public String[] parse(String inputCommand) {
		String lowercaseInputCommand = inputCommand.toLowerCase();
		return lowercaseInputCommand.split(" ");
	}

	public boolean arrayHasFourArrayElements(String[] parsedCommand) {
		return parsedCommand.length == 4;
	}

	public boolean idIsValid(String arrayElement, Bank bank) {
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

	public boolean commandIsValid(String inputCommand, Bank bank) {
		String[] parsedCommand = parse(inputCommand);
		if (parsedCommand[0].equals("create")) {
			if (parsedCommand[1].equals("checking") || (parsedCommand[1].equals("savings"))) {
				if (arrayHasFourArrayElements(parsedCommand)) {
					return (idIsValid(parsedCommand[2], bank)) && (aprIsValid(parsedCommand[3]));
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
