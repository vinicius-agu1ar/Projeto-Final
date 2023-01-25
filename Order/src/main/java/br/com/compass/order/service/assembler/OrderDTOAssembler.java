package br.com.compass.order.service.assembler;

import br.com.compass.order.entities.Order;
import br.com.compass.order.service.dto.response.OrderResponseDTO;
import br.com.compass.order.service.dto.response.OrderResumeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderDTOAssembler {
    private final ModelMapper modelMapper;

    public OrderResponseDTO toModel(Order order){
            return modelMapper.map(order, OrderResponseDTO.class);
    }

    public OrderResumeResponseDTO toResumeModel(OrderResponseDTO response){
        return modelMapper.map(response, OrderResumeResponseDTO.class);
    }

    public List<OrderResponseDTO> toCollectionModel(List<Order> orders){
        return orders.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}