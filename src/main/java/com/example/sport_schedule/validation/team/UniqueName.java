package com.example.sport_schedule.validation.team;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UniqueNameValidator.class)
public @interface UniqueName {
    String message() default "Team with that name already exist!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
