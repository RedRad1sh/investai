package dev.redradish.investai.dto.portfolio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.tinkoff.piapi.contract.v1.PortfolioPosition;
import ru.tinkoff.piapi.contract.v1.PortfolioResponse;
import ru.tinkoff.piapi.contract.v1.VirtualPortfolioPosition;

import java.util.List;

/**
 * @author nkucherenko
 */
public record FullPortfolioInfoResponse(@JsonIgnore PortfolioResponse portfolioResponse) {
    public List<PortfolioPosition> getPositions() {
        return portfolioResponse.getPositionsList();
    }
    public List<VirtualPortfolioPosition> getVirtualPositions() {
        return portfolioResponse.getVirtualPositionsList();
    }
    public String getAccountId() {
        return portfolioResponse.getAccountId();
    }
}
