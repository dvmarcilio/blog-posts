package command.solution.refactored;

public class StringValueValidator implements ConfigValueValidator {

	@Override
	public void validate(String value) throws RuntimeException {
		validateIfValueIsNotEmpty(value);
	}

	private void validateIfValueIsNotEmpty(String value) {
		if (value.isEmpty())
			throw new RuntimeException("should not have an empty value.");
	}

}
