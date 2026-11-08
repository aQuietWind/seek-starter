package seek.util.ai.spring.ai.util.autoconfigure;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import seek.util.ai.spring.ai.util.enums.PackageEnum;
import seek.util.ai.spring.ai.util.support.AiLogAdvisor;
import seek.util.ai.spring.ai.util.core.ChatTemplate;

@AutoConfiguration(PackageEnum.Full_Package_Prefix +"AutoConfig")
public class AutoConfig {
    @Bean
    public ChatMemory chatMemory() {
        return MessageWindowChatMemory.builder()
                .chatMemoryRepository(new InMemoryChatMemoryRepository())
                .maxMessages(20).build();
    }

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder, ChatMemory chatMemory) {
        return builder
                // 全局系统提示词
                .defaultSystem("")
                .defaultAdvisors(new AiLogAdvisor(), MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }
    @Bean
    public ChatTemplate chatUtil(ChatClient chatClient) {
        return new ChatTemplate(chatClient);
    }
}
