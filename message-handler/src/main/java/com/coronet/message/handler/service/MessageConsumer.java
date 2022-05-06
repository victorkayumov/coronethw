package com.coronet.message.handler.service;

import com.coronet.constants.RabbitConstants;
import com.coronet.dto.MessageDto;
import com.coronet.message.handler.service.detector.CreditCardNumberDetector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Set;

@Slf4j
@Service
public class MessageConsumer {
    @Autowired
    private CreditCardNumberDetector creditCardNumberDetector;
    @Autowired
    private EventService eventService;

    @RabbitListener(queues = RabbitConstants.MESSAGE_QUEUE)
    public void consume(MessageDto messageDto) {
        try {
            Set<String> detections = creditCardNumberDetector.detect(messageDto);

            if (!CollectionUtils.isEmpty(detections)) {
                eventService.createEvent(detections, messageDto);
            }
        } catch (Exception e) {
            log.error("Failed to handle the message {}", messageDto, e);
        }
    }

}
