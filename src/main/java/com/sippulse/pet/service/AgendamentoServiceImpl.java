package com.sippulse.pet.service;

import com.sippulse.pet.entity.Agendamento;
import com.sippulse.pet.repository.AgendamentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing {@link Agendamento}.
 */
@Service
@Transactional
public class AgendamentoServiceImpl implements AgendamentoService {

    private final Logger log = LoggerFactory.getLogger(AgendamentoServiceImpl.class);

    @Autowired
    private final AgendamentoRepository agendamentoRepository;

    @Autowired
    public AgendamentoServiceImpl(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    /**
     * Save a agendamento.
     *
     * @param agendamento the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Agendamento save(Agendamento agendamento) {
        log.debug("Request to save Agendamento : {}", agendamento);
        return agendamentoRepository.save(agendamento);
    }

    /**
     * Get all the agendamentos.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Agendamento> findAll() {
        log.debug("Request to get all Agendamentos");
        return agendamentoRepository.findAll();
    }


    /**
     * Delete the agendamento by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Agendamento : {}", id);
        agendamentoRepository.delete(id);
    }

    /**
     * Get one agendamento by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Agendamento findOneById(Long id){
        return agendamentoRepository.findOne(id);
    }
}
