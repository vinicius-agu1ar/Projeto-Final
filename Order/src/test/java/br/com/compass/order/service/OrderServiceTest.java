package br.com.compass.order.service;

import br.com.compass.order.client.AddressClient;
import br.com.compass.order.entities.Order;
import br.com.compass.order.exceptions.response.EntityInUseException;
import br.com.compass.order.exceptions.response.OrderNotFoundException;
import br.com.compass.order.repository.OrderRepository;
import br.com.compass.order.service.assembler.OrderDTOAssembler;
import br.com.compass.order.service.disassembler.OrderInputDisassembler;
import br.com.compass.order.service.dto.request.OrderRequestDTO;
import br.com.compass.order.service.dto.response.OrderResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    static final Long ID = 1L;

    @InjectMocks
    private OrderService service;
    @Mock
    private OrderRepository repository;
    @Mock
    private OrderDTOAssembler assembler;
    @Mock
    private OrderInputDisassembler disassembler;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private AddressClient addressClient;
    @Mock
    private Pageable pageable;


    @Test
    void shouldFindOrderById_success() {
        Order order = new Order();
        OrderResponseDTO response = new OrderResponseDTO();

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(order));
        Mockito.when(assembler.toModel(order)).thenReturn(response);

        OrderResponseDTO orderResponseDTO = service.findBy(ID);

        Assertions.assertEquals(response.getOrderId(), orderResponseDTO.getOrderId());
        Assertions.assertEquals(response, orderResponseDTO);
    }

    @Test
    void shouldFindOrderById_NotFound() {
        Mockito.when(repository.findById(any())).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> service.findBy(ID));
    }

    @Test
    void shouldCreateOrder_error() {
        Order order = new Order();

        doThrow(new DataIntegrityViolationException("test")).when(repository).save(any());
        Assertions.assertThrows(EntityInUseException.class, () -> service.create(order));
    }

    @Test
    void shouldUpdateOrder_success() {
        Order order = new Order();
        OrderRequestDTO request = new OrderRequestDTO();
        OrderResponseDTO response = new OrderResponseDTO();

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(order));
        Mockito.when(repository.save(any())).thenReturn(order);
        Mockito.when(assembler.toModel(any())).thenReturn(response);

        OrderResponseDTO orderResponseDTO = service.update(ID, request);
        assertEquals(response, orderResponseDTO);
        verify(repository).save(any());
    }

    @Test
    void shouldDeleteOrder_success() {
        service.delete(ID);
        verify(repository).deleteById(any());
    }

    @Test
    void shouldDeleteOrder_error() {
        doThrow(new EmptyResultDataAccessException(21)).when(repository).deleteById(any());
        Assertions.assertThrows(OrderNotFoundException.class, () -> service.delete(ID));
    }

    @Test
    void shouldDeleteOrder_errorDataIntegrityViolationException() {
        doThrow(new DataIntegrityViolationException("test")).when(repository).deleteById(any());
        Assertions.assertThrows(EntityInUseException.class, () -> service.delete(ID));
    }

    @Test
    void shouldFindAllOrders_success() {
        Page<Order> orderPage = new PageImpl<>(List.of(new Order()));
        List<OrderResponseDTO> orderResponseDTOS = List.of(new OrderResponseDTO());

        Mockito.when(repository.findAll(any(Pageable.class))).thenReturn(orderPage);
        Mockito.when(assembler.toCollectionModel(orderPage.getContent())).thenReturn(orderResponseDTOS);

        List<OrderResponseDTO> all = service.findAll(pageable);

        Assertions.assertEquals(orderResponseDTOS, all);
    }

    @Test
    void shouldFindOrderByName_success() {
        Order order = new Order();
        OrderResponseDTO response = new OrderResponseDTO();

        Mockito.when(repository.findByCpf(any())).thenReturn(order);
        Mockito.when(assembler.toModel(order)).thenReturn(response);

        OrderResponseDTO orderResponseDTO = service.findByCpf(any());

        Assertions.assertEquals(response.getCpf(), orderResponseDTO.getCpf());
        Assertions.assertEquals(response, orderResponseDTO);
    }

    @Test
    void shouldVerifyOrderResponseDTO_findAll() {
        Page<Order> orderPage = new PageImpl<>(List.of(new Order()));
        List<OrderResponseDTO> orderResponseDTOS = List.of(new OrderResponseDTO());

        Mockito.when(repository.findAll(any(Pageable.class))).thenReturn(orderPage);
        Mockito.when(assembler.toCollectionModel(orderPage.getContent())).thenReturn(orderResponseDTOS);

        List<OrderResponseDTO> all = service.verifyOrderResponseDTO(pageable, null);

        Assertions.assertEquals(all, orderResponseDTOS);
    }

    @Test
    void shouldVerifyOrderResponseDTO_findByName() {
        Order order = new Order();
        order.setCpf("05529003540");
        OrderResponseDTO response = new OrderResponseDTO();

        Mockito.when(repository.findByCpf(any())).thenReturn(order);
        Mockito.when(assembler.toModel(order)).thenReturn(response);

        List<OrderResponseDTO> orderResponseDTOS = service.verifyOrderResponseDTO(pageable, order.getCpf());

        Assertions.assertEquals(orderResponseDTOS.get(0), response);
    }
}
