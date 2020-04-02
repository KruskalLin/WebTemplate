package stg.template.template.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "app")
@Component
public class AppConfig {

    private String jwtSecret;

    private Long jwtExpirationInMs;

    private String frontendPort;
}
