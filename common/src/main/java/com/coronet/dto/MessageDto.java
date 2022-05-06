package com.coronet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

    private String id;
    private String sender;
    private List<String> recipients;
    private String subject;
    private String body;
    private long sentTime;
}
