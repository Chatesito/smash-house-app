package com.house.smash.smash_house.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Datos del perfil del usuario")
public class ProfileResponse {

    @Schema(description = "Nickname del usuario", example = "dacrusoft")
    private String nickname;

    @Schema(description = "Nombre completo del usuario", example = "Diego Carvajal")
    private String name;

    @Schema(description = "Email del usuario", example = "dieguitocarrr@example.com")
    private String email;
}
