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

import com.felipe.algafood.api.docs.RestauranteFormaPagamentoControllerOpenApi;
import com.felipe.algafood.api.dto.converters.FormaPagamentoDtoManager;
import com.felipe.algafood.api.dto.model.FormaPagamentoModel;
import com.felipe.algafood.domain.model.Restaurante;
import com.felipe.algafood.domain.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes/{id}/formas-pagamento")
public class RestauranteFormaPagamentoController implements RestauranteFormaPagamentoControllerOpenApi{

	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private FormaPagamentoDtoManager formaPagamentoDtoManager;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<FormaPagamentoModel> listarTodos(@PathVariable Long id) {
		Restaurante restaurante = restauranteService.buscarPorId(id);
		return formaPagamentoDtoManager.toCollectionDtoModel(restaurante.getFormasPagamentos());
	}
	
	@PutMapping("/{idFormaPagamento}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long id, @PathVariable Long idFormaPagamento) {
		this.restauranteService.associarFormaPagamento(id, idFormaPagamento);
	}
	
	@DeleteMapping("/{idFormaPagamento}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long id, @PathVariable Long idFormaPagamento) {
		this.restauranteService.desassociarFormaPagamento(id, idFormaPagamento);
	}
}
