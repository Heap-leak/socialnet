package heapleak.socialnet.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import heapleak.socialnet.dto.EventType;
import heapleak.socialnet.dto.ObjectType;
import heapleak.socialnet.dto.WsEventsDto;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Component
public class WsSender {
    private final SimpMessagingTemplate template;
    private final ObjectMapper mapper;

    public WsSender(SimpMessagingTemplate template, ObjectMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    public <T> BiConsumer<EventType, T> getSender(ObjectType objectType, Class view){
        ObjectWriter objectWriter = mapper.setConfig(mapper.getSerializationConfig())
                .writerWithView(view);

        return (EventType eventType, T payload)-> {
            String value =null;
            try {
                value = objectWriter.writeValueAsString(payload);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            template.convertAndSend("/topic/activity",
                    new WsEventsDto(objectType, eventType, value));
        };
    }
}
