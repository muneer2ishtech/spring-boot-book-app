package fi.ishtech.common.validation;

import java.time.LocalDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator that ensures a numeric value does not exceed the current calendar year.
 *
 * Supported types are:
 * <ul>
 *   <li>{@code short}</li>
 *   <li>{@code int}</li>
 *   <li>{@code long}</li>
 *   <li>and their respective wrappers</li>
 * </ul>
 * {@code null} elements are considered valid. Use @NotNull additionally if null check required.
 */
public class MaxCurrentYearValidator implements ConstraintValidator<MaxCurrentYear, Number> {

	@Override
	public boolean isValid(final Number value, ConstraintValidatorContext context) {
		return value == null || value.intValue() <= LocalDate.now().getYear();
	}

}