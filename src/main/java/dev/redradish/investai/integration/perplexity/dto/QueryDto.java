package dev.redradish.investai.integration.perplexity.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;

import java.util.List;

/**
 * @author nkucherenko
 */
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record QueryDto(ModelDto model, String query, List<MessageDto> messages, List<String> searchDomainFilter) {
}
