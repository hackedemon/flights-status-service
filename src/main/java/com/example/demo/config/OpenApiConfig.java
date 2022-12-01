package com.example.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition(
        info = @Info(
                title = "Flights Status service Api",
                description = """
                        <link rel="import" href="https://www.google.com">
                        """,
                version = "v1",
                contact = @Contact(
                        name = "Chirag Bhatia",
                        email = "placeholder@email.com")),
        servers = @Server(url = ""),
        tags = {@Tag(name = "Public"), @Tag(name = "Admin")}
)
@SecurityScheme(
        name = "authenticationToken",
        description = "Enter api access token below",
        scheme = "apiToken",
        type = SecuritySchemeType.APIKEY,
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
