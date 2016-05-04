package command.initial;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		ConfigurationsValidator configsValidator = new ConfigurationsValidator();
		configsValidator.validateConfigs();
	}
}
