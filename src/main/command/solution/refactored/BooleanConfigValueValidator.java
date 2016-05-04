package command.solution.refactored;

public class BooleanConfigValueValidator implements ConfigValueValidator {

	@Override
	public void validate(String value) throws RuntimeException {
		validateIfValueIsNotEmpty(value);
		validateIfValidBoolean(value);
	}

	private void validateIfValueIsNotEmpty(String value) {
		if (value.isEmpty())
			throw new RuntimeException("should not have an empty value.");
	}

	private void validateIfValidBoolean(String value) {
		if (!isStringValidBoolean(value)) {
			throw new RuntimeException(
					"should have 'true' or 'false' as value.");
		}
	}

	private boolean isStringValidBoolean(String value) {
		return value.equalsIgnoreCase("TRUE")
				|| value.equalsIgnoreCase("FALSE");
	}

}
