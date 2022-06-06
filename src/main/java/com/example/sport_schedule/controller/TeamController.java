package com.example.sport_schedule.controller;

import com.example.sport_schedule.entity.Team;
import com.example.sport_schedule.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/manage/teams")
@AllArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @GetMapping
    public String showAllTeams(Model model) {
        model.addAttribute("teams", teamService.getAll());
        return "teams";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("team", teamService.getById(id));
        return "edit-team";
    }

    @GetMapping("/new-team")
    public String showNewScheduleForm(Team team) {
        return "add-team";
    }

    @PostMapping("/add-team")
    public String addTeam(@Valid Team team, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-team";
        }
        teamService.saveTeam(teamService.getNotTransientInstance(team));
        return "redirect:/manage/teams";
    }

    @PostMapping("/update/{id}")
    public String updateTeam(@PathVariable("id") Long id,
                             @Valid Team team,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit-team";
        }
        teamService.saveTeam(teamService.getNotTransientInstance(team));
        return "redirect:/manage/teams";
    }

    @GetMapping("/delete/{id}")
    public String deleteTeam(@PathVariable("id") Long id, Model model) {
        teamService.deleteTeam(teamService.getById(id));
        return "redirect:/manage/teams";
    }
}
