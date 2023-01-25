package br.com.compass.order.controller;

import br.com.compass.order.service.OrderService;
import br.com.compass.order.service.dto.request.AddressRequestDTO;
import br.com.compass.order.service.dto.request.ItemRequestDTO;
import br.com.compass.order.service.dto.request.OrderRequestDTO;
import br.com.compass.order.service.dto.response.OrderResponseDTO;
import br.com.compass.order.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = OrderController.class)
public class OrderControllerTest {

    public static final String BASE_URL = "/api/pedidos";
    public static final String ID_URL = BASE_URL + "/1";
    @MockBean
    private OrderService service;
    @MockBean
    private RabbitTemplate template;
    @Autowired
    private MockMvc mvc;

    @Test
    void findAll() throws Exception {
        List<OrderResponseDTO> orders = Arrays.asList(new OrderResponseDTO());
        when(service.findAll(any(Pageable.class))).thenReturn(orders);
        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.get(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse resposta = result.getResponse();
        assertEquals(HttpStatus.OK.value(), resposta.getStatus());
    }

    @Test
    void findById() throws Exception {
        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.get(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void create() throws Exception {
        OrderRequestDTO request = getOrderRequestDTO();
        String input = TestUtils.mapToJson(request);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }
    @Test
    void update() throws Exception {
        OrderRequestDTO request = getOrderRequestDTO();
        String input = TestUtils.mapToJson(request);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.put(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void delete() throws Exception {
        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.delete(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }


    private OrderRequestDTO getOrderRequestDTO(){
        return OrderRequestDTO.builder()
                .cpf("64568266750")
                .item(items())
                .address(address())
                .build();
    }
    private List<ItemRequestDTO> items(){
        ItemRequestDTO request = ItemRequestDTO.builder()
                .name("string")
                .price(BigDecimal.ONE)
                .expirationDate(null)
                .description("string")
                .build();
        List<ItemRequestDTO> itemsList = new ArrayList<>();
        itemsList.add(request);
        return itemsList;
    }

    private AddressRequestDTO address(){
        return AddressRequestDTO.builder()
                .cep("70150900")
                .numero(1)
                .build();
    }
}
