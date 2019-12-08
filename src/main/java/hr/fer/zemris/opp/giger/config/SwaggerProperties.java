package hr.fer.zemris.opp.giger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Profile({"local"})
@Configuration
@EnableSwagger2
public class SwaggerProperties {
    @Bean
    public Docket api_giger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("GIGER")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/**"))
                .build();
    }
}