package heapleak.socialnet.dto;


import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonView;
import com.sun.tracing.dtrace.ArgsAttributes;
import heapleak.socialnet.domain.Views;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonView(Views.IdName.class)
public class WsEventsDto {
    private ObjectType objectType;
    private EventType eventType;
    @JsonRawValue
    private String body;
}
