package com.sippulse.pet.controller;

import com.sippulse.pet.HeaderUtil;
import com.sippulse.pet.entity.Pet;
import com.sippulse.pet.service.ClienteService;
import com.sippulse.pet.service.PetService;
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
 * REST controller para cadastrar, pesquisar e atualizar pets {@link com.sippulse.pet.entity.Pet}.
 */
@RestController
@RequestMapping("/api")
public class PetController {

    private final Logger log = LoggerFactory.getLogger(PetController.class);

    private static final String ENTITY_NAME = "pet";

    @Value("Pet")
    private String applicationName;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    /**
     * {@code POST  /pets} : Cria um novo pet.
     *
     * @param pet para criar um pet.
     * @return o {@link ResponseEntity} com status {@code 201 (Created)} e com os dados do pet criado no corpo da resposta,
     * ou com status {@code 400 (Bad Request)} se um pet com este id já existir.
     * @throws URISyntaxException se a syntax URI estiver incorreta.
     */
    @RequestMapping(value = "/pets", method = RequestMethod.POST)
    public ResponseEntity<Pet> createPet(@RequestBody Pet pet) throws URISyntaxException {
        log.debug("REST request to save Pet : {}" + pet);
        if (pet.getId() != null) {
            ResponseEntity.badRequest().build();
        }
        Pet result = petService.save(pet);
        return ResponseEntity.created(new URI("/api/pets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pets} : Atualiza um pet existente.
     *
     * @param pet o pet que será atualizado.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e com os dados do pet atualizado,
     * ou com status {@code 400 (Bad Request)} se o pet for inválido,
     * ou com status {@code 500 (Internal Server Error)} se o pet não puder ser atualizado.
     * @throws URISyntaxException se a syntax URI estiver incorreta.
     */
    @RequestMapping(value = "/pets", method = RequestMethod.PUT)
    public ResponseEntity<Pet> updatePet(@RequestBody Pet pet) throws URISyntaxException {
        log.debug("REST request to update Pet : {}" + pet);
        if (pet.getId() == null) {
            ResponseEntity.badRequest().build();
        }
        Pet result = petService.save(pet);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, pet.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pets} : retorna todos os pets.
     *
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e com a lista dos pets no corpo da resposta.
     */
    @RequestMapping(value = "/pets", method = RequestMethod.GET)
    public List<Pet> getAllPets() {
        log.debug("REST request to get all Pets");
        return petService.findAll();
    }

    /**
     * {@code GET  /pets/:id} : retorna um pet pelo id.
     *
     * @param id o id do pet requisitado.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e com os dados do pet no corpo da resposta,
     * ou com status {@code 404 (Not Found)}.
     */
    @RequestMapping(value = "/pets/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pet> getPet(@PathVariable Long id) {
        log.debug("REST request to get Pet : {}", id);
        Pet pet = petService.findOne(id);
        return ResponseEntity.ok(pet);
    }

    /**
     * {@code DELETE  /pets/:id} : deleta um pet pelo id.
     *
     * @param id o id do pet que será deletado.
     * @return o {@link ResponseEntity} com status {@code 204 (NO_CONTENT)}.
     */
    @RequestMapping(value = "/pets/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        log.debug("REST request to delete Pet : {}", id);
        petService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
