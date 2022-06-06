package com.example.sport_schedule.validation.game;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = NotSameTeamsValidator.class)
public @interface NotSameTeams {
    String message() default "Team can not play against itself!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
