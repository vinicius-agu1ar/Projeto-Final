package br.com.compass.history.service.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class HistoryResponseDTO {
    private String id;
    private Integer orderId;
    private BigDecimal total;
    private LocalDate historyDate;
}
