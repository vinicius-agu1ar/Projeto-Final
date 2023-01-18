package br.com.compass.order.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "ADDRESS")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "STREET")
    private String logradouro;

    @Column(name = "NUMBER")
    private Integer numero;

    @Column(name = "DISTRICT")
    private String bairro;

    @Column(name = "CITY")
    private String localidade;

    @Column(name = "STATE")
    private String uf;

    @Column(name = "ZIPCODE")
    private String cep;
}
