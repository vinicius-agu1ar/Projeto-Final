package br.com.compass.history.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class History {

    @Id
    private String id;
    private Integer orderId;
    private BigDecimal total;
    private LocalDate historyDate;
}
