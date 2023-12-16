package com.vivafattura.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class AdultAgeValidator implements ConstraintValidator<AdultAge, LocalDate> {


    /**
     * Implements the validation logic.
     * The state of {@code value} must not be altered.
     * <p>
     * This method can be accessed concurrently, thread-safety must be ensured
     * by the implementation.
     *
     * @param dateOfBirth   object to validate
     * @param context context in which the constraint is evaluated
     * @return {@code false} if {@code value} does not pass the constraint
     */
    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext context) {

        if (dateOfBirth == null) {
            return false;
        }

        // Verifica se l'età è almeno 18 anni
        LocalDate currentDate = LocalDate.now();
        Period age = Period.between(dateOfBirth, currentDate);
        if (age.getYears() < 18) {
            return false;
        }

        // Verifica la formattazione "yyyy-MM-dd"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateOfBirth = dateOfBirth.format(formatter);

        if(!formattedDateOfBirth.equals(dateOfBirth.toString())){
            return false;
        }

        return formattedDateOfBirth.equals(dateOfBirth.toString());
    }

}
