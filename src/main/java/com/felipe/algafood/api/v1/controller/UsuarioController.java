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

import com.felipe.algafood.api.v1.docs.UsuarioControllerOpenApi;
import com.felipe.algafood.api.v1.dto.converters.UsuarioDtoManager;
import com.felipe.algafood.api.v1.dto.inputs.UsuarioInput;
import com.felipe.algafood.api.v1.dto.inputs.UsuarioInputNoPassword;
import com.felipe.algafood.api.v1.dto.inputs.UsuarioSenhaInput;
import com.felipe.algafood.api.v1.dto.model.UsuarioModel;
import com.felipe.algafood.core.security.CheckSecurity;
import com.felipe.algafood.domain.model.Usuario;
import com.felipe.algafood.domain.service.UsuarioService;

@RestController
@RequestMapping(path = "/v1/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController implements UsuarioControllerOpenApi {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioDtoManager dtoManager;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@CheckSecurity.UsuarioGrupoPermissoes.PodeConsultar
	public CollectionModel<UsuarioModel> listarTodos() {
		return this.dtoManager.toCollectionModel(this.usuarioService.buscarTodos());
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@CheckSecurity.UsuarioGrupoPermissoes.PodeConsultar
	public UsuarioModel listarPorCodigo(@PathVariable Long id) {
		return this.dtoManager.toModel(this.usuarioService.buscarPorId(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@CheckSecurity.UsuarioGrupoPermissoes.PodeEditar
	public UsuarioModel salvar(@RequestBody @Valid UsuarioInput usuarioInput) {
		Usuario usuario = this.dtoManager.converterToDomainObject(usuarioInput);
		return this.dtoManager.toModel(this.usuarioService.salvar(usuario));
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@CheckSecurity.UsuarioGrupoPermissoes.PodeAlterarUsuario
	public UsuarioModel atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioInputNoPassword usuarioinput) {
		Usuario usuario = usuarioService.buscarPorId(id);
		dtoManager.copyToDomainObject(usuarioinput, usuario);
		return this.dtoManager.toModel(this.usuarioService.salvar(usuario));
	}
	
	@PutMapping("/{id}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@CheckSecurity.UsuarioGrupoPermissoes.PodeAlterarPropriaSenha
	public void alterarSenha(@PathVariable Long id, @RequestBody @Valid UsuarioSenhaInput input) {
		this.usuarioService.atualizarSenha(id, input.getSenhaAtual(), input.getNovaSenha());
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@CheckSecurity.UsuarioGrupoPermissoes.PodeEditar
	public void excluir(@PathVariable Long id) {
		this.usuarioService.excluir(id);
	}
}
