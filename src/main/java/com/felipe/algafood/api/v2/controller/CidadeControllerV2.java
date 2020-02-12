package com.felipe.algafood.api.v2.controller;

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

import com.felipe.algafood.api.ResourceUriHelper;
import com.felipe.algafood.api.v2.docs.CidadeControllerV2OpenApi;
import com.felipe.algafood.api.v2.dto.converters.CidadeDtoManagerV2;
import com.felipe.algafood.api.v2.dto.inputs.CidadeInputV2;
import com.felipe.algafood.api.v2.dto.model.CidadeModelV2;
import com.felipe.algafood.domain.model.Cidade;
import com.felipe.algafood.domain.service.CidadeService;


@RestController
@RequestMapping(path = "/v2/cidades", produces  = MediaType.APPLICATION_JSON_VALUE)
public class CidadeControllerV2 implements CidadeControllerV2OpenApi{

	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private CidadeDtoManagerV2 cidadeDtoManager;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CollectionModel<CidadeModelV2> buscar() {
		
		//collection.add(WebMvcLinkBuilder.linkTo(CidadeController.class).withSelfRel());
		return cidadeDtoManager.toCollectionModel(this.cidadeService.getCidadeRepository().findAll());
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CidadeModelV2 buscarPorId(@PathVariable Long id) {
		CidadeModelV2 cidadeModel = cidadeDtoManager.toModel(this.cidadeService.buscarPorId(id)) ;
		return cidadeModel;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModelV2 salvar (@RequestBody @Valid CidadeInputV2 cidadeInput) {
		Cidade cidade = this.cidadeDtoManager.converterToDomainObject(cidadeInput);
		cidade = this.cidadeService.salvar(cidade);
		ResourceUriHelper.addUriInResponseHeader(cidade.getId());
		return this.cidadeDtoManager.toModel(cidade);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CidadeModelV2 atualizar(@PathVariable Long id, @RequestBody @Valid  CidadeInputV2 cidadeInput) {
		Cidade cidade = cidadeService.atualizar(id, this.cidadeDtoManager.converterToDomainObject(cidadeInput));
		return this.cidadeDtoManager.toModel(cidade);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir( @PathVariable Long id) {
		cidadeService.excluir(id);
	}
	
}
