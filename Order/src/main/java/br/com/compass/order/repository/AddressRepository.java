package br.com.compass.order.repository;

import br.com.compass.order.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository <Address, Long> {

    Address findByCep (String cep);
}
