package seek.starter.starter.ai.langchain4j.starter.support;

import org.springframework.beans.factory.annotation.Autowired;
import seek.starter.starter.ai.langchain4j.starter.configure.PropertiesConfig;
import seek.util.common.config.object.db.redis.RedisKeyData;
import seek.starter.starter.db.redis.starter.core.Redis.RedisTemplate;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ChatMessageDeserializer;
import dev.langchain4j.data.message.ChatMessageSerializer;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;

import java.util.List;

//该存储方式不应该支持多模态,如图片音乐等等
//redis记忆存储会话
public class RedisChatMemoryStore implements ChatMemoryStore {

    private final RedisTemplate redisTemplate;
    private final RedisKeyData redisKey;

    @Autowired
    public RedisChatMemoryStore(RedisTemplate redisTemplate, PropertiesConfig propertiesConfig) {
        this.redisTemplate = redisTemplate;
        redisKey = propertiesConfig.getRedisSessionMemory().getRedisKey();
    }

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        if(memoryId==null) return null;
        String json = redisTemplate.getString(redisKey, memoryId);
        return ChatMessageDeserializer.messagesFromJson(json);
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> messages) {
        if(memoryId==null) return;
        String json = ChatMessageSerializer.messagesToJson(messages);
        redisTemplate.justSetStringWithExpire(redisKey,memoryId, json);
    }

    @Override
    public void deleteMessages(Object memoryId) {
        if(memoryId==null) return;
        redisTemplate.delete(redisKey, memoryId);
    }
}