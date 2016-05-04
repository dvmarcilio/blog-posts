package command.initial;

import java.util.Arrays;
import java.util.Collection;

public enum Configuration {

	LOG_LOGIN_FAILS,
	INSTITUTION_NAME,
	TASKS_RETRY_INTERVAL;
	
	public static Collection<Configuration> getAll() {
		return Arrays.asList(Configuration.values());
	}
	
}
