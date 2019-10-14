package com.sippulse.pet.controller;

import com.sippulse.pet.HeaderUtil;
import com.sippulse.pet.entity.Cliente;
import com.sippulse.pet.service.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * REST controller para cadastrar, pesquisar e atualizar {@link com.sippulse.pet.entity.Cliente}.
 */
@RestController
@RequestMapping("/api")
public class ClienteController {

    private final Logger log = LoggerFactory.getLogger(ClienteController.class);

    private static final String ENTITY_NAME = "cliente";

    @Value("Pet")
    private String applicationName;

    @Autowired
    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    /**
     * {@code POST  /clientes} : Cria novo cliente.
     *
     * @param cliente o cliente a ser criado.
     * @return o {@link ResponseEntity} com status {@code 201 (Created)} e com os dados do novo cliente,
     * ou com status {@code 400 (Bad Request)} se o cliente com ID já existir.
     * @throws URISyntaxException se a syntax URI estiver incorreta.
     */
    @RequestMapping(value = "/clientes", method = RequestMethod.POST)
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) throws URISyntaxException {
        log.debug("REST request to save Cliente : {}", cliente);
        if (cliente.getId() != null) {
            ResponseEntity.badRequest().build();
        }
        Cliente result = clienteService.save(cliente);
        return ResponseEntity.created(new URI("/api/clientes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /clientes} : Atualiza um cliente existente.
     *
     * @param cliente o cliente que será atualizado.
     * @return the {@link ResponseEntity} com status {@code 200 (OK)} e com os dados atualizados do cliente,
     * ou status {@code 400 (Bad Request)} se o cliente for inválido,
     * ou status {@code 500 (Internal Server Error)} se o cliente não puder ser atualizado.
     * @throws URISyntaxException se a syntax URI estiver incorreta.
     */
    @RequestMapping(value = "/clientes", method = RequestMethod.PUT)
    public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente cliente) throws URISyntaxException {
        log.debug("REST request to update Cliente : {}", cliente);
        if (cliente.getId() == null) {
            ResponseEntity.badRequest().build();
        }
        Cliente result = clienteService.save(cliente);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cliente.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /clientes} : retorna todos os clientes.
     *
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e alista dos clientes no corpo do retorno.
     */
    @RequestMapping(value = "/clientes", method = RequestMethod.GET)
    public List<Cliente> getAllClientes() {
        log.debug("REST request to get all Clientes");
        return clienteService.findAll();
    }

    /**
     * {@code GET  /clientes/:id} : retorna cliente pelo id.
     *
     * @param id o id do cliente requisitado.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} com os dados do cliente no corpo da resposta,
     * ou com status {@code 404 (Not Found)}.
     */
    @RequestMapping (value = "/clientes/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> getCliente(@PathVariable Long id) {
        log.debug("REST request to get Cliente : {}", id);
        Cliente cliente = clienteService.findOne(id);
        return ResponseEntity.ok(cliente);
    }

    /**
     * {@code DELETE  /clientes/:id} : deleta um cliente pelo id.
     *
     * @param id o id do cliente a ser deletetado.
     * @return o {@link ResponseEntity} com status {@code 204 (NO_CONTENT)}.
     */
    @RequestMapping (value = "/clientes/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        log.debug("REST request to delete Cliente : {}", id);
        clienteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
