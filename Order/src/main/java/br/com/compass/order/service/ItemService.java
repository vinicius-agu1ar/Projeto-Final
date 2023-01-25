package br.com.compass.order.service;

import br.com.compass.order.entities.Item;
import br.com.compass.order.exceptions.response.ItemNotFoundException;
import br.com.compass.order.repository.ItemRepository;
import br.com.compass.order.service.assembler.ItemDTOAssembler;
import br.com.compass.order.service.dto.response.ItemResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemDTOAssembler assembler;
    private final ItemRepository repository;

    @Transactional
    public ItemResponseDTO patch(Long id, String name) {
        log.info("Chamando método update - Service Item");
        Item item = fetchOrFail(id);
        item.setName(name);
        repository.save(item);
        return assembler.toModel(item);
    }

    public Item fetchOrFail(Long id){
        log.info("Chamando método fetchOrFail - Service Item");
        return repository.findById(id).orElseThrow(ItemNotFoundException::new);
    }
}
