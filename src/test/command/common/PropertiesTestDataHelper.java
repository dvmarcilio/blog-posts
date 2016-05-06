package command.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import command.solution.initial.Configuration;
import command.solution.refactored.RefactoredConfiguration;

/**
 * Only add {@link Configuration}s to the Properties returned. This will work as
 * long as {@link RefactoredConfiguration}'s enum values are the same as the
 * enum values from {@link Configuration}. For every {@link Configuration} add
 * one to {@link RefactoredConfiguration} with the same name.That's because the
 * Properties' key lookup is made by the name of the enum.
 *
 */
public class PropertiesTestDataHelper {

	private static final String BOOLEAN_VALID_VALUE = "false";
	private static final String INT_VALID_VALUE = "3";
	private static final String STRING_VALID_VALUE = "string";

	public static Properties getValid() {
		Properties properties = new Properties();
		Map<Configuration, String> configsToValue = configurationsValuesToBeAdded();
		for (Map.Entry<Configuration, String> configToValue : configsToValue
				.entrySet())
			properties.setProperty(configToValue.getKey().toString(),
					configToValue.getValue());
		return properties;
	}

	// XXX If you add a key, add it to the map.
	// If you don't some tests will fail accordingly.
	private static Map<Configuration, String> configurationsValuesToBeAdded() {
		Map<Configuration, String> configsToValue = new HashMap<>();
		configsToValue.put(Configuration.INSTITUTION_NAME, STRING_VALID_VALUE);
		configsToValue.put(Configuration.TASKS_RETRY_INTERVAL, INT_VALID_VALUE);
		configsToValue.put(Configuration.LOG_LOGIN_FAILS, BOOLEAN_VALID_VALUE);
		return configsToValue;
	}

	public static Collection<Configuration> configurationsAdded() {
		return configurationsValuesToBeAdded().keySet();
	}

	public static Object[] getAllWithOneKeyMissing() {
		Map<Configuration, Properties> missingKeyToProperties = mapMissingKeyToProperties();
		return propertiesByKeyMapToObjectArray(missingKeyToProperties);
	}

	private static Map<Configuration, Properties> mapMissingKeyToProperties() {
		Map<Configuration, Properties> missingKeyToProperties = new HashMap<>(
				3);
		for (Configuration key : Configuration.getAll()) {
			Properties properties = getValid();
			properties.remove(key.toString());
			missingKeyToProperties.put(key, properties);
		}
		return missingKeyToProperties;
	}

	private static Object[] propertiesByKeyMapToObjectArray(
			Map<Configuration, Properties> propertiesByKeyMap) {
		List<Object[]> params = new ArrayList<>(3);
		propertiesByKeyMap.forEach((k, v) -> params.add(new Object[] { k, v }));
		return params.toArray();
	}

	public static Object[] getAllWithEmptyValue() {
		Map<Configuration, Properties> keyWithEmptyValueToProperties = mapKeyWithEmptyValueToProperties();
		return propertiesByKeyMapToObjectArray(keyWithEmptyValueToProperties);
	}

	private static Map<Configuration, Properties> mapKeyWithEmptyValueToProperties() {
		Map<Configuration, Properties> emptyValueKeyToProperties = new HashMap<>(
				3);
		for (Configuration key : Configuration.getAll()) {
			Properties properties = getValid();
			properties.put(key.toString(), "");
			emptyValueKeyToProperties.put(key, properties);
		}
		return emptyValueKeyToProperties;
	}

}
