package banking;

public abstract class Validator {
	private Bank bank;

	public Validator(Bank bank) {
		this.bank = bank;
	}

	public String[] parse(String inputCommand) {
		String lowercaseInputCommand = inputCommand.toLowerCase();
		return lowercaseInputCommand.split(" ");
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

	public boolean isCdType(String idString) {
		int id = Integer.parseInt(idString);
		return (bank.getAccounts().get(id) instanceof CdAccount);
	}
}
