package dev.redradish.investai.integration.perplexity.dto;

import lombok.Builder;

import java.util.List;

/**
 * @author nkucherenko
 */
@Builder
public record QueryDto(Model model, String query, List<MessageDto> messages) {
}
