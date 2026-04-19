# 🎮 Smash House App

> Plataforma web centralizada para la gestión de rankings y eventos de la comunidad de Super Smash Bros. Ultimate en Neiva.

![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.5-6DB33F?style=flat-square&logo=spring)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-336791?style=flat-square&logo=postgresql)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?style=flat-square&logo=thymeleaf)

## 📌 El Problema (The Pitch)

La comunidad local de Super Smash Bros. Ultimate carecía de una plataforma oficial y distintiva en internet para centralizar su actividad. La gestión de torneos, el seguimiento de eventos y, sobre todo, el mantenimiento de un **ranking de jugadores** competitivo se realizaba de manera manual y fragmentada. 

**Smash House App** nace para solucionar este problema, ofreciendo un *hub* donde los jugadores pueden visualizar su posición en el ranking, registrarse a eventos y mantener viva la competitividad de la escena local de forma automatizada y transparente.

## 🚀 Funcionalidades Principales

- **Sistema de Ranking Dinámico:** Visualización del ELO y puntaje de los jugadores activos de la comunidad.
- **Gestión de Eventos:** Sección dedicada para anunciar y administrar torneos y encuentros locales.
- **Reportes en PDF:** Exportación de datos críticos (rankings y eventos) utilizando el motor de JasperReports.
- **API Documentada:** Endpoints expuestos y documentados mediante Swagger/OpenAPI.

## 🏗️ Arquitectura y Stack Tecnológico

El proyecto está construido bajo una arquitectura monolítica utilizando el ecosistema de Spring, diseñado para ser robusto y fácilmente escalable en futuras iteraciones.

- **Backend:** Java 21 + Spring Boot 3.3.5.
- **Persistencia:** PostgreSQL gestionado a través de Spring Data JPA (Hibernate).
- **Seguridad:** Implementación de **Spring Security** complementado con **JWT (JSON Web Tokens)** para la autenticación y autorización.
- **Frontend (Server-Side Rendering):** Thymeleaf integrado nativamente con Spring Security para el renderizado condicional de las vistas según los roles de usuario.
- **Documentación de API:** Springdoc OpenAPI (Swagger UI).

> **Decisión de Arquitectura:** Se eligió un modelo MVC renderizado en servidor (Thymeleaf) por la velocidad de desarrollo y el SEO inicial necesario para la web pública. Sin embargo, se incluyó JWT y OpenAPI en el backend previendo una futura migración hacia una arquitectura completamente desacoplada (Frontend en React/Angular y Backend como API REST pura).

## 🛠️ Instalación y Configuración (Setup)

Para ejecutar este proyecto de forma local, es necesario contar con **Java 21**, **Maven** y **PostgreSQL**.

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/Chatesito/smash-house-app.git
   cd smash-house-app
   ```

2. **Configurar la Base de Datos:**
   Crea una base de datos en PostgreSQL llamada `smash_house_db`. Luego, configura tus credenciales locales en el archivo `src/main/resources/application.properties` (o `.yml` si lo modificas):
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/smash_house_db
   spring.datasource.username=tu_usuario_aqui
   spring.datasource.password=tu_contraseña_aqui
   ```
   *Nota de seguridad: Nunca subas contraseñas reales al repositorio público.*

3. **Ejecutar el proyecto:**
   Puedes levantar el servidor utilizando el wrapper de Maven incluido en el proyecto:
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Acceso:**
   - La aplicación estará corriendo en: `http://localhost:8080`
   - La documentación interactiva de la API en: `http://localhost:8080/swagger-ui.html`

---
*Desarrollado por Juan David Rivera Chate como proyecto final de Programación Web.*
