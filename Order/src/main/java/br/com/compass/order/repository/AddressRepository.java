package br.com.compass.order.repository;

import br.com.compass.order.entities.Address;
import br.com.compass.order.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository <Address, Long> {

    Address findByCep (Integer cep);
}
