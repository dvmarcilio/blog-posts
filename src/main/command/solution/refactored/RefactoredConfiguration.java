package command.solution.refactored;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public enum RefactoredConfiguration {

	LOG_LOGIN_FAILS(Boolean.class),
	INSTITUTION_NAME(String.class),
	TASKS_RETRY_INTERVAL(Integer.class);

	private Class<?> valueType;

	private static Map<Class<?>, ConfigValueValidator> configTypeToValidator = new HashMap<>();

	private RefactoredConfiguration(Class<?> valueType) {
		this.valueType = valueType;
	}

	public static Collection<RefactoredConfiguration> getAll() {
		return Arrays.asList(RefactoredConfiguration.values());
	}

	public ConfigValueValidator getValidator() {
		return fetchValidator(this);
	}

	private static ConfigValueValidator fetchValidator(
			RefactoredConfiguration config) {
		populateMap();
		return configTypeToValidator.get(config.valueType);
	}

	private static void populateMap() {
		if (configTypeToValidator.isEmpty()) {
			configTypeToValidator.put(Integer.class,
					new IntConfigValueValidator());
			configTypeToValidator.put(Boolean.class,
					new BooleanConfigValueValidator());
			configTypeToValidator.put(String.class, new StringValueValidator());
		}
	}

}
