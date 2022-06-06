package com.example.sport_schedule.validation.game;

import com.example.sport_schedule.entity.Game;
import com.example.sport_schedule.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class DateValidator implements ConstraintValidator<DateValidation, Game> {

    @Autowired
    GameService gameService;

    @Override
    public void initialize(DateValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Game game, ConstraintValidatorContext constraintValidatorContext) {
        if (game.getId() != null && game.getDate() != null) {
            if (game.getDate().isBefore(LocalDateTime.now()))
                return gameService.getById(game.getId()).getDate().isEqual(game.getDate());
            return game.getDate().isAfter(LocalDateTime.now());
        }
        return false;
    }
}
