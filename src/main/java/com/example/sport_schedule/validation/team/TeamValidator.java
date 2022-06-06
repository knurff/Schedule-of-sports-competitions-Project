package com.example.sport_schedule.validation.team;

import com.example.sport_schedule.entity.Team;
import com.example.sport_schedule.service.TeamService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@NoArgsConstructor
public class TeamValidator implements ConstraintValidator<TeamValidation, Team> {

    @Autowired
    private TeamService teamService;

    @Override
    public void initialize(TeamValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Team team, ConstraintValidatorContext cxt) {
        return teamService.teamExist(team);
    }
}
