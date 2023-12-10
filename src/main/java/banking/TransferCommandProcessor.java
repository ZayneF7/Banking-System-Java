package banking;

public class TransferCommandProcessor {
	private Bank bank;

	public TransferCommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void transfer(String commandString) {
		String[] splitCommandString = commandString.toLowerCase().split(" ");
		int fromId = Integer.parseInt(splitCommandString[1]);
		int toId = Integer.parseInt(splitCommandString[2]);
		double amountToTransfer = Double.parseDouble((splitCommandString[3]));
		if (amountToTransfer > bank.getAccounts().get(fromId).getBalance()) {
			double fromBalance = bank.getAccounts().get(fromId).getBalance();
			bank.getAccounts().get(fromId).withdraw(fromBalance);
			bank.getAccounts().get(toId).deposit(fromBalance);
		} else {
			bank.getAccounts().get(fromId).withdraw(amountToTransfer);
			bank.getAccounts().get(toId).deposit(amountToTransfer);
		}
		if (bank.getAccounts().get(fromId) instanceof SavingsAccount) {
			bank.getAccounts().get(fromId).decreaseNumOfMonths(1);
		}
	}
}
