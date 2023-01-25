package br.com.compass.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    BAD_REQUEST("Request invalid"),
    INVALID_PARAMETER("Invalid request parameter"),
    INVALID_DATE_USAGE("Invalid date, please check before performing this request again"),
    INVALID_SYNTAX("Invalid syntax, please check before performing this request again"),
    INTERNAL_SERVER_ERROR("Internal error has occurred."),
    ORDER_NOT_FOUND("Order not found, check before making this request again"),
    ADDRESS_NOT_FOUND("Address not found, check before making this request again"),
    ITEM_NOT_FOUND("Item not found, check before making this request again"),
    ENTITY_IN_USE("Entity is in use, please check before performing this action again"),
    INVALID_CPF("Invalid CPF, please check before performing this request again"),
    INVALID_ZIPCODE("Invalid zipcode, please check before performing this request again");

    private final String message;
}

