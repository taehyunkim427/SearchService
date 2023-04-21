package search.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Value("${swagger.api.name}")
    private String API_NAME;
    @Value("${swagger.api.version}")
    private String API_VERSION;
    @Value("${swagger.api.description}")
    private String API_DESCRIPTION;

    @Bean
    public OpenAPI openAPI() {

        Info info = new Info()
                .version(API_VERSION)
                .title(API_NAME)
                .description(API_DESCRIPTION);

        return new OpenAPI()
                .info(info);
    }
}