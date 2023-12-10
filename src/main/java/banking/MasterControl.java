package banking;

import java.util.List;

public class MasterControl {
	private CreateValidator createValidator;
	private DepositValidator depositValidator;
	private PassValidator passValidator;
	private WithdrawValidator withdrawValidator;
	private CreateCommandProcessor createCommandProcessor;
	private DepositCommandProcessor depositCommandProcessor;
	private PassCommandProcessor passCommandProcessor;
	private WithdrawCommandProcessor withdrawCommandProcessor;
	private InvalidCommandStorage invalidCommandStorage;

	public MasterControl(CreateValidator createValidator, DepositValidator depositValidator,
			PassValidator passValidator, WithdrawValidator withdrawValidator,
			CreateCommandProcessor createCommandProcessor, DepositCommandProcessor depositCommandProcessor,
			PassCommandProcessor passCommandProcessor, WithdrawCommandProcessor withdrawCommandProcessor,
			InvalidCommandStorage invalidCommandStorage) {
		this.createValidator = createValidator;
		this.depositValidator = depositValidator;
		this.passValidator = passValidator;
		this.withdrawValidator = withdrawValidator;
		this.createCommandProcessor = createCommandProcessor;
		this.depositCommandProcessor = depositCommandProcessor;
		this.passCommandProcessor = passCommandProcessor;
		this.withdrawCommandProcessor = withdrawCommandProcessor;
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
			} else {
				invalidCommandStorage.addCommand(command);
			}
		}
		return invalidCommandStorage.getCommands();
	}
}
