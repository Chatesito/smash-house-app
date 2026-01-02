package com.house.smash.smash_house.controller.api;

import com.house.smash.smash_house.model.ApiError;
import com.house.smash.smash_house.model.User;
import com.house.smash.smash_house.security.dto.ProfileResponse;
import com.house.smash.smash_house.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Tag(name = "Profile", description = "Operaciones relacionadas con el perfil de usuario")
@RestController
@RequestMapping("/api")
public class ProfileRestController {

    private final UserService userService;

    public ProfileRestController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Obtener información del perfil",
            description = "Retorna la información básica del perfil del usuario autenticado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Información del perfil obtenida exitosamente",
                    content = @Content(schema = @Schema(implementation = ProfileResponse.class))),
            @ApiResponse(responseCode = "401",
                    description = "No autorizado",
                    content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404",
                    description = "Usuario no encontrado",
                    content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })

    @GetMapping("/profile")
    public ResponseEntity<?> getProfileInfo(
            @Parameter(description = "Principal del usuario autenticado", hidden = true)
            Principal principal) {
        try {
            User user = userService.getUserByNickname(principal.getName())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

            ProfileResponse response = new ProfileResponse(
                    user.getNickname(),
                    user.getName(),
                    user.getEmail()
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body("Error al obtener el perfil: " + e.getMessage());
        }
    }
}