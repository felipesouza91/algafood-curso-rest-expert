package com.felipe.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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

import com.felipe.algafood.domain.model.Cozinha;
import com.felipe.algafood.domain.repository.CozinhaRepository;
import com.felipe.algafood.domain.service.CozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CozinhaService cozinhaService;
	
	
	@GetMapping
	public ResponseEntity<List<Cozinha>> listar() {
		return ResponseEntity.ok().body(cozinhaRepository.findAll()) ;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cozinha> buscarPorId(@PathVariable Long id) {
		this.cozinhaService.buscarPorId(id);
		return ResponseEntity.ok().body(this.cozinhaService.buscarPorId(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha savar (@RequestBody @Valid Cozinha cozinha) {
		return cozinhaService.salvar(cozinha);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @RequestBody @Valid Cozinha cozinha) {
		Cozinha cozinhaAtual = this.cozinhaService.buscarPorId(id);
		BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
		cozinhaService.salvar(cozinhaAtual);
		return ResponseEntity.ok(cozinhaAtual);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Cozinha> remover(@PathVariable Long id) {
		this.cozinhaService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
