package br.com.compass.order.service;

import br.com.compass.order.entities.Item;
import br.com.compass.order.service.assembler.ItemDTOAssembler;
import br.com.compass.order.service.dto.response.ItemResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
@ExtendWith(MockitoExtension.class)
public class ItemAssemblerTest {
    @InjectMocks
    private ItemDTOAssembler assembler;

    @Spy
    private ModelMapper mapper;
    @Test
    void toModelSuccess(){
        Item item = new Item();
        item.setName("name");
        ItemResponseDTO itemResponseDTO = assembler.toModel(item);
        Assertions.assertEquals(item.getName(), itemResponseDTO.getName());
    }
}
