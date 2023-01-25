package br.com.compass.order.service;

import br.com.compass.order.entities.Item;
import br.com.compass.order.repository.ItemRepository;
import br.com.compass.order.service.assembler.ItemDTOAssembler;
import br.com.compass.order.service.dto.response.ItemResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    static final Long ID = 1L;
    @InjectMocks
    private ItemService service;
    @Mock
    private ItemDTOAssembler assembler;
    @Mock
    private ItemRepository repository;

    @Test
    void shouldPatchItem_success() {
        Item item = new Item();
        String name = "";
        ItemResponseDTO response = new ItemResponseDTO();

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(item));
        Mockito.when(repository.save(any())).thenReturn(item);
        Mockito.when(assembler.toModel(any())).thenReturn(response);

        ItemResponseDTO itemResponseDTO = service.patch(ID, name);
        assertEquals(response, itemResponseDTO);
        verify(repository).save(any());
    }
}
