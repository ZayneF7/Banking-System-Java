package banking;

import java.util.List;

public class MasterControl {
	private CreateValidator createValidator;
	private DepositValidator depositValidator;
	private CreateCommandProcessor createCommandProcessor;
	private DepositCommandProcessor depositCommandProcessor;
	private InvalidCommandStorage invalidCommandStorage;

	public MasterControl(CreateValidator createValidator, DepositValidator depositValidator,
			CreateCommandProcessor createCommandProcessor, DepositCommandProcessor depositCommandProcessor,
			InvalidCommandStorage invalidCommandStorage) {
		this.createValidator = createValidator;
		this.depositValidator = depositValidator;
		this.createCommandProcessor = createCommandProcessor;
		this.depositCommandProcessor = depositCommandProcessor;
		this.invalidCommandStorage = invalidCommandStorage;
	}

	public List<String> start(List<String> input) {
		for (String command : input) {
			if (createValidator.commandIsValid(command)) {
				createCommandProcessor.createAccount(command);
			} else if (depositValidator.commandIsValid(command)) {
				depositCommandProcessor.depositIntoAccount(command);
			} else {
				invalidCommandStorage.addCommand(command);
			}
		}
		return invalidCommandStorage.getCommands();
	}
}
