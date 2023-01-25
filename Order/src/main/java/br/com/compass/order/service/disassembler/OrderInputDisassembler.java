package br.com.compass.order.service.disassembler;

import br.com.compass.order.entities.Order;
import br.com.compass.order.service.dto.request.OrderRequestDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderInputDisassembler {

    private final ModelMapper modelMapper;
    public Order toDomainObject(OrderRequestDTO orderRequestDTO) {
        return modelMapper.map(orderRequestDTO, Order.class);
    }
    public void copyToDomainObject(OrderRequestDTO orderRequestDTO, Order order){
        modelMapper.map(orderRequestDTO, order);
    }
}
