package br.com.compass.history.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    BAD_REQUEST("Request invalid"),
    INVALID_PARAMETER("Invalid request parameter"),
    INVALID_DATE_USAGE("Invalid date, please check before performing this request again"),
    INTERNAL_SERVER_ERROR("Internal error has occurred."),
    HISTORY_NOT_FOUND("History not found, check before making this request again");

    private final String message;
}
