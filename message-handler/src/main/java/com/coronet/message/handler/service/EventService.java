package com.coronet.message.handler.service;

import com.coronet.dto.MessageDto;
import com.coronet.message.handler.converter.EventConverter;
import com.coronet.message.handler.model.Event;
import com.coronet.message.handler.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;
    @Autowired
    private EventConverter eventConverter;

    public void createEvent(Set<String> detections, MessageDto messageDto) {
        if (messageDto != null) {
            Event event = eventConverter.convert(messageDto);
            event.setDetections(detections);
            repository.save(event);
        }
    }
}
