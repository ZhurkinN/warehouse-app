package ru.zhurkin.warehouseapp.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Value("${openapi.title}")
    private String title;

    @Value("${openapi.description}")
    private String description;

    @Value("${openapi.name}")
    private String name;

    @Value("${openapi.email}")
    private String email;

    /*
    Адрес интерфейса - http://localhost:8090/swagger-ui/index.html#
     */
    @Bean
    public OpenAPI getOpenApi() {

        return new OpenAPI()
                .info(new Info()
                        .title(title)
                        .description(description)
                        .contact(new Contact()
                                .name(name)
                                .email(email)));
    }
}
