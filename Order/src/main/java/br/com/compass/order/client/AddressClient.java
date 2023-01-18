package br.com.compass.order.client;

import br.com.compass.order.service.dto.response.AddressResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url= "https://viacep.com.br/ws/" , name = "viacep")
public interface AddressClient {

    @GetMapping("{cep}/json")
    AddressResponseDTO findByCep(@PathVariable("cep") String cep);
}
