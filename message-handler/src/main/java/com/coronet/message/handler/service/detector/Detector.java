package com.coronet.message.handler.service.detector;

import com.coronet.dto.MessageDto;

import java.util.Set;

public interface Detector {

    Set<String> detect(MessageDto messageDto);
}
