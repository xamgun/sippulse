package com.sippulse.pet.controller;

import com.sippulse.pet.entity.Agendamento;
import com.sippulse.pet.service.AgendamentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sippulse.pet.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * REST controller para cadastrar, pesquisar e atualizar agendamentos de
 * consultas {@link com.sippulse.pet.entity.Agendamento}.
 */
@RestController
@RequestMapping("/api")
public class AgendamentoController {

    String applicationName = "Pet";

    private final Logger log = LoggerFactory.getLogger(AgendamentoController.class);

    private static final String ENTITY_NAME = "agendamento";

    @Autowired
    private final AgendamentoService agendamentoService;
    
    @Autowired
    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }


    /**
     * {@code POST  /agendamentos} : Cria novo agendamento.
     *
     * @param agendamento para criar agendamento.
     * @return the {@link ResponseEntity} com status {@code 201 (Created)} e com os dados do novo agendamento,
     * ou status {@code 400 (Bad Request)} se agendamento com este ID já existir.
     * @throws URISyntaxException se URI syntax estiver incorreta.
     */
    @RequestMapping(value ="/agendamentos", method = RequestMethod.POST)
    public ResponseEntity<Agendamento> createAgendamento(@RequestBody Agendamento agendamento) throws URISyntaxException {
        log.debug("REST request to save Agendamento : {}", agendamento);
        Agendamento result = agendamentoService.save(agendamento);
        return ResponseEntity.created(new URI("/api/agendamentos/" + result.getId()))
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, agendamento.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /agendamentos} : Atualiza um agendamento existente.
     *
     * @param agendamento para atualizar agendamento.
     * @return {@link ResponseEntity} com status {@code 200 (OK)} e com os dados do novo agendamento,
     * ou status {@code 400 (Bad Request)} se o agendamento não for válido,
     * ou status {@code 500 (Internal Server Error)} se a atualização do agendamento não for possível.
     * @throws URISyntaxException se URI syntax estiver incorreta.
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
     * {@code GET  /agendamentos} : retorna todos agendamentos.
     *
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} com a listade agendamentos no corpo da resposta.
     */
    @RequestMapping(value="/agendamentos", method = RequestMethod.GET)
    public List<Agendamento> getAllAgendamentos() {
        log.debug("REST request to get all Agendamentos");
        return agendamentoService.findAll();
    }

    /**
     * {@code GET  /agendamentos/:id} : retorna um agendamento pelo id.
     *
     * @param id o id do agendamento requerido.
     * @return  {@link ResponseEntity} com status {@code 200 (OK)} com os dados do agendamento, ou status {@code 404 (Not Found)}.
     */
    @RequestMapping(value="/agendamentos/{id}", method = RequestMethod.GET)
    public ResponseEntity<Agendamento> getAgendamento(@PathVariable Long id) {
        log.debug("REST request to get Agendamento : {}", id);
        Agendamento agendamento = agendamentoService.findOneById(id);
        return ResponseEntity.ok(agendamento);
    }

    /**
     * {@code DELETE  /agendamentos/:id} : deleta agendamento pelo id passado.
     *
     * @param id o id do agendamento para ser deletado.
     * @return o {@link ResponseEntity} com status {@code 204 (NO_CONTENT)}.
     */
    @RequestMapping(value="/agendamentos/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAgendamento(@PathVariable Long id) {
        log.debug("REST request to delete Agendamento : {}", id);
        agendamentoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
