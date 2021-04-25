package com.awesome.testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.zalando.logbook.HeaderFilter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static org.zalando.logbook.HeaderFilter.none;

@EnableSwagger2
@SpringBootApplication
public class SampleApi {

    @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.awesome.testing.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public HeaderFilter headerFilter() {
        return none();
    }

    public static void main(String[] args) {
        SpringApplication.run(SampleApi.class, args);
    }
}
