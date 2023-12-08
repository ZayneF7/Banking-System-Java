package banking;

public class DepositValidator extends Validator {
	private Bank bank;

	public DepositValidator(Bank bank) {
		super(bank);
		this.bank = bank;
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
		if (parsedCommand.length == 3) {
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
