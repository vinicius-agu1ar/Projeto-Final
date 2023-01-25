package br.com.compass.history.exceptions.responses;

import br.com.compass.history.enums.ErrorCode;
import br.com.compass.history.enums.ErrorCodePTBR;
import org.springframework.http.HttpStatus;

import java.io.Serial;

public class HistoryNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    private final String details;
    private final ErrorCode errorCode;
    private final ErrorCodePTBR errorCodePTBR;
    private final HttpStatus httpStatus;

    public HistoryNotFoundException(){
        super(ErrorCode.HISTORY_NOT_FOUND.name());
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.errorCode = ErrorCode.HISTORY_NOT_FOUND;
        this.errorCodePTBR = ErrorCodePTBR.HISTORICO_NAO_ENCONTRADO;
        this.details = ErrorCode.HISTORY_NOT_FOUND.getMessage();
    }

}
