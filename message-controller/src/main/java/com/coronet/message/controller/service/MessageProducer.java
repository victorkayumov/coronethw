package com.coronet.message.controller.service;

import com.coronet.constants.RabbitConstants;
import com.coronet.dto.MessageDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    @Autowired
    private RabbitTemplate jsonRabbitTemplate;

    public void produce(MessageDto messageDto) {
        jsonRabbitTemplate.convertAndSend(RabbitConstants.MESSAGE_QUEUE, messageDto);
    }

}
