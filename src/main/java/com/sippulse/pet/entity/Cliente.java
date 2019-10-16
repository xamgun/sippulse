package com.sippulse.pet.entity;



import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Cliente
 */
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "cpf")
    private String cpf;

    @OneToMany(mappedBy = "cliente")
    private Set<Agendamento> consultas = new HashSet<>();

    @OneToMany(mappedBy = "cliente")
    private Set<Pet> pets = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Cliente nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public Cliente email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public Cliente endereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public Cliente telefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Set<Agendamento> getConsultas() {
        return consultas;
    }

    public Cliente consultas(Set<Agendamento> agendamentos) {
        this.consultas = agendamentos;
        return this;
    }

    public Cliente addConsulta(Agendamento agendamento) {
        this.consultas.add(agendamento);
        agendamento.setCliente(this);
        return this;
    }

    public Cliente removeConsulta(Agendamento agendamento) {
        this.consultas.remove(agendamento);
        agendamento.setCliente(null);
        return this;
    }

    public void setConsultas(Set<Agendamento> agendamentos) {
        this.consultas = agendamentos;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public Cliente pets(Set<Pet> pets) {
        this.pets = pets;
        return this;
    }

    public Cliente addPet(Pet pet) {
        this.pets.add(pet);
        pet.setCliente(this);
        return this;
    }

    public Cliente removePet(Pet pet) {
        this.pets.remove(pet);
        pet.setCliente(null);
        return this;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cliente)) {
            return false;
        }
        return id != null && id.equals(((Cliente) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Cliente{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", email='" + getEmail() + "'" +
            ", endereco='" + getEndereco() + "'" +
            ", telefone='" + getTelefone() + "'" +
                ", cpf='" + getCpf() + "'" +
            "}";
    }
}
