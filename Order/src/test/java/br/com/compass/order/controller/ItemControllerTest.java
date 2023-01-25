package br.com.compass.order.controller;

import br.com.compass.order.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
@WebMvcTest(controllers = ItemController.class)
public class ItemControllerTest {

    public static final String BASE_URL = "/api/itens";
    public static final String ID_URL = BASE_URL + "/1";
    public static final String NAME_URL = ID_URL + "/name";
    @MockBean
    private ItemService service;
    @Autowired
    private MockMvc mvc;

    @Test
    void patch() throws Exception {
        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.patch(NAME_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
