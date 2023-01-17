package br.com.compass.order.service.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ItemResponseDTO {

    private Long id;

    private String name;

    private LocalDateTime creationDate;

    private LocalDateTime expirationDate;

    private BigDecimal price;

    private String description;
}
