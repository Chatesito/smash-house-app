package com.house.smash.smash_house.controller;

import com.house.smash.smash_house.model.Tournament;
import com.house.smash.smash_house.service.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/tournament")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminTournamentController {

    private final TournamentService tournamentService;

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("tournament", new Tournament());
        return "admin/tournament-create";
    }

    @PostMapping("/create")
    public String createTournament(@ModelAttribute Tournament tournament,
                                   RedirectAttributes redirectAttributes) {
        try {
            tournamentService.createTournament(tournament);
            redirectAttributes.addFlashAttribute("success", "Torneo creado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al crear el torneo");
        }
        return "redirect:/tournaments";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("tournament", tournamentService.getTournamentById(id));
        return "admin/tournament-details-admin";
    }

    @PostMapping("/update/{id}")
    public String updateTournament(@PathVariable Long id,
                                   @ModelAttribute Tournament tournament,
                                   RedirectAttributes redirectAttributes) {
        try {
            tournamentService.updateTournament(id, tournament);
            redirectAttributes.addFlashAttribute("success", "Torneo actualizado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el torneo");
        }
        return "redirect:/tournaments";
    }

    @PostMapping("/delete/{id}")
    public String deleteTournament(@PathVariable Long id,
                                   RedirectAttributes redirectAttributes) {
        try {
            Tournament tournament = tournamentService.getTournamentById(id);
            tournamentService.deleteTournament(id);
            redirectAttributes.addFlashAttribute("success",
                    "El torneo " + tournament.getName() + " ha sido eliminado.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el torneo");
        }
        return "redirect:/tournaments";
    }
}
