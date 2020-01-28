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

import com.felipe.algafood.api.docs.EstadoControllerOpenApi;
import com.felipe.algafood.api.dto.converters.EstadoDtoManager;
import com.felipe.algafood.api.dto.inputs.EstadoInput;
import com.felipe.algafood.api.dto.model.EstadoModel;
import com.felipe.algafood.domain.model.Estado;
import com.felipe.algafood.domain.service.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController implements EstadoControllerOpenApi{

	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private EstadoDtoManager dtoManager;
	
	@GetMapping
	public ResponseEntity<List<EstadoModel>> buscar() {
		return ResponseEntity.ok().body(dtoManager.toCollectionDtoModel(this.estadoService.getEstadoRepository().findAll()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EstadoModel> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(dtoManager.conveterToDtoModel(this.estadoService.buscarPorId(id)));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoModel salvar (@RequestBody @Valid EstadoInput estadoInput) {
		return dtoManager.conveterToDtoModel(this.estadoService.salvar(this.dtoManager.converterToDomainObject(estadoInput)));
				
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EstadoModel> atualizar(@PathVariable Long id, @RequestBody @Valid EstadoInput estadoInput) {
		Estado estado = estadoService.buscarPorId(id);
		dtoManager.copyToDomainObject(estadoInput, estado);
		return ResponseEntity.ok(dtoManager.conveterToDtoModel(this.estadoService.salvar(estado)));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		estadoService.excluir(id);
	}
	
}
