package banking;

public class DepositCommandProcessor {
	Bank bank;

	public DepositCommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void depositIntoAccount(String commandString) {
		String[] splitCommandString = commandString.toLowerCase().split(" ");
		int id = Integer.parseInt(splitCommandString[1]);
		double amountToDeposit = Double.parseDouble((splitCommandString[2]));
		bank.getAccounts().get(id).deposit(amountToDeposit);
	}
}
