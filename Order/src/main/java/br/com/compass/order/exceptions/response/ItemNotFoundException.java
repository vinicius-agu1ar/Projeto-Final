package br.com.compass.order.exceptions.response;

import br.com.compass.order.enums.ErrorCode;
import br.com.compass.order.enums.ErrorCodePTBR;
import org.springframework.http.HttpStatus;

import java.io.Serial;

public class ItemNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    private final String details;
    private final ErrorCode errorCode;
    private final ErrorCodePTBR errorCodePTBR;
    private final HttpStatus httpStatus;

    public ItemNotFoundException() {
        super(ErrorCode.ITEM_NOT_FOUND.name());
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.errorCode = ErrorCode.ITEM_NOT_FOUND;
        this.errorCodePTBR = ErrorCodePTBR.ITEM_NAO_ENCONTRADO;
        this.details = ErrorCode.ITEM_NOT_FOUND.getMessage();
    }
}
