package com.house.smash.smash_house.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa un usuario en el sistema")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del usuario", example = "1")
    private Long id;

    @Column(name = "name", nullable = false)
    @Schema(description = "Nombre completo del usuario", example = "Juan Pérez")
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    @Schema(description = "Correo electrónico del usuario", example = "juan@email.com")
    private String email;

    @Column(name = "nickname", nullable = false, unique = true)
    @Schema(description = "Nombre de usuario único", example = "juanperez123")
    private String nickname;

    @Column(name = "password", nullable = false)
    @Schema(description = "Contraseña del usuario (encriptada)", hidden = true)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Schema(description = "Roles asignados al usuario")
    private Set<Role> roles = new HashSet<>();

    // toString() personalizado
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", roles=" + roles.stream().map(Role::getName).collect(Collectors.toList()) +
                '}';
    }
}