package banking;

public abstract class Account {
	private int accountId;
	private double apr;
	private double balance;

	public Account(int accountId, double apr) {
		this.accountId = accountId;
		this.apr = apr;
		balance = 0;
	}

	public Account(int accountId, double apr, double balance) {
		this.accountId = accountId;
		this.apr = apr;
		this.balance = balance;
	}

	public int getAccountId() {
		return accountId;
	}

	public double getAPR() {
		return apr;
	}

	public double getBalance() {
		return balance;
	}

	public void deposit(double amountToDeposit) {
		balance += amountToDeposit;
	}

	public void withdraw(double amountToWithdraw) {
		if (amountToWithdraw >= balance) {
			balance = 0;
		} else {
			balance -= amountToWithdraw;
		}
	}
}
