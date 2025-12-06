package dev.redradish.investai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.piapi.contract.v1.SandboxServiceGrpc;
import ru.ttech.piapi.core.connector.ServiceStubFactory;

/**
 * @author nkucherenko
 */
@Configuration
public class TinkoffSandboxConfig {
    @Bean
    public SandboxServiceGrpc.SandboxServiceBlockingStub sandboxServiceBlockingStub(ServiceStubFactory serviceStubFactory) {
        return serviceStubFactory.newSyncService(SandboxServiceGrpc::newBlockingStub).getStub();
    }
}
