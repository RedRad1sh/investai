package dev.redradish.investai.integration.tinvest.service;

import dev.redradish.investai.dto.account.AccountCreationResponse;
import dev.redradish.investai.dto.account.AccountInfoResponse;
import ru.tinkoff.piapi.contract.v1.GetOperationsByCursorResponse;
import ru.tinkoff.piapi.contract.v1.GetOrdersResponse;
import ru.tinkoff.piapi.contract.v1.PostOrderResponse;

/**
 * @author nkucherenko
 */
public interface TinkoffAccountService {
    AccountCreationResponse openAccount(String name);
    PostOrderResponse buyTicker(String accountId, String tickerName, int count);
    PostOrderResponse sellTicker(String accountId, String tickerName, int count);

    GetOrdersResponse getActiveOrders(String accountId);

    AccountInfoResponse getAccount(String accountId);

    GetOperationsByCursorResponse getOperations(String accountNumber, String cursor);
}
