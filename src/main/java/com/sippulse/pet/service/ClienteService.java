package com.sippulse.pet.service;

import com.sippulse.pet.entity.Cliente;

import java.util.List;

/**
 * Service Interface for managing {@link Cliente}.
 */
public interface ClienteService {

    /**
     * Save a cliente.
     *
     * @param cliente the entity to save.
     * @return the persisted entity.
     */
    Cliente save(Cliente cliente);

    /**
     * Get all the clientes.
     *
     * @return the list of entities.
     */
    List<Cliente> findAll();


    /**
     * Get the "id" cliente.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Cliente findOne(Long id);

    /**
     * Delete the "id" cliente.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
