package br.com.compass.order.service;

import br.com.compass.order.client.AddressClient;
import br.com.compass.order.entities.Address;
import br.com.compass.order.entities.Item;
import br.com.compass.order.entities.Order;
import br.com.compass.order.exceptions.response.EntityInUseException;
import br.com.compass.order.exceptions.response.InvalidDateUsageException;
import br.com.compass.order.exceptions.response.OrderNotFoundException;
import br.com.compass.order.repository.OrderRepository;
import br.com.compass.order.service.assembler.OrderDTOAssembler;
import br.com.compass.order.service.disassembler.OrderInputDisassembler;
import br.com.compass.order.service.dto.request.ItemRequestDTO;
import br.com.compass.order.service.dto.request.OrderRequestDTO;
import br.com.compass.order.service.dto.response.AddressResponseDTO;
import br.com.compass.order.service.dto.response.OrderResponseDTO;
import br.com.compass.order.service.dto.response.OrderResumeResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
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

    private final ModelMapper modelMapper;

    private final AddressClient addressClient;


    @Transactional
    public OrderResponseDTO create(OrderRequestDTO request) {
        log.info("Chamando método create - Service Order");
        Address address = getAddress(request);
        List<Item> itemsList = getItems(request);
        Order order = orderBuilder(request, address, itemsList);
        order.getItem().forEach(item -> item.setOrder(order));
        order.setTotal(finalPrice(order));
        repository.saveAndFlush(order);
        return assembler.toModel(order);
    }

    private static Order orderBuilder(OrderRequestDTO request, Address address, List<Item> itemsList) {
        Order order = Order.builder()
                .cpf(request.getCpf())
                .address(address)
                .item(itemsList)
                .build();
        return order;
    }

    private List<Item> getItems(OrderRequestDTO request) {
        List<ItemRequestDTO> items = request.getItem();
        List<Item> itemsList = items.stream().map(item -> {
            Item itemEntity = modelMapper.map(item, Item.class);
            itemEntity.setCreationDate(LocalDate.now());
            if(itemEntity.getExpirationDate().isBefore(itemEntity.getCreationDate())){
                throw new InvalidDateUsageException();
            }
            return itemEntity;
        }).toList();
        return itemsList;
    }

    private Address getAddress(OrderRequestDTO request) {
        AddressResponseDTO addressResponseDTO = addressClient.findByCep(request.getAddress().getCep());
        Address address = modelMapper.map(addressResponseDTO, Address.class);
        address.setNumero(request.getAddress().getNumero());
        return address;
    }

    public OrderResumeResponseDTO resumeResponse(OrderResponseDTO response){
        OrderResumeResponseDTO resumeResponse = assembler.toResumeModel(response);
        return resumeResponse;
    }

    @Transactional
    public Order create(Order order) {
        log.info("Chamando método create (salvando no repository) - Service Order");
        try {
            return repository.save(order);
        } catch (DataIntegrityViolationException ex) {
            throw new EntityInUseException();
        }
    }

    public List<OrderResponseDTO> findAll(Pageable pageable) {
        log.info("Chamando método findAll - Service Order");
        Page<Order> pageOrders = repository.findAll(pageable);
        return assembler.toCollectionModel(pageOrders.getContent());
    }

    public List<OrderResponseDTO> verifyOrderResponseDTO(Pageable pageable, String cpf) {
        log.info("Chamando método verifyOrderResponseDTO - Service Order");
        if (cpf == null) {
            return findAll(pageable);
        } else {
            List<Order> orders = repository.findByCpf(cpf, pageable).getContent();
            return assembler.toCollectionModel(orders);
        }
    }

    public OrderResponseDTO findBy(Long id) {
        log.info("Chamando método findBy - Service Order");
        Order order = fetchOrFail(id);
        return assembler.toModel(order);
    }

    private Order fetchOrFail(Long id) {
        log.info("Chamando método fetchOrFail - Service Order");
        return repository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    public OrderResponseDTO update(Long id, OrderRequestDTO request) {
        log.info("Chamando método update - Service Order");
        Order order = fetchOrFail(id);
        BigDecimal bigDecimal = finalPrice(order);
        order.setTotal(bigDecimal);
        disassembler.copyToDomainObject(request, order);
        repository.save(order);
        return assembler.toModel(order);
    }

    @Transactional
    public void delete(Long id) {
        log.info("Chamando método delete (excluindo no repository) - Service Order");
        try {
            repository.deleteById(id);
            repository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new OrderNotFoundException();
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException();
        }
    }

    public BigDecimal finalPrice (Order order){
        List<Item> items = order.getItem();
        BigDecimal valor = items.stream().map(item -> {
            Item itemEntity = modelMapper.map(item, Item.class);
            return itemEntity.getPrice();
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
        return valor;
    }
}
