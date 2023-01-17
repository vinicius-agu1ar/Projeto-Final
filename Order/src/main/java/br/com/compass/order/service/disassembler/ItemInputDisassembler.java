package br.com.compass.order.service.disassembler;

import br.com.compass.order.entities.Item;
import br.com.compass.order.service.dto.request.ItemRequestDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemInputDisassembler {

    private final ModelMapper modelMapper;

    public Item toDomainObject(ItemRequestDTO itemRequestDTO){
        return modelMapper.map(itemRequestDTO, Item.class);
    }

    public void copyToDomainObject(ItemRequestDTO itemRequestDTO, Item item){
        modelMapper.map(itemRequestDTO, item);
    }
}
