package com.house.smash.smash_house.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Respuesta que contiene el token JWT y datos del usuario")
public class JwtResponse {

    @Schema(description = "Token JWT generado", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huX2RvZSJ9...")
    private String token;

    @Schema(description = "Tipo de token", example = "Bearer", defaultValue = "Bearer")
    private String type = "Bearer";

    @Schema(description = "Nickname del usuario", example = "dacrusoft")
    private String nickname;

    @Schema(description = "Nombre completo del usuario", example = "Diego Carvajal")
    private String name;

    @Schema(description = "Email del usuario", example = "dieguitocarrr@example.com")
    private String email;

    public JwtResponse(String token, String nickname, String name, String email) {
        this.token = token;
        this.nickname = nickname;
        this.name = name;
        this.email = email;
    }
}
