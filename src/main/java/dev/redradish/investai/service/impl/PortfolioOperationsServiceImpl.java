package dev.redradish.investai.service.impl;

import dev.redradish.investai.dto.PortfolioRebalanceResponse;
import dev.redradish.investai.dto.portfolio.FullPortfolioInfoResponse;
import dev.redradish.investai.dto.portfolio.PortfolioCreateResponse;
import dev.redradish.investai.integration.tinvest.service.TinkoffAccountService;
import dev.redradish.investai.service.PortfolioOperationsService;

/**
 * @author nkucherenko
 */
public class PortfolioOperationsServiceImpl implements PortfolioOperationsService {
    TinkoffAccountService tinkoffAccountService;

    @Override
    public PortfolioCreateResponse initNewPortfolio(String userCommentRequest) {
        return null;
    }

    @Override
    public FullPortfolioInfoResponse getExistingPortfolio(String portfolioId) {
        return null;
    }

    @Override
    public PortfolioRebalanceResponse forceRebalancePortfolio(String portfolioId, String userCommentRequest) {
        return null;
    }

    @Override
    public PortfolioRebalanceResponse deletePortfolio(String portfolioId, String userCommentRequest) {
        return null;
    }
}
