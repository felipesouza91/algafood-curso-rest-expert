package com.felipe.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.algafood.api.dto.converters.ProdutoDtoManager;
import com.felipe.algafood.api.dto.inputs.ProdutoInput;
import com.felipe.algafood.api.dto.model.ProdutoModel;
import com.felipe.algafood.domain.model.Produto;
import com.felipe.algafood.domain.model.Restaurante;
import com.felipe.algafood.domain.service.ProdutoService;
import com.felipe.algafood.domain.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes/{idRestaurante}/produtos")
public class RestauranteProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private ProdutoDtoManager dtoManager;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProdutoModel> buscarTodos(@PathVariable Long idRestaurante, @RequestParam(required = false) boolean incluirInativo) {
		Restaurante restaurante = restauranteService.buscarPorId(idRestaurante);
		if (incluirInativo) {
			return this.dtoManager.toCollectionDtoModel(this.produtoService.getProdutoRepository().findByRestaurante(restaurante));
		} else {
			return this.dtoManager.toCollectionDtoModel(this.produtoService.getProdutoRepository().findAtivoByRestaurante(restaurante));	
		}
		
	}
	
	@GetMapping("/{idProduto}")
	@ResponseStatus(HttpStatus.OK)
	public ProdutoModel buscarPorId(@PathVariable Long idRestaurante, @PathVariable Long idProduto) {
		restauranteService.buscarPorId(idRestaurante);
		Produto produto = this.produtoService.buscarPorIdRestauranteEProduto(idRestaurante, idProduto);
		return this.dtoManager.conveterToDtoModel(produto);
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoModel salvar(@PathVariable Long idRestaurante, @RequestBody @Valid ProdutoInput produtoInput) {
		Restaurante restaurante = this.restauranteService.buscarPorId(idRestaurante);
		Produto produto = this.dtoManager.converterToDomainObject(produtoInput);
		produto.setRestaurante(restaurante);
		return this.dtoManager.conveterToDtoModel(this.produtoService.salvar(produto));
	}
	
	@PutMapping("/{idProduto}")
	@ResponseStatus(HttpStatus.OK)
	public ProdutoModel atualizar(@PathVariable Long idRestaurante, @PathVariable Long idProduto,
			@RequestBody @Valid ProdutoInput produtoInput) {
		this.restauranteService.buscarPorId(idRestaurante);
		Produto produtoAtual = this.produtoService.buscarPorIdRestauranteEProduto(idRestaurante, idProduto);
		dtoManager.copyToDomainObject(produtoInput, produtoAtual);
		return this.dtoManager.conveterToDtoModel(this.produtoService.salvar(produtoAtual));
	}
}
