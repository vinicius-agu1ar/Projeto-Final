package br.com.compass.order.exceptions;

import br.com.compass.order.exceptions.handler.RestExceptionHandler;
import br.com.compass.order.exceptions.response.*;
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
    void handleEntityInUseExceptionTest() {
        EntityInUseException ex = new EntityInUseException();
        assertEquals(HttpStatus.CONFLICT, handler.handleEntityInUseException(ex).getStatusCode());
    }


    @Test
    void handleOrderNotFoundExceptionTest() {
        OrderNotFoundException ex = new OrderNotFoundException();
        assertEquals(HttpStatus.NOT_FOUND, handler.handleOrderNotFoundException(ex).getStatusCode());
    }

    @Test
    void handleInvalidZipcodeExceptionTest() {
        InvalidZipcodeException ex = new InvalidZipcodeException();
        assertEquals(HttpStatus.BAD_REQUEST, handler.handleInvalidZipcodeException(ex).getStatusCode());
    }

    @Test
    void handleItemNotFoundExceptionTest() {
        ItemNotFoundException ex = new ItemNotFoundException();
        assertEquals(HttpStatus.NOT_FOUND, handler.handleItemNotFoundException(ex).getStatusCode());
    }

    @Test
    void handleInvalidDateUsageExceptionTest() {
        InvalidDateUsageException ex = new InvalidDateUsageException();
        assertEquals(HttpStatus.BAD_REQUEST, handler.handleInvalidDateUsageException(ex).getStatusCode());
    }

    @Test
    void handleInvalidCpfExceptionTest() {
        InvalidCpfException ex = new InvalidCpfException();
        assertEquals(HttpStatus.BAD_REQUEST, handler.handleInvalidCpfException(ex).getStatusCode());
    }

    @Test
    void handleAddressNotFoundExceptionTest() {
        AddressNotFoundException ex = new AddressNotFoundException();
        assertEquals(HttpStatus.NOT_FOUND, handler.handleAddressNotFoundException(ex).getStatusCode());
    }
}
