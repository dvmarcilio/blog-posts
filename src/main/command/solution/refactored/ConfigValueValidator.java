package command.solution.refactored;

public abstract class ConfigValueValidator {

	public void validate(String value) throws RuntimeException {
		validateIfValueIsNotEmpty(value);
		doValidate(value);
	}

	private void validateIfValueIsNotEmpty(String value) {
		if (value.isEmpty())
			throw new RuntimeException("should not have an empty value.");
	}

	protected abstract void doValidate(String value);

}
