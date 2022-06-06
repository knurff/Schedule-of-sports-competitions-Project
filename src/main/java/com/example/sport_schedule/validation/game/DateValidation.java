package com.example.sport_schedule.validation.game;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DateValidator.class)
public @interface DateValidation {
    String message() default "Date must be in future!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
