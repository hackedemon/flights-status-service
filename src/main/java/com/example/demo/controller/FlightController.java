package com.example.demo.controller;

import com.example.demo.dto.response.FlightStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface FlightController {
    @Operation(
            operationId = "getFligthStatus",
            summary = "Returns flight status based on flight number and travel date.",
            description = "Returns flight status based on flight number and travel date.",
            tags = {"Public"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = """
                                    Returns response status in body for flight number and travel date passed.
                                    In case details are not found then not found error response is returned.
                                    """,
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = FlightStatus.class))
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters are not valid.",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(
                                                    example = """
                                                            {
                                                                "timeStamp": "2022-12-01T17:23:37.442225800Z",
                                                                "status": 400,
                                                                "message": "travelDate value 2 is invalid.",
                                                                "path": "uri=/flight/status/flightNumber/EW/travelDate/2"
                                                            }
                                                            """
                                            ))
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "When flight number and/or travel date combination is not found.",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(
                                                    example = """
                                                            {
                                                                "timeStamp": "2022-12-01T17:23:37.442225800Z",
                                                                "status": 404,
                                                                "message": "No flight exists with flight number: 124",
                                                                "path": "uri=/flight/status/flightNumber/124/travelDate/2000-12-12"
                                                            }
                                                            """
                                            ))
                            }
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "In case of a server error.",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(
                                                    example = """
                                                            {
                                                                "timeStamp": "2022-12-01T17:23:37.442225800Z",
                                                                "status": 500,
                                                                "message": "Some error occured while handling the request.",
                                                                "path": "uri=/flight/status/flightNumber/EW/travelDate/2000-12-12"
                                                            }
                                                            """
                                            ))
                            }
                    )
            }
    )
    ResponseEntity<FlightStatus> getFlightStatus(String flightNumber, LocalDate travelDate);
}
