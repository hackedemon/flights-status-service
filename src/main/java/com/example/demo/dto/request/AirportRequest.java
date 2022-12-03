package com.example.demo.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirportRequest {
    @NotNull
    @NotBlank
    @Min(
            value = 3,
            message = "Length of airport code should be 3."
    )
    @Max(
            value = 3,
            message = "Length of airport code should be 3."
    )
    private String code;

    @NotNull
    @NotBlank
    private String name;
}
