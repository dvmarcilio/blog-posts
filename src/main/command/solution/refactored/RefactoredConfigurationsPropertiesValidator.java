package command.solution.refactored;

import java.util.Collection;
import java.util.Properties;

import command.common.ConfigPropertiesValidator;

public class RefactoredConfigurationsPropertiesValidator
		implements ConfigPropertiesValidator {

	private Properties properties;

	@Override
	public void validate(Properties properties) {
		this.properties = properties;
		Collection<RefactoredConfiguration> configs = RefactoredConfiguration
				.getAll();
		for (RefactoredConfiguration config : configs)
			validateConfig(config);
	}

	private void validateConfig(RefactoredConfiguration configKey) {
		validateIfKeyIsPresent(configKey);
		validateConfigValue(configKey);
	}

	private void validateIfKeyIsPresent(RefactoredConfiguration configKey) {
		if (getConfigValue(configKey) == null)
			throw new RuntimeException("Key " + configKey + " not found");
	}

	private String getConfigValue(RefactoredConfiguration configKey) {
		return properties.getProperty(configKey.toString());
	}

	private void validateConfigValue(RefactoredConfiguration configKey) {
		try {
			doValidateConfigValue(configKey);
		} catch (RuntimeException e) {
			throw new RuntimeException(
					"The key " + configKey + " " + e.getMessage());
		}
	}

	private void doValidateConfigValue(RefactoredConfiguration configKey) {
		String value = getConfigValue(configKey);
		ConfigValueValidator validator = configKey.getValidator();
		validator.validate(value);
	}

}
