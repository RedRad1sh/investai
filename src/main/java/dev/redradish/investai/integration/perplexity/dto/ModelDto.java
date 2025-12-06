package dev.redradish.investai.integration.perplexity.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @author nkucherenko
 */
@Getter
public enum ModelDto {
    SONAR_PRO("sonar-pro"),
    SONAR("sonar");

    ModelDto(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    private final String name;
}
