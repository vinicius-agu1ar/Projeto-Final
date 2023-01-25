package br.com.compass.order.controller;

import br.com.compass.order.service.ItemService;
import br.com.compass.order.service.dto.response.ItemResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/itens")
public class ItemController {

    private final ItemService service;

    @PatchMapping("/{id}/{name}")
    public ResponseEntity<ItemResponseDTO> patch(@PathVariable("id") Long id, @PathVariable("name") String name){
        log.info("Atualizando Item por id...");
        ItemResponseDTO response = service.patch(id, name);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
