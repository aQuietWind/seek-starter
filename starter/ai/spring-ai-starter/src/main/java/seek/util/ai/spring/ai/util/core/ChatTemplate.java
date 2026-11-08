package seek.util.ai.spring.ai.util.core;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;

public class ChatTemplate {

    private final ChatClient chatClient;
    @Autowired
    public ChatTemplate(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chat(String msg) {
        return chatClient.prompt()
                .user(msg)
                .call()
                .content();
    }

    public Flux<String> streamChat(String msg) {
        return chatClient.prompt()
                .user(msg)
                .stream()
                .content();
    }
}