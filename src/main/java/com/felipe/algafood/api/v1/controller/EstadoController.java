package com.felipe.algafood.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.felipe.algafood.api.v1.docs.EstadoControllerOpenApi;
import com.felipe.algafood.api.v1.dto.converters.EstadoDtoManager;
import com.felipe.algafood.api.v1.dto.inputs.EstadoInput;
import com.felipe.algafood.api.v1.dto.model.EstadoModel;
import com.felipe.algafood.core.security.CheckSecurity;
import com.felipe.algafood.domain.model.Estado;
import com.felipe.algafood.domain.service.EstadoService;

@RestController
@RequestMapping(path="/v1/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController implements EstadoControllerOpenApi{

	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private EstadoDtoManager dtoManager;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@CheckSecurity.Estado.PodeConsultar
	public CollectionModel<EstadoModel> buscar() {
		return dtoManager.toCollectionModel(this.estadoService.getEstadoRepository().findAll());
	}
	
	@GetMapping("/{id}")
	@CheckSecurity.Estado.PodeConsultar
	public EstadoModel buscarPorId(@PathVariable Long id) {
		return dtoManager.toModel(this.estadoService.buscarPorId(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@CheckSecurity.Estado.PodeEditar
	public EstadoModel salvar (@RequestBody @Valid EstadoInput estadoInput) {
		return dtoManager.toModel(this.estadoService.salvar(this.dtoManager.converterToDomainObject(estadoInput)));
				
	}
	
	@PutMapping("/{id}")
	@CheckSecurity.Estado.PodeEditar
	public ResponseEntity<EstadoModel> atualizar(@PathVariable Long id, @RequestBody @Valid EstadoInput estadoInput) {
		Estado estado = estadoService.buscarPorId(id);
		dtoManager.copyToDomainObject(estadoInput, estado);
		return ResponseEntity.ok(dtoManager.toModel(this.estadoService.salvar(estado)));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@CheckSecurity.Estado.PodeEditar
	public void excluir(@PathVariable Long id) {
		estadoService.excluir(id);
	}
	
}
