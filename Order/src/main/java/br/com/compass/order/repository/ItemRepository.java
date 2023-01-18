package br.com.compass.order.repository;

import br.com.compass.order.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ItemRepository extends JpaRepository <Item, Long> {

    List<Item> findByPrice (BigDecimal price);
}
