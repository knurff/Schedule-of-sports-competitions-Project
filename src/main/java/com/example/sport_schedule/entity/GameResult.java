package com.example.sport_schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.Min;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameResult {

    @Min(value = 0, message = "Team score must be positive value")
    protected Integer firstTeamScore;

    @Min(value = 0, message = "Team score must be positive value")
    protected Integer secondTeamScore;
}

