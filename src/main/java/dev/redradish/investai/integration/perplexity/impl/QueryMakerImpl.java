package dev.redradish.investai.integration.perplexity.impl;

import dev.redradish.investai.integration.perplexity.QueryMaker;
import dev.redradish.investai.integration.perplexity.dto.MessageDto;
import dev.redradish.investai.integration.perplexity.dto.Model;
import dev.redradish.investai.integration.perplexity.dto.QueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author nkucherenko
 */
@Component
@RequiredArgsConstructor
public class QueryMakerImpl implements QueryMaker {

    @Value("${perplexity.token}")
    public String token;

    private static final String PERPLEXITY_API_URL = "https://api.perplexity.ai/chat/completions";

    String dummyQuery = """
        Проведи фактчекинг основных экономических, политических, санкционных новостей и квартальных отчетов ключевых российских компаний (Сбербанк, Яндекс, X5, Газпром, ОЗОН, ЦИАН, другие), анализируй изменения в макроэкономике и курсе рубля, и корректируй инвестиционный портфель с учетом изменений. Отчитывайся о всех значимых изменениях и рекомендации для инвестора неквалифицированного уровня с доходностью 35% годовых и уровнем риска не выше 70%.
        Если необходимо используй модуль Finance (если доступно что-то для россии)
        """;
    private final RestTemplate restTemplate;

    @Override
    public String makeQuery(String query) {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", List.of("Bearer %s".formatted(token)));
        headers.put("Content-Type", List.of(MediaType.APPLICATION_JSON_VALUE));

        QueryDto queryDto = QueryDto.builder()
            .model(Model.SONAR_PRO)
            .messages(List.of(
                MessageDto.builder()
                    .role("user")
                    .content(dummyQuery)
                    .build()
            ))
            .build();
        HttpEntity<QueryDto> request = new HttpEntity<>(queryDto, headers);
        ResponseEntity<String> response = restTemplate.exchange(PERPLEXITY_API_URL, HttpMethod.POST, request, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException(response.getBody());
        }
    }
}
