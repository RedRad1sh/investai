package dev.redradish.investai.integration.perplexity.dto;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.util.List;

/**
 * @author nkucherenko
 */
public record CompleteAnswerDto(
    @JsonPropertyDescription("Действия, которые будут совершены над портфелем")
    List<TinkoffCommandDto> commands,
    @JsonPropertyDescription("Общее объяснение совершаемых действий")
    String reasoning
) {}
