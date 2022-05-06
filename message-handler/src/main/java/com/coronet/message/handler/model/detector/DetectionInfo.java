package com.coronet.message.handler.model.detector;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DetectionInfo {

    private boolean detected;
    private String detection;
}
