package dev.redradish.investai.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author nkucherenko
 */
@Getter
@Setter
@Component
@ConfigurationProperties("domain")
public class UrlFilterProperties {
    List<String> filter;
}
