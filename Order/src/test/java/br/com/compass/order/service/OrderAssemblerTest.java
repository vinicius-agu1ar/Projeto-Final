package br.com.compass.order.service;

import br.com.compass.order.entities.Address;
import br.com.compass.order.entities.Item;
import br.com.compass.order.entities.Order;
import br.com.compass.order.service.assembler.OrderDTOAssembler;
import br.com.compass.order.service.dto.response.OrderResponseDTO;
import br.com.compass.order.service.dto.response.OrderResumeResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@ExtendWith(MockitoExtension.class)
public class OrderAssemblerTest {
    @InjectMocks
    private OrderDTOAssembler assembler;

    @Spy
    private ModelMapper mapper;

    @Test
    void toModelSuccess(){
        Order order = getOrder();
        OrderResponseDTO orderResponseDTO = assembler.toModel(order);
        Assertions.assertEquals(order.getCpf(), orderResponseDTO.getCpf());
    }
    @Test
    void toResumeModelSuccess(){
        OrderResponseDTO response = getResponse();
        OrderResumeResponseDTO resumeResponse = assembler.toResumeModel(response);
        Assertions.assertEquals(getResponse().getOrderId(), resumeResponse.getOrderId());
    }

    @Test
    void toCollectionModelSuccess(){
        List<Order> order = List.of(getOrder());
        List<OrderResponseDTO> orderResponseDTOS = assembler.toCollectionModel(order);
        Assertions.assertEquals(order.get(0).getCpf(), orderResponseDTOS.get(0).getCpf());
    }


    private Order getOrder(){
        Order order = new Order();
            order.setOrderid(1L);
            order.setCpf("64568266750");
            order.setItem(getItems());
            order.setAddress(getAddress());
            order.setTotal(BigDecimal.ONE);
        return order;
    }
    private List<Item> getItems(){
        Item request = new Item();
            request.setId(1L);
            request.setName("string");
            request.setPrice(BigDecimal.ONE);
            request.setCreationDate(null);
            request.setExpirationDate(null);
            request.setDescription("string");
        List<Item> itemsList = new ArrayList<>();
        itemsList.add(request);
        return itemsList;
    }

    private Address getAddress(){
        Address address = new Address();
            address.setId(1L);
            address.setUf("BA");
            address.setBairro("Zona Cívico-Administrativa");
            address.setLogradouro("Praça dos Três Poderes");
            address.setLocalidade("Brasília");
            address.setCep("70150900");
            address.setNumero(1);
        return address;
    }

    public OrderResponseDTO getResponse(){
        OrderResponseDTO responseDTO = new OrderResponseDTO();
        responseDTO.setOrderId(1L);
        responseDTO.setTotal(BigDecimal.ONE);
        return responseDTO;
    }
}
