package com.felipe.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.algafood.api.dto.converters.PermissaoDtoManager;
import com.felipe.algafood.api.dto.model.PermissaoModel;
import com.felipe.algafood.domain.model.Grupo;
import com.felipe.algafood.domain.service.GrupoService;

@RestController
@RequestMapping("/grupos/{id}/permissoes")
public class GrupoPermissaoController {

	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private PermissaoDtoManager dtoManager;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<PermissaoModel> listarTodos(@PathVariable Long id) {
		Grupo grupo = this.grupoService.buscarPorId(id);
		return this.dtoManager.toCollectionDtoModel(grupo.getPermissoes());
	}
	
	@PutMapping("/{idPermissao}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long id, @PathVariable Long idPermissao) {
		this.grupoService.associarPermissao(id, idPermissao);
	}
	
	@DeleteMapping("/{idPermissao}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long id, @PathVariable Long idPermissao) {
		this.grupoService.desassociarPermissao(id, idPermissao);
	}
}
