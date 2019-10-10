package com.sippulse.pet.controller;

import com.sippulse.pet.entity.Agendamento;
import com.sippulse.pet.service.AgendamentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sippulse.pet.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.sippulse.pet.entity.Agendamento}.
 */
@RestController
@RequestMapping("/api")
public class AgendamentoController {

    String applicationName = "Pet";

    private final Logger log = LoggerFactory.getLogger(AgendamentoController.class);

    private static final String ENTITY_NAME = "agendamento";

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    /**
     * {@code POST  /agendamentos} : Create a new agendamento.
     *
     * @param agendamento the agendamento to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new agendamento, or with status {@code 400 (Bad Request)} if the agendamento has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @RequestMapping(value ="/agendamentos", method = RequestMethod.POST)
    public ResponseEntity<Agendamento> createAgendamento(@RequestBody Agendamento agendamento) throws URISyntaxException {
        log.debug("REST request to save Agendamento : {}", agendamento);
        Agendamento result = agendamentoService.save(agendamento);
        return ResponseEntity.created(new URI("/api/agendamentos/" + result.getId()))
            .headers(applicationName, false, ENTITY_NAME, + agendamento.getId().toString()).body(
                );
    }

    /**
     * {@code PUT  /agendamentos} : Updates an existing agendamento.
     *
     * @param agendamento the agendamento to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated agendamento,
     * or with status {@code 400 (Bad Request)} if the agendamento is not valid,
     * or with status {@code 500 (Internal Server Error)} if the agendamento couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @RequestMapping(value="/agendamentos", method = RequestMethod.PUT)
    public ResponseEntity<Agendamento> updateAgendamento(@RequestBody Agendamento agendamento) throws URISyntaxException {
        log.debug("REST request to update Agendamento : {}", agendamento);
        Agendamento result = agendamentoService.save(agendamento);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, agendamento.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /agendamentos} : get all the agendamentos.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of agendamentos in body.
     */
    @GetMapping("/agendamentos")
    public List<Agendamento> getAllAgendamentos() {
        log.debug("REST request to get all Agendamentos");
        return agendamentoService.findAll();
    }

    /**
     * {@code GET  /agendamentos/:id} : get the "id" agendamento.
     *
     * @param id the id of the agendamento to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the agendamento, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/agendamentos/{id}")
    public ResponseEntity<Agendamento> getAgendamento(@PathVariable Long id) {
        log.debug("REST request to get Agendamento : {}", id);
        Optional<Agendamento> agendamento = agendamentoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(agendamento);
    }

    /**
     * {@code DELETE  /agendamentos/:id} : delete the "id" agendamento.
     *
     * @param id the id of the agendamento to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/agendamentos/{id}")
    public ResponseEntity<Void> deleteAgendamento(@PathVariable Long id) {
        log.debug("REST request to delete Agendamento : {}", id);
        agendamentoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
