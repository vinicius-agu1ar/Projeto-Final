package br.com.compass.order.controller;

import br.com.compass.order.service.AddressService;
import br.com.compass.order.service.dto.response.AddressResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService service;

    @GetMapping("/{cep}")
    public ResponseEntity<AddressResponseDTO> findBy(@PathVariable("cep") Integer cep){
        log.info("Buscando Address por cep...");
        AddressResponseDTO orderResponseDTO = service.findBy(cep);
        return ResponseEntity.status(HttpStatus.OK ).body(orderResponseDTO);
    }
}
