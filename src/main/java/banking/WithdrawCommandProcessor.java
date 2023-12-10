package banking;

public class WithdrawCommandProcessor {
	private Bank bank;

	public WithdrawCommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void withdrawFromAccount(String commandString) {
		String[] splitCommandString = commandString.toLowerCase().split(" ");
		int id = Integer.parseInt(splitCommandString[1]);
		double amountToWithdraw = Double.parseDouble((splitCommandString[2]));
		double balance = bank.getAccounts().get(id).getBalance();
		if (amountToWithdraw > balance) {
			bank.getAccounts().get(id).withdraw(balance);
		} else {
			bank.getAccounts().get(id).withdraw(amountToWithdraw);
		}
		if (bank.getAccounts().get(id) instanceof SavingsAccount) {
			bank.getAccounts().get(id).decreaseNumOfMonths(1);
		} else if (bank.getAccounts().get(id) instanceof CdAccount) {
			bank.getAccounts().get(id).decreaseNumOfMonths(12);
		}
	}
}
