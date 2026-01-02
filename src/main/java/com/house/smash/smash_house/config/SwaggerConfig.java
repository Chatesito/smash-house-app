package com.house.smash.smash_house.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("admin")
                .pathsToMatch("/admin/**")
                .addOpenApiCustomizer(openApi -> openApi.info(
                        new io.swagger.v3.oas.models.info.Info()
                                .title("API Administrativa")
                                .description("Endpoints exclusivos para administradores")
                                .version("1.0")
                ))
                .build();
    }
}
