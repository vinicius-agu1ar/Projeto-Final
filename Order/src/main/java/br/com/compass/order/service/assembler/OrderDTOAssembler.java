package br.com.compass.order.service.assembler;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import br.com.compass.order.service.dto.response.OrderResponseDTO;
import br.com.compass.order.entities.Order;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderDTOAssembler {
    private final ModelMapper modelMapper;

    public OrderResponseDTO toModel(Order order){
            return modelMapper.map(order, OrderResponseDTO.class);
    }

    public List<OrderResponseDTO> toCollectionModel(List<Order> orders){
        return orders.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}