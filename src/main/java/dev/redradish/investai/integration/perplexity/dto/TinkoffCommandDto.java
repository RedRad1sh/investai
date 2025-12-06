package dev.redradish.investai.integration.perplexity.dto;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.math.BigDecimal;

/**
 * @author nkucherenko
 */
public record TinkoffCommandDto(
    @JsonPropertyDescription("Конкретный тикер, короткое название конкретной бумаги, по которому ее можно найти. " +
                             "Пример с Тинькоф.Инвестиции: LKOH для лукойла")
    String ticker,
    @JsonPropertyDescription("Существует ли бумага уже в портфеле")
    boolean existsInPortfolio,
    @JsonPropertyDescription("Какое именно действие будет совершено над бумагами")
    ActionDto actionDto,
    @JsonPropertyDescription("Сумма, по которой действие будет совершено действие. Если указано 0, значит по рыночной цене.")
    BigDecimal price,
    @JsonPropertyDescription("Количество бумаг, с которыми будет совершено действие")
    int count,
    @JsonPropertyDescription("Прогнозируемый уровень риска по совершению действия в процентах")
    byte riskLevel,
    @JsonPropertyDescription("Причина выполнения действия")
    String actionReasoning
) {
}
