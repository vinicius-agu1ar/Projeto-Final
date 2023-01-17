package br.com.compass.order.exceptions.response;

import br.com.compass.order.enums.ErrorCode;
import br.com.compass.order.enums.ErrorCodePTBR;
import br.com.compass.order.repository.AddressRepository;
import org.springframework.http.HttpStatus;

import java.io.Serial;

public class AddressNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    private final String details;
    private final ErrorCode errorCode;
    private final ErrorCodePTBR errorCodePTBR;
    private final HttpStatus httpStatus;

    public AddressNotFoundException(){
        super(ErrorCode.ADDRESS_NOT_FOUND.name());
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.errorCode = ErrorCode.ADDRESS_NOT_FOUND;
        this.errorCodePTBR = ErrorCodePTBR.ENDERECO_NAO_ENCONTRADO;
        this.details = ErrorCode.ADDRESS_NOT_FOUND.getMessage();
    }
}
