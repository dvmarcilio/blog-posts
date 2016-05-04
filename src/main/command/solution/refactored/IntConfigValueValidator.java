package command.solution.refactored;

public class IntConfigValueValidator implements ConfigValueValidator {

	@Override
	public void validate(String value) throws RuntimeException {
		validateIfValueIsNotEmpty(value);
		validateIfIsAnInt(value);
		validateIfValueIsGreaterThanZero(value);
	}

	private void validateIfValueIsNotEmpty(String value) {
		if (value.isEmpty())
			throw new RuntimeException("should not have an empty value.");
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
