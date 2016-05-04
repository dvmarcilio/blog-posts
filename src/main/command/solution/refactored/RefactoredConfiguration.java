package command.solution.refactored;

import java.util.Arrays;
import java.util.Collection;

public enum RefactoredConfiguration {

	LOG_LOGIN_FAILS(new BooleanConfigValueValidator()),
	INSTITUTION_NAME(new StringValueValidator()),
	TASKS_RETRY_INTERVAL(new IntConfigValueValidator());

	private ConfigValueValidator validator;

	private RefactoredConfiguration(ConfigValueValidator validator) {
		this.validator = validator;
	}
	
	public ConfigValueValidator getValidator() {
		return validator;
	}

	public static Collection<RefactoredConfiguration> getAll() {
		return Arrays.asList(RefactoredConfiguration.values());
	}

}
