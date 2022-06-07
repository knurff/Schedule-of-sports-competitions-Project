package com.example.sport_schedule.service;

import com.example.sport_schedule.entity.Game;
import com.example.sport_schedule.entity.GameResult;
import com.example.sport_schedule.entity.User;
import com.example.sport_schedule.exceptions.custom.GameNotFoundException;
import com.example.sport_schedule.repository.GameRepository;
import com.example.sport_schedule.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;

    public List<Game> getAll() {
        return gameRepository.findAll().stream()
                .sorted(Comparator.comparing(Game::getDate)
                        .reversed())
                .toList();
    }

    public Game getById(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));
    }

    public List<Game> searchByTeamName(String name) {
        return gameRepository.getGamesByFirstTeamOrSecondTeam
                        (teamRepository.getByName(name), teamRepository.getByName(name)).stream()
                .sorted(Comparator.comparing(Game::getDate)
                        .reversed())
                .toList();
    }

    public void saveGame(Game game) {
        gameRepository.save(game);
    }

    public void deleteGame(Game game) {
        gameRepository.delete(game);
    }

    public Game getNotTransientInstance(Game game, User user) {
        if (game != null && user != null) {
            return Game.builder()
                    .id(game.getId())
                    .firstTeam(teamRepository.getByName(game.getFirstTeam().getName()))
                    .secondTeam(teamRepository.getByName(game.getSecondTeam().getName()))
                    .date(game.getDate())
                    .gameResult(GameResult.builder()
                            .firstTeamScore(game.getGameResult().getFirstTeamScore()
                                    == null ? 0 : game.getGameResult().getFirstTeamScore())
                            .secondTeamScore(game.getGameResult().getSecondTeamScore()
                                    == null ? 0 : game.getGameResult().getSecondTeamScore())
                            .build())
                    .lastInteractionBy(user)
                    .build();
        } else return null;
    }

    public void checkUpdating(Game game) {
        if (game != null) {
            Game gameFromDB = getById(game.getId());
            if (!game.getGameResult().getFirstTeamScore().equals(gameFromDB.getGameResult().getFirstTeamScore())
                    || !game.getGameResult().getSecondTeamScore().equals(gameFromDB.getGameResult().getSecondTeamScore()))
                throw new IllegalStateException("Change of inappropriate parameters!");
        }
    }

    public void checkEnteringResult(Game game) {
        if (game != null) {
            Game gameFromDB = getById(game.getId());
            if (game.getDate().isAfter(LocalDateTime.now())
                    || gameFromDB.getDate().isAfter(LocalDateTime.now()))
                throw new IllegalStateException("Entering result for future game!");
            if (!game.getFirstTeam().getName().equals(gameFromDB.getFirstTeam().getName())
                    || !game.getSecondTeam().getName().equals(gameFromDB.getSecondTeam().getName())
                    || !game.getDate().equals(gameFromDB.getDate()))
                throw new IllegalStateException("Change of inappropriate parameters!");
        }
    }
}
