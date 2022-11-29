package com.example.demo.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Builder
public class AppError implements Serializable {
    private Instant timeStamp;
    private Integer status;
    private String message;
    private String path;
}
