package com.sippulse.pet.service;

import com.sippulse.pet.entity.Agendamento;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Agendamento}.
 */
public interface AgendamentoService {

    /**
     * Save a agendamento.
     *
     * @param agendamento the entity to save.
     * @return the persisted entity.
     */
    Agendamento save(Agendamento agendamento);

    /**
     * Get all the agendamentos.
     *
     * @return the list of entities.
     */
    List<Agendamento> findAll();


    /**
     * Get the "id" agendamento.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Agendamento> findOne(Long id);

    /**
     * Delete the "id" agendamento.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
