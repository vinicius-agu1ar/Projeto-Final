package br.com.compass.order.repository;

import br.com.compass.order.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository <Item, Long> {

}
