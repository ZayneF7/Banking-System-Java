package banking;

public class CommandProcessor {
	private Bank bank;

	public CommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void createAccount(String commandString) {
		String[] splitCommandString = commandString.toLowerCase().split(" ");

		int id = Integer.parseInt(splitCommandString[2]);
		double apr = Double.parseDouble(splitCommandString[3]);

		if (splitCommandString[1].equals("checking")) {
			bank.addAccount("checking", id, apr, 0);
		} else if (splitCommandString[1].equals("savings")) {
			bank.addAccount("savings", id, apr, 0);
		} else if (splitCommandString[1].equals("cd")) {
			double balance = Double.parseDouble(splitCommandString[4]);
			bank.addAccount("cd", id, apr, balance);
		}
	}

	public void depositIntoAccount(String commandString) {
		String[] splitCommandString = commandString.toLowerCase().split(" ");
		int id = Integer.parseInt(splitCommandString[1]);
		double amountToDeposit = Double.parseDouble((splitCommandString[2]));
		bank.getAccounts().get(id).deposit(amountToDeposit);
	}

}
