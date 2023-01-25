package br.com.compass.history.exceptions;

import br.com.compass.history.exceptions.handlers.RestExceptionHandler;
import br.com.compass.history.exceptions.responses.HistoryNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(MockitoExtension.class)
public class HandlerTest {
    @InjectMocks
    private RestExceptionHandler handler;


    @Test
    void handleHistoryNotFoundExceptionTest() {
        HistoryNotFoundException ex = new HistoryNotFoundException();
        assertEquals(HttpStatus.CONFLICT, handler.handleHistoryNotFoundException(ex).getStatusCode());
    }
}
