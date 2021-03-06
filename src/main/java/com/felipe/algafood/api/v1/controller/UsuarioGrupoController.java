package com.felipe.algafood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.algafood.api.v1.AlgaLinks;
import com.felipe.algafood.api.v1.docs.UsuarioGrupoControllerOpenApi;
import com.felipe.algafood.api.v1.dto.converters.GrupoDtoManager;
import com.felipe.algafood.api.v1.dto.model.GrupoModel;
import com.felipe.algafood.core.security.CheckSecurity;
import com.felipe.algafood.domain.model.Usuario;
import com.felipe.algafood.domain.service.UsuarioService;

@RestController
@RequestMapping(path = "/v1/usuarios/{id}/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private GrupoDtoManager dtoManager;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@CheckSecurity.UsuarioGrupoPermissoes.PodeConsultar
	public CollectionModel<GrupoModel> listarTodos(@PathVariable Long id) {
		Usuario usuario = this.usuarioService.buscarPorId(id);
		CollectionModel<GrupoModel> collectionModel = this.dtoManager.toCollectionModel(usuario.getGrupos())
				.removeLinks();
		collectionModel.getContent().forEach(grupo -> grupo.add(algaLinks.linkToUsuarioDesassociarGrupo(id, grupo.getId(), "desassociar")));
		return collectionModel.add(algaLinks.linkToGruposUsuarios(id));
	}
	
	@PutMapping("/{idGrupo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@CheckSecurity.UsuarioGrupoPermissoes.PodeEditar
	public ResponseEntity<Void> associarGrupo(@PathVariable Long id, @PathVariable Long idGrupo) {
		this.usuarioService.associarGrupo(id, idGrupo);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{idGrupo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@CheckSecurity.UsuarioGrupoPermissoes.PodeEditar
	public ResponseEntity<Void> desassociarGrupo(@PathVariable Long id, @PathVariable Long idGrupo) {
		this.usuarioService.desassociarGrupo(id, idGrupo);
		return ResponseEntity.noContent().build();
	}
}
