package com.felipe.algafood.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.algafood.api.v1.AlgaLinks;
import com.felipe.algafood.api.v1.docs.RestauranteProdutoControllerOpenApi;
import com.felipe.algafood.api.v1.dto.converters.ProdutoDtoManager;
import com.felipe.algafood.api.v1.dto.inputs.ProdutoInput;
import com.felipe.algafood.api.v1.dto.model.ProdutoModel;
import com.felipe.algafood.domain.model.Produto;
import com.felipe.algafood.domain.model.Restaurante;
import com.felipe.algafood.domain.service.ProdutoService;
import com.felipe.algafood.domain.service.RestauranteService;

@RestController
@RequestMapping(path = "/v1/restaurantes/{idRestaurante}/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteProdutoController implements RestauranteProdutoControllerOpenApi {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private ProdutoDtoManager dtoManager;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CollectionModel<ProdutoModel> buscarTodos(@PathVariable Long idRestaurante,
			@RequestParam(required = false, defaultValue = "false") Boolean incluirInativo) {
		Restaurante restaurante = restauranteService.buscarPorId(idRestaurante);
		CollectionModel<ProdutoModel> collection = null;
		if (incluirInativo) {
			collection = this.dtoManager.toCollectionModel(this.produtoService.getProdutoRepository().findByRestaurante(restaurante));
		} else {
			collection = this.dtoManager.toCollectionModel(this.produtoService.getProdutoRepository().findAtivoByRestaurante(restaurante));
		}
		return collection.add(algaLinks.linkToProdutos(idRestaurante));
	}
	
	@GetMapping("/{idProduto}")
	@ResponseStatus(HttpStatus.OK)
	public ProdutoModel buscarPorId(@PathVariable Long idRestaurante, @PathVariable Long idProduto) {
		restauranteService.buscarPorId(idRestaurante);
		Produto produto = this.produtoService.buscarPorIdRestauranteEProduto(idRestaurante, idProduto);
		return this.dtoManager.toModel(produto);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoModel salvar(@PathVariable Long idRestaurante, @RequestBody @Valid ProdutoInput produtoInput) {
		Restaurante restaurante = this.restauranteService.buscarPorId(idRestaurante);
		Produto produto = this.dtoManager.converterToDomainObject(produtoInput);
		produto.setRestaurante(restaurante);
		return this.dtoManager.toModel(this.produtoService.salvar(produto));
	}
	
	@PutMapping("/{idProduto}")
	@ResponseStatus(HttpStatus.OK)
	public ProdutoModel atualizar(@PathVariable Long idRestaurante, @PathVariable Long idProduto,
			@RequestBody @Valid ProdutoInput produtoInput) {
		this.restauranteService.buscarPorId(idRestaurante);
		Produto produtoAtual = this.produtoService.buscarPorIdRestauranteEProduto(idRestaurante, idProduto);
		dtoManager.copyToDomainObject(produtoInput, produtoAtual);
		return this.dtoManager.toModel(this.produtoService.salvar(produtoAtual));
	}
}
