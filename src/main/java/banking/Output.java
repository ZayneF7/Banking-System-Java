package banking;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Output {
	private Bank bank;
	private Map<Integer, Account> accounts;
	private ValidCommandStorage validCommandStorage;
	private InvalidCommandStorage invalidCommandStorage;
	private ArrayList<String> output;

	public Output(Bank bank, ValidCommandStorage validCommandStorage, InvalidCommandStorage invalidCommandStorage) {
		this.bank = bank;
		this.accounts = bank.getAccounts();
		this.validCommandStorage = validCommandStorage;
		this.invalidCommandStorage = invalidCommandStorage;
		output = new ArrayList<String>();
	}

	public ArrayList<String> createOutputList() {
		ArrayList<String> validCommands = validCommandStorage.getCommands();
		ArrayList<String> invalidCommands = invalidCommandStorage.getCommands();
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		decimalFormat.setRoundingMode(RoundingMode.FLOOR);
		List<Integer> accountKeys = new ArrayList<>(accounts.keySet());
		Collections.reverse(accountKeys);
		for (Integer id : accountKeys) {
			String idString = Integer.toString(id);
			double apr = bank.getAccounts().get(id).getAPR();
			String aprString = decimalFormat.format(apr);
			double balance = bank.getAccounts().get(id).getBalance();
			String balanceString = decimalFormat.format(balance);
			if (bank.getAccounts().get(id) instanceof CheckingAccount) {
				String accountState = "Checking " + idString + " " + balanceString + " " + aprString;
				output.add(accountState);
			} else if (bank.getAccounts().get(id) instanceof SavingsAccount) {
				String accountState = "Savings " + idString + " " + balanceString + " " + aprString;
				output.add(accountState);
			} else if (bank.getAccounts().get(id) instanceof CdAccount) {
				String accountState = "Cd " + idString + " " + balanceString + " " + aprString;
				output.add(accountState);
			}
			for (String command : validCommands) {
				if (command.contains(idString)) {
					output.add(command);
				}
			}
		}
		for (String command : invalidCommands) {
			output.add(command);
		}
		return output;
	}
}
