package com.vivafattura.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AdultAgeValidator.class)
@Documented
public @interface AdultAge {
    String message() default "Il cliente deve avere almeno 18 anni";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
