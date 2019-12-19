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
import org.springframework.web.bind.annotation.RestController;

import com.felipe.algafood.api.dto.converters.CidadeDtoManager;
import com.felipe.algafood.api.dto.inputs.CidadeInput;
import com.felipe.algafood.api.dto.model.CidadeModel;
import com.felipe.algafood.domain.model.Cidade;
import com.felipe.algafood.domain.service.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private CidadeDtoManager cidadeDtoManager;

	@GetMapping
	public ResponseEntity<List<CidadeModel>> buscar() {
		List<CidadeModel> list = cidadeDtoManager.toCollectionDtoModel(this.cidadeService.getCidadeRepository().findAll());
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CidadeModel> buscarPorId(@PathVariable Long id) {
			return ResponseEntity.ok(
					cidadeDtoManager.conveterToDtoModel(
							this.cidadeService.buscarPorId(id)
							)
					);
	}
	
	@PostMapping
	public ResponseEntity<?> salvar (@RequestBody @Valid CidadeInput cidadeInput) {
		Cidade cidade = this.cidadeDtoManager.converterToDomainObject(cidadeInput);
		cidade = this.cidadeService.salvar(cidade);
		return ResponseEntity.status(HttpStatus.CREATED).body(this.cidadeDtoManager.conveterToDtoModel(cidade));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid CidadeInput cidadeInput) {
		Cidade cidade = cidadeService.atualizar(id, this.cidadeDtoManager.converterToDomainObject(cidadeInput));
		return ResponseEntity.ok(this.cidadeDtoManager.conveterToDtoModel(cidade));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		cidadeService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
}
