package com.coronet.message.handler.converter;

import com.coronet.dto.MessageDto;
import com.coronet.message.handler.model.Event;
import org.springframework.stereotype.Service;

@Service
public class EventConverter implements Converter<Event, MessageDto> {

    @Override
    public Event convert(MessageDto messageDto) {

        return Event.builder()
                .creationTime(System.currentTimeMillis())
                .messageId(messageDto.getId())
                .recipients(messageDto.getRecipients())
                .sender(messageDto.getSender())
                .build();
    }
}
