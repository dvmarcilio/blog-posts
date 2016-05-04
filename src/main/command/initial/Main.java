package command.initial;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import command.common.ConfigPropertiesValidator;

public class Main {

	private static final String PROPERTIES_FILE_NAME = "config.properties";

	public static void main(String[] args) throws IOException {
		ConfigPropertiesValidator configsValidator = new ConfigurationsPropertiesValidator();
		configsValidator.validate(loadProperties());
	}

	private static Properties loadProperties()
			throws FileNotFoundException, IOException {
		InputStream is = ConfigurationsPropertiesValidator.class
				.getResourceAsStream(PROPERTIES_FILE_NAME);
		Properties properties = new Properties();
		properties.load(is);
		return properties;
	}
}
