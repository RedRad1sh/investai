package dev.redradish.investai.service;

import dev.redradish.investai.dto.PortfolioRebalanceResponse;
import dev.redradish.investai.dto.portfolio.PortfolioCreateResponse;
import dev.redradish.investai.dto.portfolio.FullPortfolioInfoResponse;

/**
 * @author nkucherenko
 */
public interface PortfolioOperationsService {
    PortfolioCreateResponse initNewPortfolio(String userCommentRequest);
    FullPortfolioInfoResponse getExistingPortfolio(String portfolioId);
    PortfolioRebalanceResponse forceRebalancePortfolio(String portfolioId, String userCommentRequest);

    /**
     * Удаление только из БД бота, фактически остается на аккаунте
     * @param portfolioId
     * @return
     */
    PortfolioRebalanceResponse deletePortfolio(String portfolioId, String userCommentRequest);
}
