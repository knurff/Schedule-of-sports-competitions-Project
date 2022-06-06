package com.example.sport_schedule.service;

import com.example.sport_schedule.entity.Team;
import com.example.sport_schedule.exceptions.custom.TeamNotFoundException;
import com.example.sport_schedule.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    public List<Team> getAll() {
        return teamRepository.findAll().stream()
                .sorted(Comparator.comparing(Team::getName))
                .toList();
    }

    public Team getById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException(id));
    }

    public void saveTeam(Team team) {
        teamRepository.save(team);
    }

    public void deleteTeam(Team team) {
        teamRepository.delete(team);
    }

    public Team getByName(String name) {
        return teamRepository.getByName(name);
    }

    public boolean teamExist(Team team) {
        return team != null && teamRepository.existsTeamByName(team.getName());
    }

    public Team getNotTransientInstance(Team team) {
        return Team.builder()
                .id(team.getId())
                .name(team.getName())
                .city(team.getCity())
                .country(team.getCountry())
                .build();
    }
}
