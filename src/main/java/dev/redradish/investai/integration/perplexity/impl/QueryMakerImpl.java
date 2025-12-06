package dev.redradish.investai.integration.perplexity.impl;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.core.JsonValue;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.openai.models.chat.completions.StructuredChatCompletion;
import com.openai.models.chat.completions.StructuredChatCompletionCreateParams;
import dev.redradish.investai.config.UrlFilterProperties;
import dev.redradish.investai.integration.perplexity.QueryMaker;
import dev.redradish.investai.integration.perplexity.dto.CompleteAnswerDto;
import dev.redradish.investai.integration.perplexity.dto.ModelDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author nkucherenko
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class QueryMakerImpl implements QueryMaker {

    public static final String CHAT_QUESTION = "/chat/completions";
    @Value("${perplexity.token}")
    public String token;
    private final UrlFilterProperties urlFilterProperties;

    String systemQuery = """
        Ты имеешь доступ к портфелю пользователя. Сейчас в портфеле пользователя 5000 рублей и нет никаких бумаг.
        Для действий с ним используем jsonFormat TinkoffCommand который задан тебе в настройках. 
        У тебя есть действие, тикер акции с которой нужно совершить действие, сумма и количество.
        Название тикера ищи на https://www.tbank.ru/invest/stocks.
        Ты не новичок, а профессиональный инвестор, поэтому можешь использовать более рисковые позиции
        """;

    @Override
    public String makeQuery(String query) {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", List.of("Bearer %s".formatted(token)));
        OpenAIClient client = OpenAIOkHttpClient.builder()
            .baseUrl("https://api.perplexity.ai")
            .apiKey(token)
            .build();

        StructuredChatCompletionCreateParams<CompleteAnswerDto> params = ChatCompletionCreateParams.builder()
            .addSystemMessage(systemQuery)
            .addUserMessage(query)
            .responseFormat(CompleteAnswerDto.class)
            .model(ModelDto.SONAR_PRO.getName())
            .additionalBodyProperties(
                Map.of("search_domain_filter", JsonValue.from(urlFilterProperties.getFilter()))
            )
            .build();

        StructuredChatCompletion<CompleteAnswerDto> chatCompletion = client.chat().completions()
            .create(params);

        return chatCompletion.choices().get(0).toString();
    }
}
