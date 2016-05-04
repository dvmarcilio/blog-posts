package command.solution;

import org.junit.runner.RunWith;

import command.common.ConfigPropertiesValidator;
import command.solution.initial.ConfigurationsPropertiesValidator;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class InitialConfigPropertiesValidatorTest extends ConfigPropertiesValidatorTest {

	@Override
	protected ConfigPropertiesValidator createValidatorInstance() {
		return new ConfigurationsPropertiesValidator();
	}

}
