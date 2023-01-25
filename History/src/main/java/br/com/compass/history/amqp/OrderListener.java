package br.com.compass.history.amqp;

import br.com.compass.history.entity.History;
import br.com.compass.history.repository.HistoryRepository;
import br.com.compass.history.service.HistoryService;
import br.com.compass.history.service.assembler.HistoryDTOAssembler;
import br.com.compass.history.service.disassembler.HistoryInputDisassembler;
import br.com.compass.history.service.response.HistoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class OrderListener {

    private final HistoryService service;
    private final HistoryInputDisassembler disassembler;

    @RabbitListener(queues = "order_complete")
    public void messegerReciever(HistoryResponseDTO response){
        response.setHistoryDate(LocalDate.now());
        String mensagem = """
                Order Id: %s
                Order Total: %s
                History retrieved: %s
                """.formatted(response.getOrderId(),
                response.getTotal(),
                response.getHistoryDate());
        System.out.println("Order recieved: " + mensagem);
        History history = disassembler.toDomainObject(response);
        service.save(history);
    }
}
