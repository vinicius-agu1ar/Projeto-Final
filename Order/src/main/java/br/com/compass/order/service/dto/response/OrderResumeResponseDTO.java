package br.com.compass.order.service.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderResumeResponseDTO {
    private Long orderId;

    private BigDecimal total;
}
