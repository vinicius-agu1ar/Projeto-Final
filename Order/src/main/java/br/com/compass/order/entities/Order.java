package br.com.compass.order.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "TOTAL")
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @OneToOne
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;
}
