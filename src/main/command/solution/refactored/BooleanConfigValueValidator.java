package command.solution.refactored;

public class BooleanConfigValueValidator extends ConfigValueValidator {

	public void doValidate(String value) throws RuntimeException {
		validateIfValidBoolean(value);
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
