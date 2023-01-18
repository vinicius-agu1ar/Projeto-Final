package br.com.compass.order.service.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ItemResponseDTO {

    private Long id;

    private String name;

    private LocalDate creationDate;

    private LocalDate expirationDate;

    private BigDecimal price;

    private String description;
}
