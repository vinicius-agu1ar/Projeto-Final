package br.com.compass.order.exceptions.response;

import br.com.compass.order.enums.ErrorCode;
import br.com.compass.order.enums.ErrorCodePTBR;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class EntityInUseException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    private final String details;
    private final ErrorCode errorCode;
    private final ErrorCodePTBR errorCodePTBR;
    private final HttpStatus httpStatus;


    public EntityInUseException() {
        super(ErrorCode.ENTITY_IN_USE.name());
        this.httpStatus = HttpStatus.CONFLICT;
        this.errorCode = ErrorCode.ENTITY_IN_USE;
        this.errorCodePTBR = ErrorCodePTBR.ENTIDADE_EM_USO;
        this.details = ErrorCode.ENTITY_IN_USE.getMessage();
    }
}
