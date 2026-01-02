package com.house.smash.smash_house.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para el registro de nuevos usuarios")
public class RegisterDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Schema(description = "Nombre completo del usuario", example = "Juan Pérez", required = true)
    private String name;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe ser un email válido")
    @Schema(description = "Correo electrónico del usuario", example = "juan@email.com", required = true)
    private String email;

    @NotBlank(message = "El nickname es obligatorio")
    @Size(min = 3, max = 50, message = "El nickname debe tener entre 3 y 50 caracteres")
    @Schema(description = "Nombre de usuario único", example = "juanperez123", required = true, minLength = 3, maxLength = 50)
    private String nickname;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    @Schema(description = "Contraseña del usuario", example = "password123", required = true, minLength = 6)
    private String password;

    @NotBlank(message = "La confirmación de contraseña es obligatoria")
    @Schema(description = "Confirmación de la contraseña", example = "password123", required = true)
    private String confirmPassword;
}
