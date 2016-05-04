package command;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import command.common.ConfigPropertiesValidator;
import command.initial.Configuration;
import command.initial.ConfigurationsPropertiesValidator;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;

@RunWith(JUnitParamsRunner.class)
public class ConfigPropertiesValidatorTest {

	private ConfigPropertiesValidator validator = new ConfigurationsPropertiesValidator();

	private Properties properties;

	@Before
	public void setUp() {
		properties = new Properties();
	}

	@Test
	public void shouldNotThrowExceptionWhenAllConfigsArePresent() {
		properties = PropertiesTestDataHelper.getValid();

		try {
			validator.validate(properties);
		} catch (Throwable e) {
			fail("Should not throw exception with valid values");
		}
	}

	@Test
	@Parameters(source = PropertiesTestDataHelper.class, method = "getAllWithOneKeyMissing")
	@TestCaseName("Missing Key {0}")
	public void shouldThrowExceptionWithRelevantMessageWhenConfigKeyIsMissing(
			Configuration missingKey, Properties properties) {
		try {
			validator.validate(properties);
			fail("Should throw RuntimeException when a Key is not present.");
		} catch (RuntimeException e) {
			assertEquals(getMissingKeyErrorMessage(missingKey), e.getMessage());
		}
	}

	private String getMissingKeyErrorMessage(Configuration missingKey) {
		return "Key " + missingKey + " not found";
	}

	@Test
	@Parameters({ "text", "false", "1.5" })
	@TestCaseName("not an integer value = '{0}'")
	public void shouldThrowExceptionWithRelevantMessageWhenIntValueIsNotInteger(
			String invalidIntValue) {
		Properties p = PropertiesTestDataHelper.getValid();
		p.put(Configuration.TASKS_RETRY_INTERVAL.toString(), invalidIntValue);

		try {
			validator.validate(p);
			fail("Should throw RuntimeException when expected int value is not an integer.");
		} catch (RuntimeException e) {
			assertEquals(getNotAnIntegerMessage(), e.getMessage());
		}
	}

	private String getNotAnIntegerMessage() {
		return "The key " + Configuration.TASKS_RETRY_INTERVAL
				+ " should have an integer value. ";
	}

	@Test
	@Parameters({ "0", "-1", "-10000" })
	@TestCaseName("int value: {0}")
	public void shouldThrowExceptionWithRelevantMessageWhenIntValueIsNotGreaterThanZero(
			String valueNotGreaterThanZero) {
		Properties p = PropertiesTestDataHelper.getValid();
		p.put(Configuration.TASKS_RETRY_INTERVAL.toString(),
				valueNotGreaterThanZero);

		try {
			validator.validate(p);
			fail("Should throw RuntimeException when expected int value is not greater than zero.");
		} catch (RuntimeException e) {
			assertEquals(getIntegerNotGreaterThanZeroMessage(), e.getMessage());
		}
	}

	private String getIntegerNotGreaterThanZeroMessage() {
		return "The key " + Configuration.TASKS_RETRY_INTERVAL
				+ " should have a value greater than zero. ";
	}

	@Test
	@Parameters(source = PropertiesTestDataHelper.class, method = "getAllWithEmptyValue")
	@TestCaseName("Key {0} with empty value")
	public void shouldThrowExceptionWithRelevantMessageWhenConfigKeyHasEmptyValue(
			Configuration keyWithEmptyValue, Properties properties) {
		try {
			validator.validate(properties);
			fail("Should throw RuntimeException when Key has empty value.");
		} catch (RuntimeException e) {
			assertEquals(getEmptyValueMessage(keyWithEmptyValue),
					e.getMessage());
		}
	}

	private String getEmptyValueMessage(Configuration key) {
		return "The key " + key + " should not have an empty value.";
	}

	@Test
	@Parameters({ "truee", "fals", "5", "5.0", "TRU", "FALSY" })

	public void shouldThrowExceptionWithRelevantMessageWhenBooleanIsInvalid(
			String invalidBoolean) {
		Properties p = PropertiesTestDataHelper.getValid();
		p.put(Configuration.LOG_LOGIN_FAILS.toString(), invalidBoolean);

		try {
			validator.validate(p);
			fail("Should throw RuntimeException when expected boolean value is not true/false.");
		} catch (RuntimeException e) {
			assertEquals(getShouldBeTrueOrFalseMessage(), e.getMessage());
		}
	}

	private String getShouldBeTrueOrFalseMessage() {
		return "The key " + Configuration.LOG_LOGIN_FAILS
				+ " should have 'true' or 'false' as value. ";
	}

}
