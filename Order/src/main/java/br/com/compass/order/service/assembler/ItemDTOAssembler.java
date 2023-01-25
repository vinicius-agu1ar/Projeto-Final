package br.com.compass.order.service.assembler;

import br.com.compass.order.entities.Item;
import br.com.compass.order.service.dto.response.ItemResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemDTOAssembler {
    private final ModelMapper modelMapper;

    public ItemResponseDTO toModel(Item item){
            return modelMapper.map(item, ItemResponseDTO.class);
    }

}