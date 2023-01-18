package br.com.compass.order.service.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class OrderResponseDTO {

    private Long id;

    private String cpf;

    private BigDecimal total;

    private List<ItemResponseDTO> item;

    private AddressResponseDTO address;
}
