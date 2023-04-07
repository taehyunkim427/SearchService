package search.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "OPTIONS")
                .allowedHeaders("Authorization", "Origin", "X-Requested-With", "Content-Type", "Accept")
                .maxAge(3600);
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        MediaType applicationJsonUtf8 = new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8);
        configurer.defaultContentType(applicationJsonUtf8)
                .favorPathExtension(false)
                .favorParameter(false);
    }
}
