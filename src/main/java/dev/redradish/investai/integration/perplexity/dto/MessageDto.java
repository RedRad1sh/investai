package dev.redradish.investai.integration.perplexity.dto;

import lombok.Builder;

/**
 * @author nkucherenko
 */
@Builder
public record MessageDto(String role, String content) {
}
