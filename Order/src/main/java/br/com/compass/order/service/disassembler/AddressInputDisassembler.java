package br.com.compass.order.service.disassembler;

import br.com.compass.order.entities.Address;
import br.com.compass.order.service.dto.request.AddressRequestDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressInputDisassembler {

    private final ModelMapper modelMapper;

    public Address toDomainObject(AddressRequestDTO addressRequestDTO){
        return modelMapper.map(addressRequestDTO, Address.class);
    }

    public void copyToDomainObject(AddressRequestDTO addressRequestDTO, Address address){
        modelMapper.map(addressRequestDTO, address);
    }
}
