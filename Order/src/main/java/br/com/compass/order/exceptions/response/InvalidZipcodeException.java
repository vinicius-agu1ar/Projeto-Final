package br.com.compass.order.exceptions.response;

import br.com.compass.order.enums.ErrorCode;
import br.com.compass.order.enums.ErrorCodePTBR;
import org.springframework.http.HttpStatus;

import java.io.Serial;

public class InvalidZipcodeException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    private final String details;
    private final ErrorCode errorCode;
    private final ErrorCodePTBR errorCodePTBR;
    private final HttpStatus httpStatus;

    public InvalidZipcodeException(){
        super(ErrorCode.INVALID_ZIPCODE.name());
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errorCode = ErrorCode.INVALID_ZIPCODE;
        this.errorCodePTBR = ErrorCodePTBR.CEP_INVALIDO;
        this.details = ErrorCode.INVALID_ZIPCODE.getMessage();
    }
}
