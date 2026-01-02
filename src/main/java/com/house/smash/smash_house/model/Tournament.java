package com.house.smash.smash_house.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tournaments")
@Schema(description = "Entidad que representa un torneo")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del torneo", example = "1")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Nombre del torneo", example = "Torneo Smash Ultimate 2024")
    private String name;

    @Column(columnDefinition = "TEXT")
    @Schema(description = "Descripción detallada del torneo", example = "Gran torneo mensual con premios para los primeros lugares")
    private String description;

    @Column(name = "event_date", nullable = false)
    @Schema(description = "Fecha y hora del evento", example = "2024-12-31T20:00:00")
    private LocalDateTime eventDate;

    @Column(nullable = false)
    @Schema(description = "Ubicación del torneo", example = "Centro de Convenciones XYZ")
    private String location;
}