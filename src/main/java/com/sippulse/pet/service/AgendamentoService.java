package com.sippulse.pet.service;

import com.sippulse.pet.entity.Agendamento;

import java.util.List;

/**
 * Service Interface para gerenciar {@link Agendamento}.
 */
public interface AgendamentoService {

    /**
     * Salva agendamento.
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
    Agendamento findOneById(Long id);

    /**
     * Delete the "id" agendamento.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

}
