package com.sippulse.pet.service;

import com.sippulse.pet.entity.Usuario;

import java.util.List;

/**
 * Service Interface for managing {@link Usuario}.
 */
public interface UsuarioService {

    /**
     * Save a usuario.
     *
     * @param usuario the entity to save.
     * @return the persisted entity.
     */
    Usuario save(Usuario usuario);

    /**
     * Get all the usuarios.
     *
     * @return the list of entities.
     */
    List<Usuario> findAll();


    /**
     * Get the "id" usuario.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Usuario findOne(Long id);

    /**
     * Delete the "id" usuario.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
