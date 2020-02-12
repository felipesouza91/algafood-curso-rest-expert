package com.felipe.algafood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.algafood.api.v1.AlgaLinks;
import com.felipe.algafood.api.v1.docs.RestauranteUsuarioControllerOpenApi;
import com.felipe.algafood.api.v1.dto.converters.UsuarioDtoManager;
import com.felipe.algafood.api.v1.dto.model.UsuarioModel;
import com.felipe.algafood.domain.model.Restaurante;
import com.felipe.algafood.domain.service.RestauranteService;

@RestController
@RequestMapping(path = "/v1/restaurantes/{id}/responsaveis", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteUsuarioController implements RestauranteUsuarioControllerOpenApi{

	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private UsuarioDtoManager dtoManager;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CollectionModel<UsuarioModel> listarTodosUsuarios(@PathVariable Long id) {
		Restaurante restaurante = this.restauranteService.buscarPorId(id);
		CollectionModel<UsuarioModel> collection = this.dtoManager.toCollectionModel(restaurante.getResponsaveis()).removeLinks()
				.add(algaLinks.linkToRestauranteUsuarios(id))
				.add(algaLinks.linkToRestauranteResponsavelAssociar(id, "associar"));
		collection.getContent().forEach(responsavel -> 
			responsavel.add(algaLinks.linkToRestauranteResponsavelDesassociar(id, responsavel.getId(), "desassociar")));
		return collection;
	}
	
	@PutMapping("/{idResponsavel}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associarResponsavel(@PathVariable Long id, @PathVariable Long idResponsavel) {
		this.restauranteService.associarResponavel(id, idResponsavel);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{idResponsavel}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociarResponsavel(@PathVariable Long id, @PathVariable Long idResponsavel) {
		this.restauranteService.desassociarResponavel(id, idResponsavel);
		return ResponseEntity.noContent().build();
	}
}
