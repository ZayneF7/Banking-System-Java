public class CommandProcessor {

	public CommandProcessor() {
	}

	public void createAccount(String commandString, Bank bank) {
		String[] splitCommandString = commandString.toLowerCase().split(" ");

		if (splitCommandString[1].equals("checking")) {
			int id = Integer.parseInt(splitCommandString[2]);
			double apr = Double.parseDouble(splitCommandString[3]);
			bank.addAccount("checking", id, apr, 0);
		} else if (splitCommandString[1].equals("savings")) {
			int id = Integer.parseInt(splitCommandString[2]);
			double apr = Double.parseDouble(splitCommandString[3]);
			bank.addAccount("savings", id, apr, 0);
		} else if (splitCommandString[1].equals("cd")) {
			int id = Integer.parseInt(splitCommandString[2]);
			double apr = Double.parseDouble(splitCommandString[3]);
			double balance = Double.parseDouble(splitCommandString[4]);
			bank.addAccount("cd", id, apr, balance);
		}
	}

	public void depositIntoAccount(String commandString, Bank bank) {
		String[] splitCommandString = commandString.toLowerCase().split(" ");
		int id = Integer.parseInt(splitCommandString[1]);
		double amountToDeposit = Double.parseDouble((splitCommandString[2]));
		bank.getAccounts().get(id).deposit(amountToDeposit);
	}

}
