package com.felipe.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.algafood.api.AlgaLinks;
import com.felipe.algafood.api.docs.UsuarioGrupoControllerOpenApi;
import com.felipe.algafood.api.dto.converters.GrupoDtoManager;
import com.felipe.algafood.api.dto.model.GrupoModel;
import com.felipe.algafood.domain.model.Usuario;
import com.felipe.algafood.domain.service.UsuarioService;

@RestController
@RequestMapping("/usuarios/{id}/grupos")
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private GrupoDtoManager dtoManager;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CollectionModel<GrupoModel> listarTodos(@PathVariable Long id) {
		Usuario usuario = this.usuarioService.buscarPorId(id);
		CollectionModel<GrupoModel> collectionModel = this.dtoManager.toCollectionModel(usuario.getGrupos())
				.removeLinks();
		collectionModel.getContent().forEach(grupo -> grupo.add(algaLinks.linkToUsuarioDesassociarGrupo(id, grupo.getId(), "desassociar")));
		return collectionModel.add(algaLinks.linkToGruposUsuarios(id));
	}
	
	@PutMapping("/{idGrupo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associarGrupo(@PathVariable Long id, @PathVariable Long idGrupo) {
		this.usuarioService.associarGrupo(id, idGrupo);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{idGrupo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociarGrupo(@PathVariable Long id, @PathVariable Long idGrupo) {
		this.usuarioService.desassociarGrupo(id, idGrupo);
		return ResponseEntity.noContent().build();
	}
}
