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

import com.felipe.algafood.api.dto.converters.GrupoDtoManager;
import com.felipe.algafood.api.dto.inputs.GrupoInput;
import com.felipe.algafood.api.dto.model.GrupoModel;
import com.felipe.algafood.domain.model.Grupo;
import com.felipe.algafood.domain.service.GrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private GrupoDtoManager dtoManager;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<GrupoModel> listarTodos() {
		return this.dtoManager.toCollectionDtoModel(this.grupoService.buscarTodos());
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public GrupoModel listaPorId(@PathVariable Long id) {
		return this.dtoManager.conveterToDtoModel(this.grupoService.buscarPorId(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoModel salvar( @RequestBody @Valid GrupoInput grupoInput) {
		Grupo grupo = this.dtoManager.converterToDomainObject(grupoInput);
		return this.dtoManager.conveterToDtoModel(this.grupoService.salvar(grupo));
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public GrupoModel atualizar(@PathVariable Long id, @RequestBody @Valid GrupoInput grupoInput) {
		Grupo grupoSalvo = this.grupoService.buscarPorId(id);
		this.dtoManager.copyToDomainObject(grupoInput, grupoSalvo);
		return this.dtoManager.conveterToDtoModel(this.grupoService.salvar(grupoSalvo));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		this.grupoService.excluir(id);
	}
	
}
