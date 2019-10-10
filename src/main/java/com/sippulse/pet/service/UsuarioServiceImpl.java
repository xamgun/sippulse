package com.sippulse.pet.service;

import com.sippulse.pet.entity.Usuario;
import com.sippulse.pet.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Usuario}.
 */
@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Save a usuario.
     *
     * @param usuario the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Usuario save(Usuario usuario) {
        log.debug("Request to save Usuario : {}", usuario);
        return usuarioRepository.save(usuario);
    }

    /**
     * Get all the usuarios.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        log.debug("Request to get all Usuarios");
        return usuarioRepository.findAll();
    }


    /**
     * Get one usuario by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findOne(Long id) {
        log.debug("Request to get Usuario : {}", id);
        return usuarioRepository.findById(id);
    }

    /**
     * Delete the usuario by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Usuario : {}", id);
        usuarioRepository.deleteById(id);
    }
}
