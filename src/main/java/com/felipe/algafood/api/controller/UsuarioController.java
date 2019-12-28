package com.felipe.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.algafood.api.dto.converters.UsuarioDtoManager;
import com.felipe.algafood.api.dto.inputs.UsuarioInput;
import com.felipe.algafood.api.dto.inputs.UsuarioInputNoPassword;
import com.felipe.algafood.api.dto.inputs.UsuarioSenhaInput;
import com.felipe.algafood.api.dto.model.UsuarioModel;
import com.felipe.algafood.domain.model.Usuario;
import com.felipe.algafood.domain.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioDtoManager dtoManager;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<UsuarioModel> listarTodos() {
		return this.dtoManager.toCollectionDtoModel(this.usuarioService.buscarTodos());
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public UsuarioModel listarPorCodigo(@PathVariable Long id) {
		return this.dtoManager.conveterToDtoModel(this.usuarioService.buscarPorId(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioModel salvar(@RequestBody @Valid UsuarioInput usuarioInput) {
		Usuario usuario = this.dtoManager.converterToDomainObject(usuarioInput);
		return this.dtoManager.conveterToDtoModel(this.usuarioService.salvar(usuario));
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public UsuarioModel atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioInputNoPassword usuarioinput) {
		Usuario usuario = usuarioService.buscarPorId(id);
		dtoManager.copyToDomainObject(usuarioinput, usuario);
		return this.dtoManager.conveterToDtoModel(this.usuarioService.salvar(usuario));
	}
	
	@PutMapping("/{id}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alterarSenha(@PathVariable Long id, @RequestBody @Valid UsuarioSenhaInput input) {
		this.usuarioService.atualizarSenha(id, input.getSenhaAtual(), input.getNovaSenha());
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		this.usuarioService.excluir(id);
	}
}
