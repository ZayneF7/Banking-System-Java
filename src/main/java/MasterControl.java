import java.util.List;

public class MasterControl {
	private CreateValidator createValidator;
	private DepositValidator depositValidator;
	private CommandProcessor commandProcessor;
	private CommandStorage commandStorage;

	public MasterControl(CreateValidator createValidator, DepositValidator depositValidator,
			CommandProcessor commandProcessor, CommandStorage commandStorage) {
		this.createValidator = createValidator;
		this.depositValidator = depositValidator;
		this.commandProcessor = commandProcessor;
		this.commandStorage = commandStorage;
	}

	public List<String> start(List<String> input) {
		for (String command : input) {
			if (createValidator.commandIsValid(command)) {
				commandProcessor.createAccount(command);
			} else if (depositValidator.commandIsValid(command)) {
				commandProcessor.depositIntoAccount(command);
			} else {
				commandStorage.addCommand(command);
			}
		}
		return commandStorage.getCommands();
	}
}
