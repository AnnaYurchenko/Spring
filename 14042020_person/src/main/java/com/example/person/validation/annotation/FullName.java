package com.example.person.validation.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = PersonFullNameValidator.class)
@Documented
public @interface FullName {
    int value();

    String message() default "{com.telran.person.validation.person.full_name}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}