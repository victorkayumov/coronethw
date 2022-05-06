package com.coronet.message.handler.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Document
@Builder
@Data
public class Event {

    @Id
    private String id;
    private String messageId;
    private long creationTime;
    private String sender;
    private List<String> recipients;
    private Set<String> detections;

}
