package com.sippulse.pet.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Agendamento.
 */
@Entity
@Table(name = "agendamento")
public class Agendamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data")
    private Instant data;

    @ManyToOne
    @JsonIgnoreProperties("agendamentos")
    private Cliente cliente;

    @OneToOne
    @JoinColumn(unique = true)
    private Pet pet;

    @OneToOne
    @JoinColumn(unique = true)
    private Usuario usuario;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getData() {
        return data;
    }

    public Agendamento data(Instant data) {
        this.data = data;
        return this;
    }

    public void setData(Instant data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Agendamento cliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pet getPet() {
        return pet;
    }

    public Agendamento pet(Pet pet) {
        this.pet = pet;
        return this;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Agendamento usuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Agendamento)) {
            return false;
        }
        return id != null && id.equals(((Agendamento) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Agendamento{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            "}";
    }
}
