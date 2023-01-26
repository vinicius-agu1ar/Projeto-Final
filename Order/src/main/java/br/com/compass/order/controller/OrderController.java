package br.com.compass.order.controller;

import br.com.compass.order.service.OrderService;
import br.com.compass.order.service.dto.request.OrderRequestDTO;
import br.com.compass.order.service.dto.response.OrderResponseDTO;
import br.com.compass.order.service.dto.response.OrderResumeResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pedidos")
public class OrderController {

    private final OrderService service;
    @Autowired
    private final RabbitTemplate template;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> create(@RequestBody @Valid OrderRequestDTO request) {
        log.info("Criando um novo Order...");
        OrderResponseDTO response = service.create(request);
        OrderResumeResponseDTO resumeResponse = service.resumeResponse(response);
        template.convertAndSend("order_complete", resumeResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> findAll(@PageableDefault(size = 10) Pageable pagination, @RequestParam(required = false, name = "cpf") String cpf) {
        log.info("Listando Orders com página de {} registros...", pagination.getPageSize());
        List<OrderResponseDTO> responsePage = service.verifyOrderResponseDTO(pagination, cpf);
        return ResponseEntity.status(HttpStatus.OK).body(responsePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> findBy(@PathVariable("id") Long id){
        log.info("Buscando Order por id...");
        OrderResponseDTO orderResponseDTO = service.findBy(id);
        return ResponseEntity.status(HttpStatus.OK ).body(orderResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> update(@PathVariable("id") Long id, @RequestBody @Valid OrderRequestDTO request){
        log.info("Atualizando Order por id...");
        OrderResponseDTO orderResponseDTO = service.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDTO);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete (@PathVariable("id") Long id){
        log.info("Excluindo uma Order por Id...");
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
