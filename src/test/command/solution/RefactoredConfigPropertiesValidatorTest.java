package command.solution;

import org.junit.runner.RunWith;

import command.common.ConfigPropertiesValidator;
import command.solution.refactored.RefactoredConfigurationsPropertiesValidator;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class RefactoredConfigPropertiesValidatorTest
		extends ConfigPropertiesValidatorTest {

	@Override
	protected ConfigPropertiesValidator createValidatorInstance() {
		return new RefactoredConfigurationsPropertiesValidator();
	}

}
