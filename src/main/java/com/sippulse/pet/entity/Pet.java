package com.sippulse.pet.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A Pet.
 */
@Entity
@Table(name = "pet")
public class Pet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "especie_animal")
    private String especieAnimal;

    @Column(name = "raca")
    private String raca;

    @ManyToOne
    @JsonIgnoreProperties("pets")
    private Cliente cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Pet nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecieAnimal() {
        return especieAnimal;
    }

    public Pet especieAnimal(String especieAnimal) {
        this.especieAnimal = especieAnimal;
        return this;
    }

    public void setEspecieAnimal(String especieAnimal) {
        this.especieAnimal = especieAnimal;
    }

    public String getRaca() {
        return raca;
    }

    public Pet raca(String raca) {
        this.raca = raca;
        return this;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Pet cliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pet)) {
            return false;
        }
        return id != null && id.equals(((Pet) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Pet{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", especieAnimal='" + getEspecieAnimal() + "'" +
            ", raca='" + getRaca() + "'" +
            "}";
    }
}
