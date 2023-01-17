package br.com.compass.order.service.dto.response;

import br.com.compass.order.entities.Address;
import br.com.compass.order.entities.Item;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderResponseDTO {

    private Long id;

    private String cpf;

    private BigDecimal total;

    private Item item;

    private Address address;
}
