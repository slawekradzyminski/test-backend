package com.awesome.testing;

import com.awesome.testing.dto.User;
import com.awesome.testing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
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
@RequiredArgsConstructor
public class SampleApi {

    private final UserRepository userRepository;

    @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.awesome.testing.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public HeaderFilter headerFilter() {
        return none();
    }

    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            userRepository.save(new User("Slawomir", "Radzyminski", "slawenty", "password"));
            userRepository.save(new User("Gosia", "Nowak", "gosianowak123", "password"));
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SampleApi.class, args);
    }
}
