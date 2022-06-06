package com.example.sport_schedule.repository;

import com.example.sport_schedule.entity.Game;
import com.example.sport_schedule.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> getGamesByFirstTeamOrSecondTeam(Team firstTeam, Team secondTeam);
}
