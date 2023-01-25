package br.com.compass.history.amqp;

import br.com.compass.history.entity.History;
import br.com.compass.history.service.HistoryService;
import br.com.compass.history.service.disassembler.HistoryInputDisassembler;
import br.com.compass.history.service.response.HistoryResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class OrderListenerTest {

    @InjectMocks
    private OrderListener listener;
    @Mock
    private HistoryService service;
    @Mock
    private HistoryInputDisassembler disassembler;


    @Test
    void messegerRecieverTest() {
        HistoryResponseDTO response = getHistoryResponse();
        listener.messegerReciever(response);
        Assertions.assertEquals(1, response.getOrderId());

    }

    private static HistoryResponseDTO getHistoryResponse() {
        HistoryResponseDTO responseDTO = new HistoryResponseDTO();
        responseDTO.setOrderId(1);
        responseDTO.setId("ABC");
        responseDTO.setTotal(BigDecimal.ONE);
        responseDTO.setHistoryDate(null);
        return responseDTO;
    }
}
