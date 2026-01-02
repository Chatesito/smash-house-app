package com.house.smash.smash_house.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Estructura estándar para respuestas de error")
public class ApiError {

    @Schema(description = "Mensaje descriptivo del error",
            example = "No se encontró el usuario solicitado")
    private String message;

    @Schema(description = "Código de estado HTTP",
            example = "404")
    private int statusCode;

    @Schema(description = "Momento en que ocurrió el error",
            example = "2024-03-18T10:30:00Z")
    private LocalDateTime timestamp;

    public ApiError(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now();
    }
}
