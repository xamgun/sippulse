package com.sippulse.pet.controller;

import com.sippulse.pet.HeaderUtil;
import com.sippulse.pet.entity.Usuario;
import com.sippulse.pet.service.UsuarioService;
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
 * REST controller para cadastrar, pesquisar e atualizar usuarios {@link com.sippulse.pet.entity.Usuario}.
 */
@RestController
@RequestMapping("/api")
public class UsuarioController {

    private final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    private static final String ENTITY_NAME = "usuario";

    @Value("Pet")
    private String applicationName;

    @Autowired
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * {@code POST  /usuarios} : Cria um novo usuario.
     *
     * @param usuario o usuario que será criado.
     * @return o {@link ResponseEntity} com status {@code 201 (Created)} e com os dados do novo usuario no corpo da resposta,
     * ou com status {@code 400 (Bad Request)} se o usuario com este id já existir.
     * @throws URISyntaxException se a syntax URI estiver incorreta.
     */
    @RequestMapping(value = "/usuarios", method = RequestMethod.POST)
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) throws URISyntaxException {
        log.debug("REST request to save Usuario : {}", usuario);
        if (usuario.getId() != null) {
            ResponseEntity.badRequest().build();
        }
        Usuario result = usuarioService.save(usuario);
        return ResponseEntity.created(new URI("/api/usuarios/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /usuarios} : Atualiza um usuário existente.
     *
     * @param usuario o usuario que será atualizado.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e com os dados do usuario no corpo da reposta,
     * ou com status {@code 400 (Bad Request)} se o usuario for unválido,
     * ou com status {@code 500 (Internal Server Error)} se o usuario não puder ser atualizado.
     * @throws URISyntaxException se a syntax URI estiver incorreta.
     */
    @RequestMapping(value = "/usuarios", method = RequestMethod.PUT)
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario) throws URISyntaxException {
        log.debug("REST request to update Usuario : {}", usuario);
        if (usuario.getId() == null) {
            ResponseEntity.badRequest().build();
        }
        Usuario result = usuarioService.save(usuario);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, usuario.getId().toString()))
                .body(result);
    }

    /**
     * {@code GET  /usuarios} : retorna todos os usuarios.
     *
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e a lista dos usuarios no corpo da resposta.
     */
    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    public List<Usuario> getAllUsuarios() {
        log.debug("REST request to get all Usuarios");
        return usuarioService.findAll();
    }

    /**
     * {@code GET  /usuarios/:id} : retorna um usuario pelo id.
     *
     * @param id o id do usuario requisitado.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} com os dados do usuario no corpo da resposta,
     * ou com status {@code 404 (Not Found)}.
     */
    @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.GET)
    public ResponseEntity<Usuario> getUsuario(@PathVariable Long id) {
        log.debug("REST request to get Usuario : {}", id);
        Usuario usuario = usuarioService.findOne(id);
        return ResponseEntity.ok(usuario);
    }

    /**
     * {@code DELETE  /usuarios/:id} : deleta um usuario pelo id.
     *
     * @param id o id do usuario a ser deletado.
     * @return o {@link ResponseEntity} com status {@code 204 (NO_CONTENT)}.
     */
    @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        log.debug("REST request to delete Usuario : {}", id);
        usuarioService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
