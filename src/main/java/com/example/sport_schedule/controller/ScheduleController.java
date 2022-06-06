package com.example.sport_schedule.controller;

import com.example.sport_schedule.entity.Game;
import com.example.sport_schedule.entity.User;
import com.example.sport_schedule.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/manage/schedules")
@AllArgsConstructor
public class ScheduleController {
    private final GameService gameService;

    @GetMapping
    public String showAllSchedules(Model model) {
        model.addAttribute("games", gameService.getAll());
        return "schedules";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("game", gameService.getById(id));
        return "edit-schedule";
    }

    @GetMapping("/enter-result/{id}")
    public String showEnterResultForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("game", gameService.getById(id));
        return "enter-result-schedule";
    }

    @GetMapping("/new-schedule")
    public String showNewScheduleForm(Game game) {
        return "add-schedule";
    }

    @PostMapping("/add-schedule")
    public String addGame(@AuthenticationPrincipal User user,
                          @Valid Game game, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-schedule";
        }
        gameService.saveGame(gameService.getNotTransientInstance(game, user));
        return "redirect:/manage/schedules";
    }

    @PostMapping("/update/{id}")
    public String updateGame(@PathVariable("id") Long id,
                             @AuthenticationPrincipal User user,
                             @Valid Game game,
                             BindingResult result, Model model) {
        gameService.checkUpdating(game);
        if (result.hasErrors()) {
            return "edit-schedule";
        }
        gameService.saveGame(gameService.getNotTransientInstance(game, user));
        return "redirect:/manage/schedules";
    }

    @PostMapping("/enter-result/{id}")
    public String enterResult(@PathVariable("id") Long id,
                              @AuthenticationPrincipal User user,
                              @Valid Game game,
                              BindingResult result, Model model) {
        gameService.checkEnteringResult(game);
        if (result.hasErrors()) {
            return "enter-result-schedule";
        }
        gameService.saveGame(gameService.getNotTransientInstance(game, user));
        return "redirect:/manage/schedules";
    }

    @GetMapping("/delete/{id}")
    public String deleteGame(@PathVariable("id") Long id, Model model) {
        gameService.deleteGame(gameService.getById(id));
        return "redirect:/manage/schedules";
    }

}
