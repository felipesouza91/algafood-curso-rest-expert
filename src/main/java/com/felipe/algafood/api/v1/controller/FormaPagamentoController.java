package com.felipe.algafood.api.v1.controller;

import java.time.OffsetDateTime;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.CacheControl;
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
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import com.felipe.algafood.api.v1.docs.FormaPagamentoControllerOpenApi;
import com.felipe.algafood.api.v1.dto.converters.FormaPagamentoDtoManager;
import com.felipe.algafood.api.v1.dto.inputs.FormaPagamentoInput;
import com.felipe.algafood.api.v1.dto.model.FormaPagamentoModel;
import com.felipe.algafood.domain.model.FormaPagamento;
import com.felipe.algafood.domain.service.FormaPagamentoService;

@RestController
@RequestMapping(path = "/v1/formaspagamentos", produces = MediaType.APPLICATION_JSON_VALUE)
public class FormaPagamentoController implements FormaPagamentoControllerOpenApi{
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;	
	
	@Autowired
	private FormaPagamentoDtoManager dtoManager;

	@GetMapping
	public ResponseEntity<CollectionModel<FormaPagamentoModel>> listarTodos(ServletWebRequest request) {
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
		String etag = "0" ;
		OffsetDateTime ultimaDataAtualizacao = this.formaPagamentoService.getFormaPagamentoRepository().getDataUltimaAtualizacao();
		if(ultimaDataAtualizacao != null ) {
			etag = String.valueOf(ultimaDataAtualizacao.toEpochSecond());
		}
		if(request.checkNotModified(etag)) {
			return null;
		}
		CollectionModel<FormaPagamentoModel> listFormaPagamentoModel = this.dtoManager.toCollectionModel(this.formaPagamentoService.findAll());
		return  ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
				.eTag(etag)
				.body(listFormaPagamentoModel); 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FormaPagamentoModel> buscarPorId(@PathVariable Long id, ServletWebRequest request) {
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
		String etag = "0" ;
		OffsetDateTime ultimaDataAtualizacao = this.formaPagamentoService.getFormaPagamentoRepository().getDataUltimaAtualizacaoById(id);
		if(ultimaDataAtualizacao != null ) {
			etag = String.valueOf(ultimaDataAtualizacao.toEpochSecond());
		}
		if(request.checkNotModified(etag)) {
			return null;
		}
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
				.body(this.dtoManager.toModel(this.formaPagamentoService.buscarById(id))); 
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamentoModel criar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		FormaPagamento formaPagamento = this.dtoManager.converterToDomainObject(formaPagamentoInput);
		return this.dtoManager.toModel(this.formaPagamentoService.salvar(formaPagamento));
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public FormaPagamentoModel atualizar(@PathVariable Long id, @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		FormaPagamento formaPagamento = this.formaPagamentoService.buscarById(id);
		dtoManager.copyToDomainObject(formaPagamentoInput, formaPagamento );
		return this.dtoManager.toModel(this.formaPagamentoService.salvar(formaPagamento));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		this.formaPagamentoService.excluir(id);
	}	
}
