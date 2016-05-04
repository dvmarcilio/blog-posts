package command.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import command.solution.initial.Configuration;

public class PropertiesTestDataHelper {

	private static final String LOG_VALID_VALUE = "false";
	private static final String TASKS_VALID_VALUE = "3";
	private static final String INSTITUTION_VALID_VALUE = "inst";

	public static Properties getValid() {
		Properties properties = new Properties();
		properties.setProperty(Configuration.INSTITUTION_NAME.toString(),
				INSTITUTION_VALID_VALUE);
		properties.setProperty(Configuration.TASKS_RETRY_INTERVAL.toString(),
				TASKS_VALID_VALUE);
		properties.setProperty(Configuration.LOG_LOGIN_FAILS.toString(),
				LOG_VALID_VALUE);
		return properties;
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
