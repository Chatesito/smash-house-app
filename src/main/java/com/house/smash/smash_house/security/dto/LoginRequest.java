package com.house.smash.smash_house.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Datos necesarios para la autenticación")
public class LoginRequest {

    @Schema(description = "Nickname del usuario", example = "dacrusoft", required = true)
    private String nickname;

    @Schema(description = "Contraseña del usuario", example = "apruebamediego:(", required = true, format = "password")
    private String password;
}