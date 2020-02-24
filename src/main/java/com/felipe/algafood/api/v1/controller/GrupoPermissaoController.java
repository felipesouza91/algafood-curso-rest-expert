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
import com.felipe.algafood.api.v1.docs.GrupoPermissaoControllerOpenApi;
import com.felipe.algafood.api.v1.dto.converters.PermissaoDtoManager;
import com.felipe.algafood.api.v1.dto.model.PermissaoModel;
import com.felipe.algafood.core.security.CheckSecurity;
import com.felipe.algafood.domain.model.Grupo;
import com.felipe.algafood.domain.service.GrupoService;

@RestController
@RequestMapping(path = "/v1/grupos/{id}/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {

	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private PermissaoDtoManager dtoManager;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@CheckSecurity.UsuarioGrupoPermissoes.PodeConsultar
	public CollectionModel<PermissaoModel> listarTodos(@PathVariable Long id) {
		Grupo grupo = this.grupoService.buscarPorId(id);
		CollectionModel<PermissaoModel> collectionModel = this.dtoManager.toCollectionModel(grupo.getPermissoes()).removeLinks(); 
		collectionModel.getContent().forEach(permissao -> 
				permissao.add(algaLinks.linkToGrupoDesassociarPermissao(id, permissao.getId(), "desassociar")));
		return collectionModel.add(algaLinks.linkToGrupoPermissoes(id)).add(algaLinks.linkToGrupoAssociarPermissao(id, "associar"));
	}
	
	@PutMapping("/{idPermissao}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@CheckSecurity.UsuarioGrupoPermissoes.PodeEditar
	public ResponseEntity<Void> associar(@PathVariable Long id, @PathVariable Long idPermissao) {
		this.grupoService.associarPermissao(id, idPermissao);
		return ResponseEntity.noContent().build(); 
	}
	
	@DeleteMapping("/{idPermissao}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@CheckSecurity.UsuarioGrupoPermissoes.PodeEditar
	public ResponseEntity<Void>  desassociar(@PathVariable Long id, @PathVariable Long idPermissao) {
		this.grupoService.desassociarPermissao(id, idPermissao);
		return ResponseEntity.noContent().build();
	}
}
