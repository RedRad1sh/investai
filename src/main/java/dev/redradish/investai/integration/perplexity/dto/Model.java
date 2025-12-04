package dev.redradish.investai.integration.perplexity.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @author nkucherenko
 */
@Getter
public enum Model {
    SONAR_PRO("sonar-pro"),
    SONAR("sonar");

    Model(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    private final String name;
}
