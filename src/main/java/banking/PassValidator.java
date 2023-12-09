package banking;

public class PassValidator extends Validator {
	public PassValidator() {
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
