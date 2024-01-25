package com.example.shopdemo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;


@OpenAPIDefinition
@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI baseOpenApi(){
        ApiResponse badRequestAPI = new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("Default",
                                new Example().value("{\"Code\":400,\"Status\": \"Bad Request\",\"Message\":\"Failed Singed In!\"}")))
        );
        ApiResponse internalServerErrorAPI = new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("Default",
                                new Example().value("{\"Code\":500,\"Status\": \"Internal Server Error!\",\"Message\":\"Internal Server Error!\"}")))
        );
        Components components = new Components();
        components.addResponses("badRequestAPI",badRequestAPI);
        components.addResponses("internalServerErrorAPI",internalServerErrorAPI);
        return new OpenAPI().components(components)
                .info(new Info().title("Spring Doc").version("1.0.0").description("Doc"));
    }
}
