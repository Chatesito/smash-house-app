package com.house.smash.smash_house.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO para actualizar estadísticas del ranking de un jugador")
public class PlayerRankingUpdateDTO {

    @Schema(description = "Número total de torneos jugados", example = "10")
    private Integer tournamentsPlayed;

    @Schema(description = "Número de torneos ganados", example = "3")
    private Integer tournamentsWon;

    @Schema(description = "Número de partidas ganadas", example = "25")
    private Integer matchesWon;

    @Schema(description = "Número de partidas perdidas", example = "15")
    private Integer matchesLost;

    @Schema(description = "Personaje principal del jugador", example = "Mario")
    private String mainCharacter;

    @Schema(description = "Personaje secundario del jugador", example = "Luigi")
    private String secondaryCharacter;

    @Schema(description = "Logros destacados del jugador", example = "Campeón del torneo mensual de marzo 2024")
    private String achievements;
}
