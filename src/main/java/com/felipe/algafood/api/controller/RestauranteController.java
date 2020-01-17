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

import com.fasterxml.jackson.annotation.JsonView;
import com.felipe.algafood.api.dto.converters.RestauranteDtoManager;
import com.felipe.algafood.api.dto.inputs.RestauranteInput;
import com.felipe.algafood.api.dto.model.RestauranteModel;
import com.felipe.algafood.api.view.RestauranteView;
import com.felipe.algafood.domain.model.Restaurante;
import com.felipe.algafood.domain.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private RestauranteDtoManager restauranteDtoManager;
	
	@GetMapping
	@JsonView(RestauranteView.Resumo.class)
	public ResponseEntity<List<RestauranteModel>> buscarTodos() {
		List<Restaurante> restaurantes = this.restauranteService.getRestauranteRepository().findAll();
		return ResponseEntity.ok(restauranteDtoManager.toCollectionDtoModel(restaurantes));
	}
	
	@GetMapping(params = "projecao=nome")
	@JsonView(RestauranteView.ApenasNome.class)
	public ResponseEntity<List<RestauranteModel>> buscarTodosComNome() {
		return this.buscarTodos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RestauranteModel> buscarPorId(@PathVariable Long id ) {
		Restaurante restauranteSalvo = restauranteService.buscarPorId(id); 
		return ResponseEntity.ok(restauranteDtoManager.conveterToDtoModel(restauranteSalvo));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> cadastrar(@RequestBody  @Valid RestauranteInput restauranteModel) {
		Restaurante restaurante = restauranteDtoManager.converterToDomainObject(restauranteModel);
		restaurante = restauranteService.salvar(restaurante);
		return ResponseEntity.status(HttpStatus.CREATED).body(restauranteDtoManager.conveterToDtoModel(restaurante));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar (@PathVariable Long id, @RequestBody  @Valid RestauranteInput restauranteInput){
		Restaurante restauranteAtual = this.restauranteService.buscarPorId(id); 
		restauranteDtoManager.copyToDomainObject(restauranteInput, restauranteAtual);
		return ResponseEntity.ok(restauranteDtoManager.conveterToDtoModel(this.restauranteService.atualizar(id, restauranteAtual)));
	}
	
	@PutMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativarRestaurante(@PathVariable Long id) {
		this.restauranteService.ativar(id);
	}
	
	@PutMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativarMultiplos(@RequestBody List<Long> restaurantesIds) {
		this.restauranteService.ativar(restaurantesIds);
	}
	
	@DeleteMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativarMultiplos(@RequestBody List<Long> restaurantesIds) {
		this.restauranteService.inativar(restaurantesIds);
	}
	
	@PutMapping("/{id}/fechamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void fecharRestaurante(@PathVariable Long id) {
		this.restauranteService.fechar(id);
	}
	
	@PutMapping("/{id}/abertura")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void abrirRestaurante(@PathVariable Long id) {
		this.restauranteService.abrir(id);
	}
	
	@DeleteMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativarRestaurante(@PathVariable Long id) {
		this.restauranteService.inativar(id);
	}
	
}
