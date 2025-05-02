package lll.backend.domain.chat.infra;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OpenAIWrapper {

    private final OpenAIClient client;

    public OpenAIWrapper(@Value("${openai.api.key}") String apiKey) {
        this.client = OpenAIOkHttpClient.builder()
                .apiKey(apiKey)
                .build();
    }

    public String getResponseMessage(String content) {
        ResponseCreateParams params = ResponseCreateParams.builder()
                .input(content)
                .model(ChatModel.CHATGPT_4O_LATEST)
                .build();

        Response response = client.responses().create(params);

        return response.output()
                .getFirst()
                .message()
                .orElseThrow(() -> new RuntimeException("Error generating response from OpenAI"))
                .content()
                .getFirst()
                .asOutputText()
                .text();
    }
}
