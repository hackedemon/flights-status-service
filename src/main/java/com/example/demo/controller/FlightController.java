package com.example.demo.controller;

import com.example.demo.dto.request.AirportRequest;
import com.example.demo.dto.request.FlightRequest;
import com.example.demo.dto.request.FlightScheduleRequest;
import com.example.demo.dto.response.FlightStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Map;

public interface FlightController {
    @Operation(
            operationId = "getFlightStatus",
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

    @Operation(
            operationId = "addAirport",
            summary = "Adds new airport.",
            description = "Adds new airport.",
            tags = {"Admin"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Empty body is returned when airport is added successfully."
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters are not valid. Or airport already exists.",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(
                                                    example = """
                                                            {
                                                                "timeStamp": "2022-12-01T17:23:37.442225800Z",
                                                                "status": 400,
                                                                "message": "Can't add airport with code DUS as it already exists. Use update method instead.",
                                                                "path": "uri=/v1/flight/v1/airport"
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
                                                                "path": "uri=/v1/flight/v1/airport"
                                                            }
                                                            """
                                            ))
                            }
                    )
            }
    )
    ResponseEntity<Void> addAirport(AirportRequest airportRequest);

    @Operation(
            operationId = "addFlight",
            summary = "Adds new flight.",
            description = "Adds new flight.",
            tags = {"Admin"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Empty body is returned when added new flight successfully."
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters are not valid. Or flight already exists. Or airport doesn't exist.",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(
                                                    example = """
                                                            {
                                                                "timeStamp": "2022-12-01T17:23:37.442225800Z",
                                                                "status": 400,
                                                                "message": "No airport found for code string.",
                                                                "path": "uri=/v1/flight/v1/flight"
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
                                                                "message": "Some error occurred while handling the request.",
                                                                "path": "uri=/v1/flight/v1/flight"
                                                            }
                                                            """
                                            ))
                            }
                    )
            }
    )
    ResponseEntity<Void> addFlight(FlightRequest flightRequest);

    @Operation(
            operationId = "addFlightSchedule",
            summary = "Adds new flight schedule.",
            description = "Adds new flight schedule.",
            tags = {"Admin"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Empty body is returned when adding flight schedule is successful."
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = """
                                    Request parameters are not valid. Or flight and departure date time already exists. Or flight doesn't exist.
                                    """,
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(
                                                    example = """
                                                            {
                                                                "timeStamp": "2022-12-01T17:23:37.442225800Z",
                                                                "status": 400,
                                                                "message": "No flight found with flight number EW 015",
                                                                "path": "uri=/v1/flight/v1/flightSchedule"
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
                                                                "message": "Some error occurred while handling the request.",
                                                                "path": "uri=/v1/flight/v1/flightSchedule"
                                                            }
                                                            """
                                            ))
                            }
                    )
            }
    )
    ResponseEntity<Void> addFlightSchedule(FlightScheduleRequest flightScheduleRequest);

    @Operation(
            operationId = "updateFlightSchedule",
            summary = "Update existing flight schedule.",
            description = "Update existing flight schedule based on the fields and values received in body.",
            tags = {"Admin"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Empty body is returned when updating flight schedule is successful."
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
    ResponseEntity<Void> updateFlightSchedule(
            String flightNumber, LocalDate departureDate, Map<String, Object> updatedValues);
}
