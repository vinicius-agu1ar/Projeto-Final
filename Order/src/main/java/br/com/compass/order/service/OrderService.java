package br.com.compass.order.service;

import br.com.compass.order.entities.Order;
import br.com.compass.order.exceptions.response.EntityInUseException;
import br.com.compass.order.exceptions.response.OrderNotFoundException;
import br.com.compass.order.service.assembler.OrderDTOAssembler;
import br.com.compass.order.service.disassembler.OrderInputDisassembler;
import br.com.compass.order.service.dto.request.OrderRequestDTO;
import br.com.compass.order.service.dto.response.OrderResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.compass.order.repository.OrderRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    private final OrderDTOAssembler assembler;

    private final OrderInputDisassembler disassembler;

    @Transactional
    public OrderResponseDTO create (OrderRequestDTO request) {
        log.info("Chamando método create - Service Order");
        Order order = disassembler.toDomainObject(request);
        order = create(order);
        return assembler.toModel(order);
    }

    @Transactional
    public Order create(Order order){
        log.info("Chamando método create (salvando no repository) - Service Order");
        try {
            return repository.save(order);
        } catch (DataIntegrityViolationException ex) {
            throw new EntityInUseException();
        }
    }

    public List<OrderResponseDTO> findAll(Pageable pageable) {
        log.info("Chamando método findAll - Service Order");
        Page<Order> pageCompanies = repository.findAll(pageable);
        return assembler.toCollectionModel(pageCompanies.getContent());
    }

    public List<OrderResponseDTO> verifyOrderResponseDTO(Pageable pageable, String cpf) {
        log.info("Chamando método verifyOrderResponseDTO - Service Order");
        if(cpf != null){
            List<OrderResponseDTO> list = new ArrayList<>();
            OrderResponseDTO byCpf = findByCpf(cpf);
            list.add(byCpf);
            return list;
        }else{
            return findAll(pageable);
        }
    }

    public OrderResponseDTO findByCpf(String cpf) {
        log.info("Chamando método findByName - Service Order");
        Order order = fetchOrFail(cpf);
        return assembler.toModel(order);
    }

    private Order fetchOrFail(String cpf){
        log.info("Chamando método fetchOrFail - Service Order");
        return Optional.ofNullable(repository.findByCpf(cpf)).orElseThrow(OrderNotFoundException::new);
    }

    public OrderResponseDTO findBy(Long id) {
        log.info("Chamando método findBy - Service Order");
        Order order = fetchOrFail(id);
        return assembler.toModel(order);
    }

    private Order fetchOrFail(Long id){
        log.info("Chamando método fetchOrFail - Service Order");
        return repository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    public OrderResponseDTO update(Long id, OrderRequestDTO request) {
        log.info("Chamando método update - Service Order");
        Order order = fetchOrFail(id);
        disassembler.copyToDomainObject(request,order);
        order = create(order);
        return assembler.toModel(order);
    }

    @Transactional
    public void delete(Long id){
        log.info("Chamando método delete (excluindo no repository) - Service Order");
        try{
            repository.deleteById(id);
            repository.flush();
        }catch (EmptyResultDataAccessException e) {
            throw new OrderNotFoundException();
        }catch (DataIntegrityViolationException e) {
            throw new EntityInUseException();
        }
    }
}