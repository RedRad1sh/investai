package dev.redradish.investai.integration.tinvest.service.impl.sandbox;

import dev.redradish.investai.dto.account.AccountCreationResponse;
import dev.redradish.investai.dto.account.AccountInfoResponse;
import dev.redradish.investai.integration.tinvest.service.TinkoffAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.piapi.contract.v1.GetOperationsByCursorRequest;
import ru.tinkoff.piapi.contract.v1.GetOperationsByCursorResponse;
import ru.tinkoff.piapi.contract.v1.GetOrdersRequest;
import ru.tinkoff.piapi.contract.v1.GetOrdersResponse;
import ru.tinkoff.piapi.contract.v1.OpenSandboxAccountRequest;
import ru.tinkoff.piapi.contract.v1.OpenSandboxAccountResponse;
import ru.tinkoff.piapi.contract.v1.OrderDirection;
import ru.tinkoff.piapi.contract.v1.PortfolioRequest;
import ru.tinkoff.piapi.contract.v1.PortfolioResponse;
import ru.tinkoff.piapi.contract.v1.PostOrderRequest;
import ru.tinkoff.piapi.contract.v1.PostOrderResponse;
import ru.tinkoff.piapi.contract.v1.SandboxServiceGrpc;

/**
 * @author nkucherenko
 */
@Service
@RequiredArgsConstructor
public class TinkoffSandboxAccountServiceImpl implements TinkoffAccountService {
    private final SandboxServiceGrpc.SandboxServiceBlockingStub sandBoxStub;

    @Override
    public AccountCreationResponse openAccount(String name) {
        OpenSandboxAccountRequest request = OpenSandboxAccountRequest
            .newBuilder()
            .setName(name)
            .build();
        OpenSandboxAccountResponse response = sandBoxStub
            .openSandboxAccount(request);

        return new AccountCreationResponse(response, response.getAccountId());
    }

    @Override
    public PostOrderResponse buyTicker(String accountId, String tickerName, int count) {
        PostOrderRequest request = PostOrderRequest.newBuilder()
            .setAccountId(accountId)
            .setInstrumentId(tickerName)
            .setDirection(OrderDirection.ORDER_DIRECTION_BUY)
            .build();
        return sandBoxStub.postSandboxOrder(request);
    }

    @Override
    public PostOrderResponse sellTicker(String accountId, String tickerName, int count) {
        PostOrderRequest request = PostOrderRequest.newBuilder()
            .setAccountId(accountId)
            .setInstrumentId(tickerName)
            .setDirection(OrderDirection.ORDER_DIRECTION_SELL)
            .build();
        return sandBoxStub.postSandboxOrder(request);
    }

    @Override
    public GetOrdersResponse getActiveOrders(String accountId) {
        GetOrdersRequest request = GetOrdersRequest.newBuilder()
            .setAccountId(accountId)
            .build();
        return sandBoxStub.getSandboxOrders(request);
    }

    @Override
    public AccountInfoResponse getAccount(String accountNumber) {
        PortfolioRequest portfolioRequest = PortfolioRequest.newBuilder()
            .setAccountId(accountNumber)
            .build();
        PortfolioResponse response = sandBoxStub.getSandboxPortfolio(portfolioRequest);
        return new AccountInfoResponse(response);
    }

    @Override
    public GetOperationsByCursorResponse getOperations(String accountNumber, String cursor) {
        GetOperationsByCursorRequest getOperationsByCursorRequest = GetOperationsByCursorRequest.newBuilder()
            .setAccountId(accountNumber)
            .setCursor(cursor)
            .setLimit(20)
            .build();
        return sandBoxStub.getSandboxOperationsByCursor(getOperationsByCursorRequest);
    }
}
