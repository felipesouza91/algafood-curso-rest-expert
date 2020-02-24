package com.felipe.algafood.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.algafood.api.v1.docs.GrupoControllerOpenApi;
import com.felipe.algafood.api.v1.dto.converters.GrupoDtoManager;
import com.felipe.algafood.api.v1.dto.inputs.GrupoInput;
import com.felipe.algafood.api.v1.dto.model.GrupoModel;
import com.felipe.algafood.core.security.CheckSecurity;
import com.felipe.algafood.domain.model.Grupo;
import com.felipe.algafood.domain.service.GrupoService;

@RestController
@RequestMapping(path = "/v1/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GrupoControllerOpenApi {

	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private GrupoDtoManager dtoManager;

	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@CheckSecurity.UsuarioGrupoPermissoes.PodeConsultar
	public CollectionModel<GrupoModel> listarTodos() {
		return this.dtoManager.toCollectionModel(this.grupoService.buscarTodos());
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@CheckSecurity.UsuarioGrupoPermissoes.PodeConsultar
	public GrupoModel listaPorId(@PathVariable Long id) {
		return this.dtoManager.toModel(this.grupoService.buscarPorId(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@CheckSecurity.UsuarioGrupoPermissoes.PodeEditar
	public GrupoModel salvar( @RequestBody @Valid GrupoInput grupoInput) {
		Grupo grupo = this.dtoManager.converterToDomainObject(grupoInput);
		return this.dtoManager.toModel(this.grupoService.salvar(grupo));
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@CheckSecurity.UsuarioGrupoPermissoes.PodeEditar
	public GrupoModel atualizar(@PathVariable Long id, @RequestBody @Valid GrupoInput grupoInput) {
		Grupo grupoSalvo = this.grupoService.buscarPorId(id);
		this.dtoManager.copyToDomainObject(grupoInput, grupoSalvo);
		return this.dtoManager.toModel(this.grupoService.salvar(grupoSalvo));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@CheckSecurity.UsuarioGrupoPermissoes.PodeEditar
	public void excluir(@PathVariable Long id) {
		this.grupoService.excluir(id);
	}
	
}
