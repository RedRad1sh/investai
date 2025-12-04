package dev.redradish.investai.config;

import org.hobsoft.spring.resttemplatelogger.LoggingCustomizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author nkucherenko
 */
@Configuration
public class ClientConfig {
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    public class LoggingInterceptor implements ClientHttpRequestInterceptor {

        private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

        @Override
        public ClientHttpResponse intercept(
            HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

            logRequestDetails(request, body);
            ClientHttpResponse response = execution.execute(request, body);
            logResponseDetails(response);

            return response;
        }

        private void logRequestDetails(HttpRequest request, byte[] body) throws UnsupportedEncodingException {
            logger.info("Request Headers: {}", request.getHeaders());
            logger.info("Request Attributes: {}", request.getAttributes());
            logger.info("Request Method: {}", request.getMethod());
            logger.info("Request URI: {}", request.getURI());
            logger.info("Request Body: {}", new String(body, "UTF-8"));
        }

        private void logResponseDetails(ClientHttpResponse response) throws IOException {
            logger.info("Response Status Code: {}", response.getStatusCode());
            logger.info("Response Status Text: {}", response.getStatusText());
            logger.info("Response Body: {}", StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8));
        }
    }
}
