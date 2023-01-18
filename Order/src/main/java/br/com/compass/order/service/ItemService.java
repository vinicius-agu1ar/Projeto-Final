package br.com.compass.order.service;

import br.com.compass.order.entities.Item;
import br.com.compass.order.exceptions.response.EntityInUseException;
import br.com.compass.order.exceptions.response.ItemNotFoundException;
import br.com.compass.order.repository.ItemRepository;
import br.com.compass.order.service.assembler.ItemDTOAssembler;
import br.com.compass.order.service.disassembler.ItemInputDisassembler;
import br.com.compass.order.service.dto.request.ItemRequestDTO;
import br.com.compass.order.service.dto.response.ItemResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemDTOAssembler assembler;

    private final ItemRepository repository;

    private final ItemInputDisassembler disassembler;
    @Transactional
    public ItemResponseDTO create (ItemRequestDTO request) {
        log.info("Chamando método create - Service Order");
        Item item = disassembler.toDomainObject(request);
        item = repository.save(item);
        return assembler.toModel(item);
    }

    public Page<ItemResponseDTO> findAll(Pageable pageable) {

        Page<Item> itemPage = repository.findAll(pageable);
        List<ItemResponseDTO> itemResponseDTOS = assembler.toCollectionModel(itemPage.getContent());
        return new PageImpl<>(itemResponseDTOS, pageable, itemPage.getTotalElements());
    }

    public ItemResponseDTO findBy(Long id) {
        log.info("Chamando método findBy - Service Item");
        Item item = fetchOrFail(id);
        return assembler.toModel(item);
    }

    public Item fetchOrFail(Long id){
        log.info("Chamando método fetchOrFail - Service Item");
        return repository.findById(id).orElseThrow(ItemNotFoundException::new);
    }

    @Transactional
    public ItemResponseDTO update(Long id, ItemRequestDTO request) {
        log.info("Chamando método update - Service Item");
        Item item = fetchOrFail(id);
        disassembler.copyToDomainObject(request,item);
        item = repository.save(item);
        return assembler.toModel(item);
    }

    @Transactional
    public void delete(Long id){
        log.info("Chamando método delete - Service Item");
        try{
            repository.deleteById(id);
            repository.flush();
        }catch (EmptyResultDataAccessException e) {
            throw new ItemNotFoundException();
        }catch (DataIntegrityViolationException e) {
            throw new EntityInUseException();
        }
    }
}
