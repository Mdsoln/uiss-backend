package com.uiss.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springdoc.core.configuration.SpringDocDataRestConfiguration;
import org.springframework.context.annotation.Profile;

@Configuration
//@Profile({"dev", "prod"})
@Import(SpringDocDataRestConfiguration.class)
public class SwaggerConfig {

    @Bean
    public OpenAPI customerOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("UDSM ICT STUDENTS' SOCIETY(UISS)")
                        .version("API Version v1")
                        .description("UISS REST API ONLINE DOCUMENTATION")
                        .contact(new Contact().name("Muddy Ramadhan").email("muddyfakih98@gmail.com"))
                );
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public-apis")
                .pathsToMatch("/api/v1/**")
                .build();
    }

}
