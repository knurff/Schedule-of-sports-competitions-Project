package com.example.sport_schedule.validation.game;

import com.example.sport_schedule.entity.Game;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotSameTeamsValidator implements ConstraintValidator<NotSameTeams, Game> {

    @Override
    public void initialize(NotSameTeams constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Game game, ConstraintValidatorContext constraintValidatorContext) {
        return game.getFirstTeam().getName().isBlank() && game.getSecondTeam().getName().isBlank() ||
                !game.getFirstTeam().getName().equals(game.getSecondTeam().getName());
    }
}
