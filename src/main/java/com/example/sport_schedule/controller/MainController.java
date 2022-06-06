package com.example.sport_schedule.controller;

import com.example.sport_schedule.service.GameService;
import com.example.sport_schedule.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
@AllArgsConstructor
@RequestMapping("/")
public class MainController {
    private final GameService gameService;
    private final UserService userService;

    private String getMainPagePath;

    @GetMapping()
    public String showSchedule(Model model) {
        model.addAttribute("games", gameService.getAll());
        return "index";
    }

    @GetMapping("/search")
    public String searchByTeamName(@RequestParam String name, HttpServletRequest request, Model model) {
        model.addAttribute("games", gameService.searchByTeamName(name));
        if ((userService.isAuthenticated() && request.getHeader("referer").equals(getMainPagePath))
                || !userService.isAuthenticated())
            return "index";
        else return "schedules";
    }

    @GetMapping("/login")
    public String getUserLoginPage() {
        return userService.isAuthenticated() ? "redirect:/manage/schedules" : "login";
    }
}
