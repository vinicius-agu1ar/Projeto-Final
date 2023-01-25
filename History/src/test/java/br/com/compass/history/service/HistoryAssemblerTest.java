package br.com.compass.history.service;

import br.com.compass.history.service.assembler.HistoryDTOAssembler;
import br.com.compass.history.service.response.HistoryResponseDTO;
import br.com.compass.history.entity.History;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class HistoryAssemblerTest {

    @InjectMocks
    private HistoryDTOAssembler assembler;
    @Spy
    private ModelMapper mapper;

    @Test
    void toModelSucess(){
        History history = getHistory();
        HistoryResponseDTO userResponseDTO = assembler.toModel(history);
        Assertions.assertEquals(history.getOrderId(), userResponseDTO.getOrderId());
    }

    @Test
    void toColletionModelSucess(){
        List<History> history = List.of(getHistory());
        List<HistoryResponseDTO> historyResponseDTOS = assembler.toCollectionModel(history);
        Assertions.assertEquals(history.get(0).getOrderId(), historyResponseDTOS.get(0).getOrderId());
    }

    private static History getHistory() {
        History history = new History();
        history.setOrderId(1);
        return history;
    }
}
