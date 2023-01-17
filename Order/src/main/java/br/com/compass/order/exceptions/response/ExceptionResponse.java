package br.com.compass.order.exceptions.response;

import br.com.compass.order.enums.ErrorCode;
import br.com.compass.order.enums.ErrorCodePTBR;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
public class ExceptionResponse {

    private final String code;

    private final String message;

    private final String mensagem;

    private final List<String> details;


    public ExceptionResponse(ErrorCode errorCode, ErrorCodePTBR errorCodePTBR, Throwable ex) {
        this(errorCode, errorCodePTBR, ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage());
    }

    public ExceptionResponse(ErrorCode errorCode, ErrorCodePTBR errorCodePTBR, String details) {
        this.code = errorCode.name();
        this.message = errorCode.getMessage();
        this.mensagem = errorCodePTBR.getMensagem();
        this.details = Collections.singletonList(details);
    }

    public ExceptionResponse(ErrorCode errorCode, ErrorCodePTBR errorCodePTBR, List<String> details) {
        this.code = errorCode.name();
        this.message = errorCode.getMessage();
        this.mensagem = errorCodePTBR.getMensagem();
        this.details = details;
    }
}

