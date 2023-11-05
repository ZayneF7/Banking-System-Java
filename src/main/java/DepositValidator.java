public class DepositValidator {

	public DepositValidator() {
	}

	public String[] parse(String inputCommand) {
		String lowercaseInputCommand = inputCommand.toLowerCase();
		return lowercaseInputCommand.split(" ");
	}

	public boolean arrayHasThreeElements(String[] parsedCommand) {
		return parsedCommand.length == 3;
	}

	public boolean accountIdExists(String arrayElement, Bank bank) {
		try {
			int id = Integer.parseInt(arrayElement);
			return bank.getAccounts().containsKey(id);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean isCheckingType(int id, Bank bank) {
		return (bank.getAccounts().get(id) instanceof CheckingAccount);
	}

	public boolean isSavingsType(int id, Bank bank) {
		return (bank.getAccounts().get(id) instanceof SavingsAccount);
	}

	public boolean isCdType(int id, Bank bank) {
		return (bank.getAccounts().get(id) instanceof CdAccount);
	}
}
