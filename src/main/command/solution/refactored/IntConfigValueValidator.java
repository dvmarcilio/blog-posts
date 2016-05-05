package command.solution.refactored;

public class IntConfigValueValidator extends ConfigValueValidator {

	@Override
	public void doValidate(String value) throws RuntimeException {
		validateIfIsAnInt(value);
		validateIfValueIsGreaterThanZero(value);
	}

	private void validateIfIsAnInt(String value) {
		try {
			Integer.parseInt(value);
		} catch (NumberFormatException e) {
			throw new RuntimeException("should have an integer value.");
		}
	}

	private void validateIfValueIsGreaterThanZero(String value) {
		Integer intVal = Integer.parseInt(value);
		if (intVal <= 0)
			throw new RuntimeException(
					"should have a value greater than zero.");
	}

}
