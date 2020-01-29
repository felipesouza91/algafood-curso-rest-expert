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

import com.felipe.algafood.api.docs.RestauranteUsuarioControllerOpenApi;
import com.felipe.algafood.api.dto.converters.UsuarioDtoManager;
import com.felipe.algafood.api.dto.model.UsuarioModel;
import com.felipe.algafood.domain.model.Restaurante;
import com.felipe.algafood.domain.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes/{id}/usuarios")
public class RestauranteUsuarioController implements RestauranteUsuarioControllerOpenApi{

	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private UsuarioDtoManager dtoManager;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<UsuarioModel> listarTodosUsuarios(@PathVariable Long id) {
		Restaurante restaurante = this.restauranteService.buscarPorId(id);
		return this.dtoManager.toCollectionDtoModel(restaurante.getResponsaveis());
	}
	
	@PutMapping("/{idUsuario}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associarResponsavel(@PathVariable Long id, @PathVariable Long idUsuario) {
		this.restauranteService.associarResponavel(id, idUsuario);
	}
	
	@DeleteMapping("/{idUsuario}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociarResponsavel(@PathVariable Long id, @PathVariable Long idUsuario) {
		this.restauranteService.desassociarResponavel(id, idUsuario);
	}
}
