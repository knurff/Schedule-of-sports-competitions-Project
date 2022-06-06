package com.example.sport_schedule.repository;

import com.example.sport_schedule.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Team getByName(String name);

    boolean existsTeamByName(String name);
}
