package br.com.compass.order.service.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponseDTO {

    private Long id;

    private String logradouro;

    private Integer numero;

    private String bairro;

    private String localidade;

    private String uf;

    private String cep;
}
