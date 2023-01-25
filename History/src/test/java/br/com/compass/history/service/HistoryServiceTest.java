package br.com.compass.history.service;

import br.com.compass.history.entity.History;
import br.com.compass.history.repository.HistoryRepository;
import br.com.compass.history.service.assembler.HistoryDTOAssembler;
import br.com.compass.history.service.response.HistoryResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class HistoryServiceTest {

    static final Long ID = 1L;

    @InjectMocks
    private HistoryService service;
    @Mock
    private HistoryRepository repository;
    @Mock
    private HistoryDTOAssembler assembler;
    @Mock
    private Pageable pageable;

    @Test
    void shouldFindAllHistories_success() {
        Page<History> histories = new PageImpl<>(List.of(new History()));
        List<HistoryResponseDTO> historyResponseDTOS = List.of(new HistoryResponseDTO());

        Mockito.when(repository.findByOrderByHistoryDateDesc(any(Pageable.class))).thenReturn(histories);
        Mockito.when(assembler.toCollectionModel(histories.getContent())).thenReturn(historyResponseDTOS);

        List<HistoryResponseDTO> all = service.findAll(pageable);

        Assertions.assertEquals(all, historyResponseDTOS);
    }

    @Test
    void shouldVerifyHistoryResponseDTO_withStatus(){
        Page<History> histories = new PageImpl<>(List.of(new History()));
        List<HistoryResponseDTO> historyResponseDTOS = List.of(new HistoryResponseDTO());

        Mockito.when(repository.findByOrderByHistoryDateDesc(any(Pageable.class))).thenReturn(histories);
        Mockito.when(assembler.toCollectionModel(histories.getContent())).thenReturn(historyResponseDTOS);

        List<HistoryResponseDTO> all = service.verifyHistoryResponseDTO(pageable,null);

        Assertions.assertEquals(all, historyResponseDTOS);
    }

    @Test
    void shouldVerifyHistoryResponseDTO_findByHistoryDate() {
        History history = new History();
        history.setHistoryDate(LocalDate.now());
        HistoryResponseDTO response = new HistoryResponseDTO();

        Mockito.when(repository.findByHistoryDate(any())).thenReturn(history);
        Mockito.when(assembler.toModel(history)).thenReturn(response);

        List<HistoryResponseDTO> historyResponseDTOS = service.verifyHistoryResponseDTO(pageable, history.getHistoryDate());

        Assertions.assertEquals(historyResponseDTOS.get(0), response);
    }
}
