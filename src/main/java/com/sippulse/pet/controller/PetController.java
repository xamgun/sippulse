package com.sippulse.pet.controller;

import com.sippulse.pet.HeaderUtil;
import com.sippulse.pet.entity.Pet;
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
 * REST controller for managing {@link com.sippulse.pet.entity.Pet}.
 */
@RestController
@RequestMapping("/api")
public class PetController {

    private final Logger log = LoggerFactory.getLogger(PetController.class);

    private static final String ENTITY_NAME = "pet";

    @Value("Pet")
    private String applicationName;

    @Autowired
    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    /**
     * {@code POST  /pets} : Create a new pet.
     *
     * @param pet the pet to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pet, or with status {@code 400 (Bad Request)} if the pet has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
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
     * {@code PUT  /pets} : Updates an existing pet.
     *
     * @param pet the pet to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pet,
     * or with status {@code 400 (Bad Request)} if the pet is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pet couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
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
     * {@code GET  /pets} : get all the pets.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pets in body.
     */
    @RequestMapping(value = "/pets", method = RequestMethod.GET)
    public List<Pet> getAllPets() {
        log.debug("REST request to get all Pets");
        return petService.findAll();
    }

    /**
     * {@code GET  /pets/:id} : get the "id" pet.
     *
     * @param id the id of the pet to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pet, or with status {@code 404 (Not Found)}.
     */
    @RequestMapping(value = "/pets/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pet> getPet(@PathVariable Long id) {
        log.debug("REST request to get Pet : {}", id);
        Pet pet = petService.findOne(id);
        return ResponseEntity.ok(pet);
    }

    /**
     * {@code DELETE  /pets/:id} : delete the "id" pet.
     *
     * @param id the id of the pet to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @RequestMapping(value = "/pets/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        log.debug("REST request to delete Pet : {}", id);
        petService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
