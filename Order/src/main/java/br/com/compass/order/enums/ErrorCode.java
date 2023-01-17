package br.com.compass.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    BAD_REQUEST("Request invalid"),
    INVALID_PARAMETER("Invalid request parameter"),
    INTERNAL_SERVER_ERROR("Internal error has occurred."),
    ORDER_NOT_FOUND("Order not found, check before making this request again"),
    ADDRESS_NOT_FOUND("Address not found, check before making this request again"),
    ENTITY_IN_USE("Entity is in use, please check before performing this action again");

    private final String message;
}

