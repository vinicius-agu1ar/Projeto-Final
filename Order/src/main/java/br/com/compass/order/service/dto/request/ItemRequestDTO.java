package br.com.compass.order.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequestDTO {
    @NotBlank
    private String name;
    @NotNull
    private LocalDateTime expirationDate;
    @NotNull
    private BigDecimal price;
    @NotBlank
    private String description;
}
