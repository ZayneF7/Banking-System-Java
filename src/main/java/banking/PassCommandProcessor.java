package banking;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PassCommandProcessor {
	private Map<Integer, Account> accounts;
	private Bank bank;

	public PassCommandProcessor(Bank bank) {
		this.bank = bank;
		this.accounts = bank.getAccounts();
	}

	private void calculateApr(int id) {
		int n;
		if (bank.getAccounts().get(id) instanceof CdAccount) {
			n = 4;
		} else {
			n = 1;
		}
		for (int i = 0; i < n; i++) {
			double apr = bank.getAccounts().get(id).getAPR();
			double balance = bank.getAccounts().get(id).getBalance();
			double aprDecimal = apr / 100;
			double amountToAdd = (aprDecimal / 12) * balance;
			bank.getAccounts().get(id).deposit(amountToAdd);
		}
	}

	public void passTime(String commandString) {
		List<Integer> accountsToClose = new ArrayList<>();
		String[] splitCommandString = commandString.toLowerCase().split(" ");
		int months = Integer.parseInt(splitCommandString[1]);
		for (Map.Entry<Integer, Account> account : accounts.entrySet()) {
			int id = account.getKey();
			for (int i = 0; i < months; i++) {
				bank.getAccounts().get(id).increaseNumOfMonths(1);
				if (bank.getAccounts().get(id).getBalance() == 0) {
					accountsToClose.add(id);
				} else if (bank.getAccounts().get(id).getBalance() < 100) {
					bank.getAccounts().get(id).withdraw(25);
				}
				calculateApr(id);
			}
		}
		for (int id : accountsToClose) {
			accounts.remove(id);
		}
	}
}
