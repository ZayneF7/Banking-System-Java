package banking;

public class WithdrawValidator {
	private Bank bank;

	public WithdrawValidator(Bank bank) {
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

}
