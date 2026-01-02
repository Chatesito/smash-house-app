package com.house.smash.smash_house.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "DTO para crear un nuevo ranking de jugador")
public class PlayerRankingCreateDTO {

    @NotBlank(message = "El nickname es obligatorio")
    @Schema(description = "Nickname del jugador", example = "SmashMaster", required = true)
    private String nickname;

    @NotBlank(message = "El personaje principal es obligatorio")
    @Schema(description = "Personaje principal del jugador", example = "Mario", required = true)
    private String mainCharacter;

    @Schema(description = "Personaje secundario del jugador", example = "Luigi")
    private String secondaryCharacter;
}
