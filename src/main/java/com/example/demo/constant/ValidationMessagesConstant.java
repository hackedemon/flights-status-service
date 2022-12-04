package com.example.demo.constant;

public final class ValidationMessagesConstant {
    // Airport request
    public static final String AIRPORT_CODE_NOT_BLANK = "code can't be null/blank.";
    public static final String AIRPORT_CODE_LENGTH = "Length of airport code should be 3.";
    public static final String AIRPORT_CODE_NAME_NOT_BLANK = "name can't be null/blank.";

    // Flight controller
    public static final String FLIGHT_NUMBER_NOT_BLANK = "Flight number can't be blank.";
    public static final String TRAVEL_DATE_NOT_NULL = "Travel date can't be null.";
    public static final String DEPARTURE_DATE_IN_PAST = "Can't modify past flight schedule.";
    public static final String TRAVEL_DATE_IN_PAST = "Travel date can only be today's or in future.";

    private ValidationMessagesConstant() {
        throw new UnsupportedOperationException("Constructor creation not supported");
    }
}
