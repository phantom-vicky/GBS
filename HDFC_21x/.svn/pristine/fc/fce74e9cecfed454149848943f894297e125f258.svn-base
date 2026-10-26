package com.isl.gbm.cbs.integration.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class SpecialCharacterValidator implements ConstraintValidator<SpecialCharacterValidation, String> {
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile("[(){}\\[\\]\"<>;'']");

    @Override
    public void initialize(SpecialCharacterValidation constraintAnnotation) {
        // Initialization code, if needed
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Consider null values as valid. Use @NotNull for null checks.
        }
        return !SPECIAL_CHAR_PATTERN.matcher(value).find();
    }
}

