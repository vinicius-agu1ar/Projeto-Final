package br.com.compass.order.service;

import br.com.compass.order.exceptions.response.AddressNotFoundException;
import br.com.compass.order.entities.Address;
import br.com.compass.order.repository.AddressRepository;
import br.com.compass.order.service.assembler.AddressDTOAssembler;
import br.com.compass.order.service.dto.response.AddressResponseDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressDTOAssembler assembler;

    private final AddressRepository repository;

    public AddressResponseDTO findBy(Integer cep) {
        log.info("Chamando método findBy - Service Order");
        Address address = fetchOrFail(cep);
        return assembler.toModel(address);
    }

    private Address fetchOrFail(Integer cep){
        log.info("Chamando método fetchOrFail - Service Order");
        return Optional.ofNullable(repository.findByCep(cep)).orElseThrow(AddressNotFoundException::new);
    }
}
