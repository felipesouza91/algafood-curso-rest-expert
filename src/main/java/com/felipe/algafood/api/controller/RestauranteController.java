package com.felipe.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.felipe.algafood.api.dto.converters.RestauranteDtoManager;
import com.felipe.algafood.api.dto.inputs.RestauranteInput;
import com.felipe.algafood.api.dto.model.RestauranteModel;
import com.felipe.algafood.core.validation.ValidacaoException;
import com.felipe.algafood.domain.model.Restaurante;
import com.felipe.algafood.domain.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private SmartValidator smartValidator;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private RestauranteDtoManager restauranteDtoManager;
	
	@GetMapping
	public ResponseEntity<List<RestauranteModel>> buscarTodos() {
		List<Restaurante> restaurantes = this.restauranteService.getRestauranteRepository().findAll();
		return ResponseEntity.ok(restauranteDtoManager.toCollectionDtoModel(restaurantes));
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
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos, HttpServletRequest request) {
		Restaurante restauranteAtual = this.restauranteService.buscarPorId(id);
		merge(campos, restauranteAtual, request );
		validate(restauranteAtual, "restaurante");
		return ResponseEntity.ok(this.restauranteService.atualizar(id, restauranteAtual));
	}
	
	@PutMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativarRestaurante(@PathVariable Long id) {
		this.restauranteService.ativar(id);
	}
	
	@DeleteMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativarRestaurante(@PathVariable Long id) {
		this.restauranteService.inativar(id);
	}

	private void validate(Restaurante restaurante, String objectName) {
		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
		smartValidator.validate(restaurante, bindingResult);
		if(bindingResult.hasErrors()) {
			throw new ValidacaoException(bindingResult);
		}
	}

	private void merge(Map<String, Object> campos, Restaurante restauranteDestino, HttpServletRequest request) {
		ServletServerHttpRequest httpRequest = new ServletServerHttpRequest(request);
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
			Restaurante restauranteOrigem = objectMapper.convertValue(campos, Restaurante.class);
			campos.forEach((nomeProp , valorPro) -> {
				Field field = ReflectionUtils.findField(Restaurante.class, nomeProp);
				field.setAccessible(true);
				Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
				ReflectionUtils.setField(field, restauranteDestino, novoValor);
			});
		} catch (IllegalArgumentException e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, httpRequest);
		}
		
	}
	

	
	
	
}
