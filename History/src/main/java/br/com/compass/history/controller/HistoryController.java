package br.com.compass.history.controller;

import br.com.compass.history.service.HistoryService;
import br.com.compass.history.service.response.HistoryResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pedidos")
public class HistoryController {

    private final HistoryService service;

    @GetMapping
    public ResponseEntity<List<HistoryResponseDTO>> findAll(@PageableDefault(size = 10) Pageable pagination, @RequestParam(required = false, name = "historyDate")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate historyDate) {
        log.info("Listando Orders com página de {} registros...", pagination.getPageSize());
        List<HistoryResponseDTO> responsePage = service.verifyHistoryResponseDTO(pagination, historyDate);
        return ResponseEntity.status(HttpStatus.OK).body(responsePage);
    }
}
