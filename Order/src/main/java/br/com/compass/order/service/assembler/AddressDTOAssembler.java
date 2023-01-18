package br.com.compass.order.service.assembler;

import br.com.compass.order.entities.Address;
import br.com.compass.order.service.dto.response.AddressResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AddressDTOAssembler {
    private final ModelMapper modelMapper;

    public AddressResponseDTO toModel(Address address){
        return modelMapper.map(address, AddressResponseDTO.class);
    }

    public List<AddressResponseDTO> toCollectionModel (List<Address> addresses) {
        return addresses.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}