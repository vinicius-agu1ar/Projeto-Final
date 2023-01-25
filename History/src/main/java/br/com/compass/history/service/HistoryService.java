package br.com.compass.history.service;

import br.com.compass.history.entity.History;
import br.com.compass.history.repository.HistoryRepository;
import br.com.compass.history.service.assembler.HistoryDTOAssembler;
import br.com.compass.history.service.response.HistoryResponseDTO;
import br.com.compass.history.exceptions.responses.HistoryNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository repository;

    private final HistoryDTOAssembler assembler;

    public List<HistoryResponseDTO> findAll(Pageable pageable) {
        log.info("Chamando método findAll - Service History");
        Page<History> historyPage = repository.findByOrderByHistoryDateDesc(pageable);
        return assembler.toCollectionModel(historyPage.getContent());
    }

    public List<HistoryResponseDTO> verifyHistoryResponseDTO(Pageable pageable, LocalDate historyDate) {
        log.info("Chamando método verifyHistoryResponseDTO - Service History");
        if (historyDate != null) {
            List<HistoryResponseDTO> list = new ArrayList<>();
            HistoryResponseDTO byDate = findByHistoryDate(historyDate);
            list.add(byDate);
            return list;
        } else {
            return findAll(pageable);
        }
    }

    public HistoryResponseDTO findByHistoryDate(LocalDate historyDate) {
        log.info("Chamando método findByCpf - Service History");
        History history = fetchOrFail(historyDate);
        return assembler.toModel(history);
    }

    private History fetchOrFail(LocalDate historyDate) {
        log.info("Chamando método fetchOrFail - Service History");
        return Optional.ofNullable(repository.findByHistoryDate(historyDate)).orElseThrow(HistoryNotFoundException::new);
    }

    public History save(History history){
        log.info("Chamando método save - Service History");
        return repository.save(history);
    }
}
