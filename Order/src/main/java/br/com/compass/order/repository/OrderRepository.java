package br.com.compass.order.repository;

import br.com.compass.order.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository <Order, Long> {

    Order findByCpf (String cpf);
}
