package com.coronet.message.controller.rest;

import com.coronet.dto.MessageDto;
import com.coronet.message.controller.service.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @Autowired
    private MessageProducer messageProducer;

    @PostMapping(value = "/message")
    public void message(@RequestBody MessageDto message) {
        messageProducer.produce(message);
    }
}
