package br.com.compass.order.exceptions.response;

import br.com.compass.order.enums.ErrorCode;
import br.com.compass.order.enums.ErrorCodePTBR;
import org.springframework.http.HttpStatus;

import java.io.Serial;

public class InvalidDateUsageException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    private final String details;
    private final ErrorCode errorCode;
    private final ErrorCodePTBR errorCodePTBR;
    private final HttpStatus httpStatus;

    public InvalidDateUsageException(){
        super(ErrorCode.INVALID_DATE_USAGE.name());
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errorCode = ErrorCode.INVALID_DATE_USAGE;
        this.errorCodePTBR = ErrorCodePTBR.USO_DATA_INVALIDO;
        this.details = ErrorCode.INVALID_DATE_USAGE.getMessage();
    }
}
