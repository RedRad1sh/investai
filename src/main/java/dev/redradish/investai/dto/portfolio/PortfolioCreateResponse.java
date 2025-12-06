package dev.redradish.investai.dto.portfolio;

/**
 * @author nkucherenko
 */
public record PortfolioCreateResponse(
    FullPortfolioInfoResponse info,
    PortfolioActionResponse portfolioActionResponse
) {
}
