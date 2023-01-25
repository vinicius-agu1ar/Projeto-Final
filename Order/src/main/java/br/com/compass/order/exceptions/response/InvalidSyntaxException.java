package br.com.compass.order.exceptions.response;

import br.com.compass.order.enums.ErrorCode;
import br.com.compass.order.enums.ErrorCodePTBR;
import org.springframework.http.HttpStatus;

import java.io.Serial;

public class InvalidSyntaxException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    private final String details;
    private final ErrorCode errorCode;
    private final ErrorCodePTBR errorCodePTBR;
    private final HttpStatus httpStatus;

    public InvalidSyntaxException(){
        super(ErrorCode.INVALID_SYNTAX.name());
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errorCode = ErrorCode.INVALID_SYNTAX;
        this.errorCodePTBR = ErrorCodePTBR.SINTAXE_INVALIDA;
        this.details = ErrorCode.INVALID_SYNTAX.getMessage();
    }
}
