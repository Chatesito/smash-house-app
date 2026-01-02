package com.house.smash.smash_house.controller;

import com.house.smash.smash_house.model.PlayerRanking;
import com.house.smash.smash_house.model.dto.PlayerRankingCreateDTO;
import com.house.smash.smash_house.model.dto.PlayerRankingUpdateDTO;
import com.house.smash.smash_house.service.PlayerRankingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/admin")
@Tag(name = "Admin Ranking", description = "Operaciones administrativas para el ranking de jugadores")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminRankingController {

    private final PlayerRankingService rankingService;

    @GetMapping("/player/{id}")
    public String showPlayerAdmin(@PathVariable Long id, Model model) {
        model.addAttribute("player", rankingService.getPlayerById(id));
        return "admin/player-details-admin";
    }


    @Operation(summary = "Actualizar estadísticas de jugador",
            description = "Actualiza las estadísticas de un jugador existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jugador actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Jugador no encontrado")
    })

    @PostMapping("/player/{id}/update")
    public String updatePlayer(
            @Parameter(description = "ID del jugador", required = true)
            @PathVariable Long id,
            @Parameter(description = "Datos de actualización del jugador", required = true)
            @ModelAttribute PlayerRankingUpdateDTO updateDTO,
            RedirectAttributes redirectAttributes) {
        try {
            // Validar que los torneos ganados no excedan los jugados
            if (updateDTO.getTournamentsWon() > updateDTO.getTournamentsPlayed()) {
                redirectAttributes.addFlashAttribute("error",
                        "Los torneos ganados no pueden ser más que los torneos jugados");
                return "redirect:/admin/player/" + id;
            }

            rankingService.updatePlayerStats(id, updateDTO);
            redirectAttributes.addFlashAttribute("success", "Jugador actualizado correctamente");
            return "redirect:/ranking";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el jugador");
            return "redirect:/admin/player/" + id;
        }
    }

    @GetMapping("/player/new")
    public String showCreateForm(Model model) {
        model.addAttribute("playerDTO", new PlayerRankingCreateDTO());
        return "admin/player-create";
    }


    @Operation(summary = "Crear nuevo jugador",
            description = "Crea un nuevo jugador en el ranking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jugador creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })

    @PostMapping("/player/create")
    public String createPlayer(
            @Parameter(description = "Datos del nuevo jugador", required = true)
            @ModelAttribute PlayerRankingCreateDTO playerDTO,
            RedirectAttributes redirectAttributes) {
        try {
            rankingService.createPlayer(playerDTO);
            redirectAttributes.addFlashAttribute("success", "Jugador creado correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al crear el jugador");
        }
        return "redirect:/ranking";
    }

    @PostMapping("/player/{id}/delete")
    public String deletePlayer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            PlayerRanking player = rankingService.getPlayerById(id);
            String nickname = player.getNickname();
            rankingService.deletePlayer(id);
            redirectAttributes.addFlashAttribute("success",
                    "El jugador " + nickname + " ha sido eliminado del ranking.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "Hubo un error al intentar eliminar al jugador.");
        }
        return "redirect:/ranking";
    }
}
