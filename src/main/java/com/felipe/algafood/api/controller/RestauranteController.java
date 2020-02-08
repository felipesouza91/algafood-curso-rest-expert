package com.felipe.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
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

import com.felipe.algafood.api.docs.RestauranteControllerOpenApi;
import com.felipe.algafood.api.dto.converters.RestauranteApenasNomeDtoManager;
import com.felipe.algafood.api.dto.converters.RestauranteBasicDtoManager;
import com.felipe.algafood.api.dto.converters.RestauranteDtoManager;
import com.felipe.algafood.api.dto.inputs.RestauranteInput;
import com.felipe.algafood.api.dto.model.RestauranteBasicModel;
import com.felipe.algafood.api.dto.model.RestauranteModel;
import com.felipe.algafood.api.dto.model.resumo.RestauranteApenasNomeModel;
import com.felipe.algafood.domain.model.Restaurante;
import com.felipe.algafood.domain.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController implements RestauranteControllerOpenApi{
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private RestauranteDtoManager restauranteDtoManager;
	
	@Autowired
	private RestauranteBasicDtoManager restauranteBasicDtoManager;
	
	@Autowired
	private RestauranteApenasNomeDtoManager restauranteApenasNomeDtoManager;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CollectionModel<RestauranteBasicModel> buscarTodos() {
		List<Restaurante> restaurantes = this.restauranteService.getRestauranteRepository().findAll();
		CollectionModel<RestauranteBasicModel> restaurantesModel = restauranteBasicDtoManager.toCollectionModel(restaurantes); 
		return restaurantesModel;
	}
	
	
	@GetMapping(params = "projecao=apenas-nome")
	@ResponseStatus(HttpStatus.OK)
	public CollectionModel<RestauranteApenasNomeModel> buscarTodosComNome() {
		return restauranteApenasNomeDtoManager.toCollectionModel(this.restauranteService.getRestauranteRepository().findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RestauranteModel> buscarPorId(@PathVariable Long id ) {
		Restaurante restauranteSalvo = restauranteService.buscarPorId(id); 
		return ResponseEntity.ok(restauranteDtoManager.toModel(restauranteSalvo));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteModel cadastrar(@RequestBody  @Valid RestauranteInput restauranteModel) {
		Restaurante restaurante = restauranteDtoManager.converterToDomainObject(restauranteModel);
		restaurante = restauranteService.salvar(restaurante);
		return restauranteDtoManager.toModel(restaurante);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public RestauranteModel atualizar (@PathVariable Long id, @RequestBody  @Valid RestauranteInput restauranteInput){
		Restaurante restauranteAtual = this.restauranteService.buscarPorId(id); 
		restauranteDtoManager.copyToDomainObject(restauranteInput, restauranteAtual);
		return restauranteDtoManager.toModel(this.restauranteService.atualizar(id, restauranteAtual));
	}
	
	@PutMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> ativarRestaurante(@PathVariable Long id) {
		this.restauranteService.ativar(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> ativarMultiplos(@RequestBody List<Long> restaurantesIds) {
		this.restauranteService.ativar(restaurantesIds);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> inativarMultiplos(@RequestBody List<Long> restaurantesIds) {
		this.restauranteService.inativar(restaurantesIds);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}/fechamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> fecharRestaurante(@PathVariable Long id) {
		this.restauranteService.fechar(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}/abertura")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> abrirRestaurante(@PathVariable Long id) {
		this.restauranteService.abrir(id);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> inativarRestaurante(@PathVariable Long id) {
		this.restauranteService.inativar(id);
		return ResponseEntity.noContent().build();
	}
	
}
