package com.example.sport_schedule.validation.team;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = TeamValidator.class)
public @interface TeamValidation {
    String message() default "Team does not exist!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
