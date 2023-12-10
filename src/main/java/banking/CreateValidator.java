package banking;

public class CreateValidator extends Validator {
	private Bank bank;

	public CreateValidator(Bank bank) {
		super(bank);
		this.bank = bank;
	}

	public boolean idIsValid(String arrayElement) {
		try {
			int id = Integer.parseInt(arrayElement);
			boolean accountExists = bank.getAccounts().containsKey(id);
			return (arrayElement.length() == 8) && (id >= 0) && (!accountExists);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean aprIsValid(String arrayElement) {
		try {
			double apr = Double.parseDouble(arrayElement);
			return (0 <= apr) && (apr <= 10);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean balanceIsValid(String arrayElement) {
		try {
			double balance = Double.parseDouble(arrayElement);
			return (1000 <= balance) && (balance <= 10000);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean commandIsValid(String inputCommand) {
		String[] parsedCommand = parse(inputCommand);
		if (parsedCommand[0].equals("create")) {
			if (parsedCommand[1].equals("checking") || (parsedCommand[1].equals("savings"))) {
				if (parsedCommand.length == 4) {
					return (idIsValid(parsedCommand[2])) && (aprIsValid(parsedCommand[3]));
				} else {
					return false;
				}
			} else if (parsedCommand[1].equals("cd")) {
				if (parsedCommand.length == 5) {
					return (idIsValid(parsedCommand[2])) && (aprIsValid(parsedCommand[3]))
							&& (balanceIsValid(parsedCommand[4]));
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
