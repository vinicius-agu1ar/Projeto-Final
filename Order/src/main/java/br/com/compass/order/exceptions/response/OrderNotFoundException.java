package br.com.compass.order.exceptions.response;

import br.com.compass.order.enums.ErrorCode;
import br.com.compass.order.enums.ErrorCodePTBR;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class OrderNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    private final String details;
    private final ErrorCode errorCode;
    private final ErrorCodePTBR errorCodePTBR;
    private final HttpStatus httpStatus;

    public OrderNotFoundException(){
        super(ErrorCode.ORDER_NOT_FOUND.name());
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.errorCode = ErrorCode.ORDER_NOT_FOUND;
        this.errorCodePTBR = ErrorCodePTBR.PEDIDO_NAO_ENCONTRADO;
        this.details = ErrorCode.ORDER_NOT_FOUND.getMessage();
    }
}
