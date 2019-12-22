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

import com.felipe.algafood.api.dto.converters.FormaPagamentoDtoManager;
import com.felipe.algafood.api.dto.inputs.FormaPagamentoInput;
import com.felipe.algafood.api.dto.model.FormaPagamentoModel;
import com.felipe.algafood.domain.model.FormaPagamento;
import com.felipe.algafood.domain.service.FormaPagamentoService;

@RestController
@RequestMapping("/formaspagamentos")
public class FormaPagamentoController {
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@Autowired
	private FormaPagamentoDtoManager dtoManager;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<FormaPagamentoModel> listarTodos() {
		return this.dtoManager.toCollectionDtoModel(this.formaPagamentoService.findAll()); 
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public FormaPagamentoModel buscarPorId(@PathVariable Long id) {
		return this.dtoManager.conveterToDtoModel(this.formaPagamentoService.buscarById(id)); 
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamentoModel criar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		FormaPagamento formaPagamento = this.dtoManager.converterToDomainObject(formaPagamentoInput);
		return this.dtoManager.conveterToDtoModel(this.formaPagamentoService.salvar(formaPagamento));
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public FormaPagamentoModel atualizar(@PathVariable Long id, @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		FormaPagamento formaPagamento = this.formaPagamentoService.buscarById(id);
		dtoManager.copyToDomainObject(formaPagamentoInput, formaPagamento );
		return this.dtoManager.conveterToDtoModel(this.formaPagamentoService.salvar(formaPagamento));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		this.formaPagamentoService.excluir(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
