package com.example.customer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

//@Configuration
//@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
        registry.addResourceHandler("/api/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metaData())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo metaData() {

        return new ApiInfo(
                "Simple Account and Transfers Service",
                "API documentation",
                "0.0.1",
                "",
                new Contact("Yasir Shabbir", "https://github.com/yasirshabbir44", "yasirshabbir44@gmail.com"),
                "",
                "",
                Collections.emptyList());
    }


}
