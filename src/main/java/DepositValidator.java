public class DepositValidator {
	private Bank bank;

	public DepositValidator(Bank bank) {
		this.bank = bank;
	}

	public String[] parse(String inputCommand) {
		String lowercaseInputCommand = inputCommand.toLowerCase();
		return lowercaseInputCommand.split(" ");
	}

	public boolean arrayHasThreeElements(String[] parsedCommand) {
		return parsedCommand.length == 3;
	}

	public boolean accountIdExists(String arrayElement) {
		try {
			int id = Integer.parseInt(arrayElement);
			return bank.getAccounts().containsKey(id);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean isCheckingType(String idString) {
		int id = Integer.parseInt(idString);
		return (bank.getAccounts().get(id) instanceof CheckingAccount);
	}

	public boolean isSavingsType(String idString) {
		int id = Integer.parseInt(idString);
		return (bank.getAccounts().get(id) instanceof SavingsAccount);
	}

	public boolean validDepositForChecking(String depositString) {
		try {
			double depositAmount = Double.parseDouble(depositString);
			return (0 <= depositAmount) && (depositAmount <= 1000);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean validDepositForSavings(String depositString) {
		try {
			double depositAmount = Double.parseDouble(depositString);
			return (0 <= depositAmount) && (depositAmount <= 2500);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean commandIsValid(String inputCommand) {
		String[] parsedCommand = parse(inputCommand);
		if (arrayHasThreeElements(parsedCommand)) {
			if (parsedCommand[0].equals("deposit")) {
				if (accountIdExists(parsedCommand[1])) {
					String idString = parsedCommand[1];
					String depositString = parsedCommand[2];
					if ((isCheckingType(idString)) && (validDepositForChecking(depositString))) {
						return true;
					} else if ((isSavingsType(idString)) && (validDepositForSavings(depositString))) {
						return true;
					} else {
						return false;
					}
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
