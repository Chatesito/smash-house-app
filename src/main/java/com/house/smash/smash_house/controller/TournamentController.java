package com.house.smash.smash_house.controller;

import com.house.smash.smash_house.service.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class TournamentController {

    private final TournamentService tournamentService;

    @GetMapping("/tournaments")
    public String showTournaments(Model model) {
        model.addAttribute("upcomingTournaments", tournamentService.getUpcomingTournaments());
        model.addAttribute("pastTournaments", tournamentService.getPastTournaments());
        return "tournaments";
    }

    @GetMapping("/tournament/{id}")
    public String showTournamentDetails(@PathVariable Long id, Model model) {
        model.addAttribute("tournament", tournamentService.getTournamentById(id));
        return "tournament-details";
    }
}

