package banking;

import java.util.List;

public class MasterControl {
	private CreateValidator createValidator;
	private DepositValidator depositValidator;
	private PassValidator passValidator;
	private WithdrawValidator withdrawValidator;
	private TransferValidator transferValidator;
	private CreateCommandProcessor createCommandProcessor;
	private DepositCommandProcessor depositCommandProcessor;
	private PassCommandProcessor passCommandProcessor;
	private WithdrawCommandProcessor withdrawCommandProcessor;
	private TransferCommandProcessor transferCommandProcessor;
	private InvalidCommandStorage invalidCommandStorage;

	public MasterControl(CreateValidator createValidator, DepositValidator depositValidator,
			PassValidator passValidator, WithdrawValidator withdrawValidator, TransferValidator transferValidator,
			CreateCommandProcessor createCommandProcessor, DepositCommandProcessor depositCommandProcessor,
			PassCommandProcessor passCommandProcessor, WithdrawCommandProcessor withdrawCommandProcessor,
			TransferCommandProcessor transferCommandProcessor, InvalidCommandStorage invalidCommandStorage) {
		this.createValidator = createValidator;
		this.depositValidator = depositValidator;
		this.passValidator = passValidator;
		this.withdrawValidator = withdrawValidator;
		this.transferValidator = transferValidator;
		this.createCommandProcessor = createCommandProcessor;
		this.depositCommandProcessor = depositCommandProcessor;
		this.passCommandProcessor = passCommandProcessor;
		this.withdrawCommandProcessor = withdrawCommandProcessor;
		this.transferCommandProcessor = transferCommandProcessor;
		this.invalidCommandStorage = invalidCommandStorage;
	}

	public List<String> start(List<String> input) {
		for (String command : input) {
			if (createValidator.commandIsValid(command)) {
				createCommandProcessor.createAccount(command);
			} else if (depositValidator.commandIsValid(command)) {
				depositCommandProcessor.depositIntoAccount(command);
			} else if (passValidator.commandIsValid(command)) {
				passCommandProcessor.passTime(command);
			} else if (withdrawValidator.commandIsValid(command)) {
				withdrawCommandProcessor.withdrawFromAccount(command);
			} else if (transferValidator.commandIsValid(command)) {
				transferCommandProcessor.transfer(command);
			} else {
				invalidCommandStorage.addCommand(command);
			}
		}
		return invalidCommandStorage.getCommands();
	}
}
