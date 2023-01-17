package br.com.compass.order.exceptions.handler;

import br.com.compass.order.enums.ErrorCode;
import br.com.compass.order.enums.ErrorCodePTBR;
import br.com.compass.order.exceptions.response.EntityInUseException;
import br.com.compass.order.exceptions.response.ExceptionResponse;
import br.com.compass.order.exceptions.response.OrderNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.INVALID_PARAMETER, ErrorCodePTBR.PARAMETRO_INVALIDO, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.BAD_REQUEST, ErrorCodePTBR.REQUISICAO_INVALIDA, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getMessage());
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        List<String> errors = new ArrayList<>();
        fieldErrors.forEach(error ->
                errors.add(String.format("%s : %s", error.getField(), error.getDefaultMessage()))
        );

        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.BAD_REQUEST, ErrorCodePTBR.REQUISICAO_INVALIDA, errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.BAD_REQUEST, ErrorCodePTBR.REQUISICAO_INVALIDA, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.BAD_REQUEST, ErrorCodePTBR.REQUISICAO_INVALIDA, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(EntityInUseException.class)
    public final ResponseEntity<Object> handleEntityInUseException(Exception ex) {
        log.error(ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.ENTITY_IN_USE, ErrorCodePTBR.ENTIDADE_EM_USO, ex);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponse);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public final ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException ex) {
        log.error(ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.ORDER_NOT_FOUND, ErrorCodePTBR.PEDIDO_NAO_ENCONTRADO, ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }
}
