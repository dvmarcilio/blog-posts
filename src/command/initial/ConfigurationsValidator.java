package command.initial;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;

public class ConfigurationsValidator {

	private static final String PROPERTIES_FILE_NAME = "config.properties";

	private Properties properties = new Properties();

	public ConfigurationsValidator() throws IOException {
		loadProperties();
	}

	private void loadProperties() throws FileNotFoundException, IOException {
		InputStream is = ConfigurationsValidator.class
				.getResourceAsStream(PROPERTIES_FILE_NAME);
		properties.load(is);
	}

	public void validateConfigs() {
		Collection<Configuration> configs = Configuration.getAll();
		for (Configuration config : configs)
			validateConfig(config);
	}

	private void validateConfig(Configuration config) {
		validateIfKeyIsPresent(config);
		validateConfigValue(config);
	}

	private void validateIfKeyIsPresent(Configuration config) {
		if (getConfigValue(config) == null)
			throw new RuntimeException("Key " + config + " not found");
	}

	private String getConfigValue(Configuration configKey) {
		return properties.getProperty(configKey.toString());
	}

	private void validateConfigValue(Configuration configKey) {
		String value = getConfigValue(configKey);
		validateIfValueIsNotEmpty(configKey, value);
		if (configKey.equals(Configuration.LOG_LOGIN_FAILS)) {
			validateBooleanValue(configKey, value);
		} else if (configKey.equals(Configuration.TASKS_RETRY_INTERVAL)) {
			validateIntValue(configKey, value);
		}
	}

	private void validateIfValueIsNotEmpty(Configuration configKey,
			String value) {
		if (value.isEmpty())
			throw new RuntimeException(
					"The key " + configKey + "should not have an empty value.");
	}

	private void validateIntValue(Configuration configKey, String value) {
		Integer intVal = tryToParseIntValue(configKey, value);
		if (intVal <= 0) {
			throw new RuntimeException("The key " + configKey
					+ " should have a value greater than zero. ");
		}
	}

	private Integer tryToParseIntValue(Configuration configKey, String value) {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			throw new RuntimeException(
					"The key " + configKey + " should have an integer value. ");
		}
	}

	private void validateBooleanValue(Configuration configKey, String value) {
		if (!isStringValidBoolean(value)) {
			throw new RuntimeException("The key " + configKey
					+ " should have 'false' or 'true' as value. ");
		}
	}

	private boolean isStringValidBoolean(String value) {
		return value.equalsIgnoreCase("TRUE")
				|| value.equalsIgnoreCase("FALSE");
	}

}
