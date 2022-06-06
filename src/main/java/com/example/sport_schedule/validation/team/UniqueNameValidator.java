package com.example.sport_schedule.validation.team;

import com.example.sport_schedule.entity.Team;
import com.example.sport_schedule.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, Team> {

    @Autowired
    private TeamService teamService;

    @Override
    public void initialize(UniqueName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Team team, ConstraintValidatorContext constraintValidatorContext) {
        if (teamService.getByName(team.getName()) != null) {
            Long id = teamService.getByName(team.getName()).getId();
            if (id != null && id.equals(team.getId())) return true;
        }
        return !teamService.teamExist(team);
    }
}
