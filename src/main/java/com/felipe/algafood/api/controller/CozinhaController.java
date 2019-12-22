package com.felipe.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.algafood.api.dto.converters.CozinhaDtoManager;
import com.felipe.algafood.api.dto.inputs.CozinhaInput;
import com.felipe.algafood.api.dto.model.CozinhaModel;
import com.felipe.algafood.domain.model.Cozinha;
import com.felipe.algafood.domain.repository.CozinhaRepository;
import com.felipe.algafood.domain.service.CozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@Autowired
	private CozinhaDtoManager cozinhaDtoManager;
	
	
	@GetMapping
	public ResponseEntity<List<CozinhaModel>> listar() {
		return ResponseEntity.ok().body(cozinhaDtoManager.toCollectionDtoModel(cozinhaRepository.findAll())) ;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CozinhaModel> buscarPorId(@PathVariable Long id) {
		this.cozinhaService.buscarPorId(id);
		return ResponseEntity.ok().body(cozinhaDtoManager.conveterToDtoModel(this.cozinhaService.buscarPorId(id)));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaModel savar (@RequestBody @Valid CozinhaInput cozinhaInput) {
		Cozinha cozinha = cozinhaDtoManager.converterToDomainObject(cozinhaInput);
		return cozinhaDtoManager.conveterToDtoModel(cozinhaService.salvar(cozinha));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CozinhaModel> atualizar(@PathVariable Long id, @RequestBody @Valid CozinhaInput cozinhaInput) {
		Cozinha cozinhaAtual = this.cozinhaService.buscarPorId(id);
		cozinhaDtoManager.copyToDomainObject(cozinhaInput, cozinhaAtual);
		return ResponseEntity.ok(cozinhaDtoManager.conveterToDtoModel(cozinhaService.salvar(cozinhaAtual)));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		this.cozinhaService.excluir(id);
	}
}
