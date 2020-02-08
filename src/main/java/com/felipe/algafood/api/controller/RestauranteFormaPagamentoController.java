package com.felipe.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.algafood.api.AlgaLinks;
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
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CollectionModel<FormaPagamentoModel> listarTodos(@PathVariable Long id) {
		Restaurante restaurante = restauranteService.buscarPorId(id);
		CollectionModel<FormaPagamentoModel> collection = formaPagamentoDtoManager.toCollectionModel(restaurante.getFormasPagamentos())
				.removeLinks()
				.add(algaLinks.linkToRestaurantesFormasPagamentos(id))
				.add(algaLinks.linkToRestauranteFormaPagamentoAssociar(restaurante.getId(), "associar"));
		collection.getContent().forEach(formaPagamaneto ->
			formaPagamaneto.add(
					algaLinks.linkToRestauranteFormaPagamentoDesassociar(restaurante.getId(), formaPagamaneto.getId(), "desassociar")));
		return collection;
	}
	
	@PutMapping("/{idFormaPagamento}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long id, @PathVariable Long idFormaPagamento) {
		this.restauranteService.associarFormaPagamento(id, idFormaPagamento);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{idFormaPagamento}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long id, @PathVariable Long idFormaPagamento) {
		this.restauranteService.desassociarFormaPagamento(id, idFormaPagamento);
		return ResponseEntity.noContent().build();
	}
}
