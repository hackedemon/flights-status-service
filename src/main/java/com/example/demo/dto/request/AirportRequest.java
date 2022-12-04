package com.example.demo.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AirportRequest {
    @NotBlank(message = "code can't be null/blank.")
    @Min(
            value = 3,
            message = "Length of airport code should be 3."
    )
    @Max(
            value = 3,
            message = "Length of airport code should be 3."
    )
    private String code;

    @NotBlank(message = "name can't be null/blank.")
    private String name;
}
