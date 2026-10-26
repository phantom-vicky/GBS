package com.isl.gbm.cbs.integration.validator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Constraint(validatedBy = SpecialCharacterValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SpecialCharacterValidation {
    String message() default "Invalid characters found";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
