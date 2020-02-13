package com.felipe.algafood.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.felipe.algafood.api.v1.docs.CozinhaControllerOpenApi;
import com.felipe.algafood.api.v1.dto.converters.CozinhaDtoManager;
import com.felipe.algafood.api.v1.dto.inputs.CozinhaInput;
import com.felipe.algafood.api.v1.dto.model.CozinhaModel;
import com.felipe.algafood.domain.model.Cozinha;
import com.felipe.algafood.domain.repository.CozinhaRepository;
import com.felipe.algafood.domain.service.CozinhaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping( path = "/v1/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController implements CozinhaControllerOpenApi {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@Autowired
	private CozinhaDtoManager cozinhaDtoManager;
	
	@Autowired
	private PagedResourcesAssembler<Cozinha> pagedResourcesAssembled;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public PagedModel<CozinhaModel> listar(Pageable pageable) {
		log.info("Consultando cozinhas....");
		Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);
		
		PagedModel<CozinhaModel> cozinhasPagedModel = pagedResourcesAssembled.toModel(cozinhasPage,cozinhaDtoManager);
		
		return cozinhasPagedModel;
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CozinhaModel buscarPorId(@PathVariable Long id) { 
		this.cozinhaService.buscarPorId(id);
		return cozinhaDtoManager.toModel(this.cozinhaService.buscarPorId(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaModel savar (@RequestBody @Valid CozinhaInput cozinhaInput) {
		Cozinha cozinha = cozinhaDtoManager.converterToDomainObject(cozinhaInput);
		return cozinhaDtoManager.toModel(cozinhaService.salvar(cozinha));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CozinhaModel> atualizar(@PathVariable Long id, @RequestBody @Valid CozinhaInput cozinhaInput) {
		Cozinha cozinhaAtual = this.cozinhaService.buscarPorId(id);
		cozinhaDtoManager.copyToDomainObject(cozinhaInput, cozinhaAtual);
		return ResponseEntity.ok(cozinhaDtoManager.toModel(cozinhaService.salvar(cozinhaAtual)));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		this.cozinhaService.excluir(id);
	}
}
