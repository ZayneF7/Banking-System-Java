package banking;

public class TransferValidator extends Validator {
	private Bank bank;
	private DepositValidator depositValidator;
	private WithdrawValidator withdrawValidator;

	public TransferValidator(Bank bank) {
		super(bank);
		this.bank = bank;
		this.depositValidator = new DepositValidator(bank);
		this.withdrawValidator = new WithdrawValidator(bank);
	}

	public boolean toFromAccountsValid(String idString1, String idString2) {
		return accountIdExists(idString1) && accountIdExists(idString2) && (idString1 != idString2)
				&& (!(isCdType(idString1))) && (!(isCdType(idString2)));
	}

	public boolean commandIsValid(String inputCommand) {
		String[] parsedCommand = parse(inputCommand);
		if (parsedCommand.length == 4) {
			if (parsedCommand[0].equals("transfer")) {
				if (toFromAccountsValid(parsedCommand[1], parsedCommand[2])) {
					String fromIdString = parsedCommand[1];
					String toIdString = parsedCommand[2];
					String transferString = parsedCommand[3];
					String withdrawCommand = "withdraw" + " " + fromIdString + " " + transferString;
					String depositCommand = "deposit" + " " + toIdString + " " + transferString;
					return withdrawValidator.commandIsValid(withdrawCommand)
							&& depositValidator.commandIsValid(depositCommand);
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
