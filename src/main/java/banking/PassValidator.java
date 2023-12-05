package banking;

public class PassValidator {
	public PassValidator() {
	}

	public String[] parse(String inputCommand) {
		String lowercaseInputCommand = inputCommand.toLowerCase();
		return lowercaseInputCommand.split(" ");
	}

	public boolean numOfMonthsIsValid(String arrayElement) {
		try {
			int numOfMonths = Integer.parseInt(arrayElement);
			return ((!(arrayElement.contains("."))) && 1 <= numOfMonths && numOfMonths <= 60);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean commandIsValid(String inputCommand) {
		String[] parsedCommand = parse(inputCommand);
		return (parsedCommand.length == 2) && (parsedCommand[0].equals("pass"))
				&& (numOfMonthsIsValid(parsedCommand[1]));
	}
}
