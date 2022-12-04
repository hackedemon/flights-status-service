package com.example.demo.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import static com.example.demo.constant.ValidationMessagesConstant.*;

@Getter
@Setter
public class AirportRequest {
    @NotBlank(message = AIRPORT_CODE_NOT_BLANK)
    @Min(
            value = 3,
            message = AIRPORT_CODE_LENGTH
    )
    @Max(
            value = 3,
            message = AIRPORT_CODE_LENGTH
    )
    private String code;

    @NotBlank(message = AIRPORT_CODE_NAME_NOT_BLANK)
    private String name;
}
