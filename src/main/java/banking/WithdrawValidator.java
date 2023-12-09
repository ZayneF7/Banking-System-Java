package banking;

public class WithdrawValidator extends Validator {
	private Bank bank;

	public WithdrawValidator(Bank bank) {
		super(bank);
		this.bank = bank;
	}

	public boolean validWithdrawForChecking(String withdrawString) {
		try {
			double withdrawAmount = Double.parseDouble(withdrawString);
			return (0 <= withdrawAmount) && (withdrawAmount <= 400);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean validWithdrawForSavings(String withdrawString) {
		try {
			double withdrawAmount = Double.parseDouble(withdrawString);
			return (0 <= withdrawAmount) && (withdrawAmount <= 1000);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean validWithdrawForCd(String idString, String withdrawString) {
		try {
			int id = Integer.parseInt(idString);
			double withdrawAmount = Double.parseDouble(withdrawString);
			return (withdrawAmount >= bank.getAccounts().get(id).getBalance());
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean commandIsValid(String inputCommand) {
		String[] parsedCommand = parse(inputCommand);
		if (parsedCommand.length == 3) {
			if (parsedCommand[0].equals("withdraw")) {
				if (accountIdExists(parsedCommand[1])) {
					String idString = parsedCommand[1];
					String withdrawString = parsedCommand[2];
					int months = bank.getAccounts().get(Integer.parseInt(idString)).getMonths();
					if (isCheckingType(idString) && validWithdrawForChecking(withdrawString)) {
						return true;
					} else if (isSavingsType(idString) && validWithdrawForSavings(withdrawString) && (months >= 1)) {
						return true;
					} else if (isCdType(idString) && validWithdrawForCd(idString, withdrawString) && (months >= 12)) {
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
