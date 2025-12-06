package dev.redradish.investai.dto.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.tinkoff.piapi.contract.v1.OpenSandboxAccountResponse;

/**
 * @author nkucherenko
 */
public record AccountCreationResponse(
    @JsonIgnore
    OpenSandboxAccountResponse openSandboxAccountResponse,
    String accountNumber) {
}
