package dev.redradish.investai.controller;

import dev.redradish.investai.dto.account.AccountCreationResponse;
import dev.redradish.investai.dto.account.AccountHealthResponse;
import dev.redradish.investai.dto.account.AccountInfoResponse;
import dev.redradish.investai.dto.account.AccountRebalanceResponse;
import dev.redradish.investai.integration.tinvest.service.TinkoffAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.piapi.contract.v1.GetOperationsByCursorResponse;
import ru.tinkoff.piapi.contract.v1.GetOrdersResponse;

import java.net.URI;
import java.util.UUID;

/**
 * В данный момент работает только с песочницей
 * @author nkucherenko
 */
@RestController
@RequestMapping("account")
@RequiredArgsConstructor
public class PortfolioController {

    private final TinkoffAccountService tinkoffAccountService;


    /**
     * Просто создает новый инвестиционный счет на аккаунте клиента
     */
    @PostMapping
    public ResponseEntity<AccountCreationResponse> createNewAccount() {
        AccountCreationResponse response = tinkoffAccountService
            .openAccount(UUID.randomUUID().toString());
        return ResponseEntity.created(URI.create(response.accountNumber())).body(response);
    }

    /**
     * Получить состав портфеля от Т.Инвест АПИ
     * @param accountNumber номер счета
     * @return состав портфеля
     */
    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountInfoResponse> getAccountInfo(@PathVariable String accountNumber) {
        return ResponseEntity.ok(tinkoffAccountService.getAccount(accountNumber));
    }

    /**
     * Проверить активные заявки
     * @param accountNumber номер счета
     * @return состав портфеля
     */
    @GetMapping("/{accountNumber}/orders")
    public ResponseEntity<GetOrdersResponse> getActiveOrders(@PathVariable String accountNumber) {
        return ResponseEntity.ok(tinkoffAccountService.getActiveOrders(accountNumber));
    }

    /**
     * Проверить совершенные операции
     * @param accountNumber номер счета
     * @param cursor курсор следующей страницы
     * @return состав портфеля
     */
    @GetMapping("/{accountNumber}/operations")
    public ResponseEntity<GetOperationsByCursorResponse> getOperations(@PathVariable String accountNumber, @RequestParam String cursor) {
        return ResponseEntity.ok(tinkoffAccountService.getOperations(accountNumber, cursor));
    }


    /**
     * Запросить аналитику по портфелю от АИ
     * @param accountNumber номер счета
     * @param additionalRequestComment дополнительный комментарий о требуемых действиях
     * @return состояние портвеля по оценке АИ
     */
    public ResponseEntity<AccountHealthResponse> checkAccountHealth(String accountNumber, String additionalRequestComment) {
        throw new IllegalStateException();
    }

    /**
     * Провести принудительную ребалансировку портфеля по запросу клиента
     * @param accountNumber номер счета
     * @param additionalRequestComment дополнительный комментарий о требуемых действиях
     * @return информация о действиях ребалансировки
     */
    public ResponseEntity<AccountRebalanceResponse> doAccountRebalance(String accountNumber, String additionalRequestComment) {
        throw new IllegalStateException();
    }

}
