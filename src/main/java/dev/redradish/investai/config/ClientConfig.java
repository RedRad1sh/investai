package dev.redradish.investai.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverters;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.JacksonJsonHttpMessageConverter;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;

/**
 * @author nkucherenko
 */
@Configuration
public class ClientConfig {
    private static final String PERPLEXITY_API_URL = "https://api.perplexity.ai";

    @Bean
    public JsonMapper jacksonObjectMapper() {
        return JsonMapper.builder()
            .findAndAddModules()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .defaultDateFormat(new SimpleDateFormat("yyyy-MM-dd"))
            .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
            .build();
    }

    @Bean
    public RestClient perplexityRestClient(JsonMapper jsonMapper) {
        return RestClient.builder()
            .requestInterceptor(new LoggingInterceptor())
            .baseUrl(PERPLEXITY_API_URL)
            .configureMessageConverters(HttpMessageConverters.Builder::registerDefaults)
            .configureMessageConverters(clientBuilder -> clientBuilder
                .addCustomConverter(new StringHttpMessageConverter(StandardCharsets.UTF_8))
                .addCustomConverter(new ByteArrayHttpMessageConverter())
                .addCustomConverter(new JacksonJsonHttpMessageConverter(jsonMapper))
            )
            .build();
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
        }
    }
}
