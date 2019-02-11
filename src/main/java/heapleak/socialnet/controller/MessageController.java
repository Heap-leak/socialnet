package heapleak.socialnet.controller;


import com.fasterxml.jackson.annotation.JsonView;
import heapleak.socialnet.domain.Message;
import heapleak.socialnet.domain.Views;
import heapleak.socialnet.dto.EventType;
import heapleak.socialnet.dto.ObjectType;
import heapleak.socialnet.repo.MessageRepo;
import heapleak.socialnet.util.WsSender;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("message")
public class MessageController {
    private final MessageRepo messageRepo;
    private final BiConsumer<EventType, Message> sender;

    @Autowired
    public MessageController(MessageRepo messageRepo, WsSender sender) {
        this.messageRepo = messageRepo;
        this.sender = sender.getSender(ObjectType.MESSAGE, Views.IdName.class);
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Message> list(){
        return messageRepo.findAll();
    }

    @GetMapping("{id}")
    public Message getOne(@PathVariable("id") Message message){
        return message;
    }

    @PostMapping
    public Message create(@RequestBody Message message){
        message.setCreationDate(LocalDateTime.now());
        Message createdMessage = messageRepo.save(message);
        sender.accept(EventType.CREATE, createdMessage);
        return createdMessage;
    }

    @PutMapping("{id}")
    public Message update(
            @PathVariable("id") Message messageFromDb,
            @RequestBody Message message){
        BeanUtils.copyProperties(message, messageFromDb, "id");
        Message updatedMessage = messageRepo.save(messageFromDb);
        sender.accept(EventType.UPDATE, updatedMessage);
        return updatedMessage;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message){
        messageRepo.delete(message);
        sender.accept(EventType.REMOVE, message);
    }
}
