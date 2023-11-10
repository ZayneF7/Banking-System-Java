import java.util.HashMap;
import java.util.Map;

public class Bank {
	private Map<Integer, Account> accounts;

	Bank() {
		accounts = new HashMap<>();
	}

	public Map<Integer, Account> getAccounts() {
		return accounts;
	}

	public void addAccount(String accountType, Integer accountID, double apr, double balance) {
		if (accountType.equals("checking")) {
			accounts.put(accountID, new CheckingAccount(accountID, apr));
		}
		if (accountType.equals("savings")) {
			accounts.put(accountID, new SavingsAccount(accountID, apr));
		}
		if (accountType.equals("cd")) {
			accounts.put(accountID, new CdAccount(accountID, apr, balance));
		}
	}

	public void depositIntoAccount(Integer accountId, double amountToDeposit) {
		getAccounts().get(accountId).deposit(amountToDeposit);
	}

	public void withdrawFromAccount(Integer accountId, double amountToWithdraw) {
		getAccounts().get(accountId).withdraw(amountToWithdraw);
	}
}
