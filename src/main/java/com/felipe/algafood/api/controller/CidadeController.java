package com.felipe.algafood.api.controller;

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
import com.felipe.algafood.api.docs.CidadeControllerOpenApi;
import com.felipe.algafood.api.dto.converters.CidadeDtoManager;
import com.felipe.algafood.api.dto.inputs.CidadeInput;
import com.felipe.algafood.api.dto.model.CidadeModel;
import com.felipe.algafood.domain.model.Cidade;
import com.felipe.algafood.domain.service.CidadeService;


@RestController
@RequestMapping(path = "/cidades", produces  = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi{

	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private CidadeDtoManager cidadeDtoManager;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CollectionModel<CidadeModel> buscar() {
		
		//collection.add(WebMvcLinkBuilder.linkTo(CidadeController.class).withSelfRel());
		return cidadeDtoManager.toCollectionModel(this.cidadeService.getCidadeRepository().findAll());
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CidadeModel buscarPorId(@PathVariable Long id) {
		CidadeModel cidadeModel = cidadeDtoManager.toModel(this.cidadeService.buscarPorId(id)) ;
		return cidadeModel;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModel salvar (@RequestBody @Valid CidadeInput cidadeInput) {
		Cidade cidade = this.cidadeDtoManager.converterToDomainObject(cidadeInput);
		cidade = this.cidadeService.salvar(cidade);
		ResourceUriHelper.addUriInResponseHeader(cidade.getId());
		return this.cidadeDtoManager.toModel(cidade);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CidadeModel atualizar(@PathVariable Long id, @RequestBody @Valid  CidadeInput cidadeInput) {
		Cidade cidade = cidadeService.atualizar(id, this.cidadeDtoManager.converterToDomainObject(cidadeInput));
		return this.cidadeDtoManager.toModel(cidade);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir( @PathVariable Long id) {
		cidadeService.excluir(id);
	}
	
}
