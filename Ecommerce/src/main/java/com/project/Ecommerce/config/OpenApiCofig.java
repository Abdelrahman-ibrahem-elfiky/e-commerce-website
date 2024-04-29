package com.project.Ecommerce.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Service;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Abdelrahman Elfiky",
                        email = "elfekyabdo537@gmail.com"
                ),
                description = "OpenApi Documentation For Ecommerce APP ",
                title = "OpenApi Ecommerce Store",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Rialway app",
                        url = "https://e-commerce-website-production-593a.up.railway.app"
                )
        },
        security = @SecurityRequirement(
                name = "bearerAuth"
        )

)
@SecurityScheme(
            name = "bearerAuth",
            description = "jwt auth description",
            scheme = "bearer",
            type = SecuritySchemeType.HTTP,
            bearerFormat = "jwt",
            in = SecuritySchemeIn.HEADER
)
public class OpenApiCofig {
}
