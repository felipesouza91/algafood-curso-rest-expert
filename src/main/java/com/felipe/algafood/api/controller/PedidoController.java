package com.felipe.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.algafood.api.dto.converters.PedidoDtoManager;
import com.felipe.algafood.api.dto.inputs.PedidoInput;
import com.felipe.algafood.api.dto.model.PedidoModel;
import com.felipe.algafood.api.dto.model.resumo.PedidoResumoModel;
import com.felipe.algafood.domain.model.Pedido;
import com.felipe.algafood.domain.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private PedidoDtoManager dtoManager;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<PedidoResumoModel> listarTodos() {
		return dtoManager.toCollectionDtoResumoModel(this.pedidoService.buscarTodos());
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public PedidoModel listarPorId(@PathVariable Long id) {
		return dtoManager.conveterToDtoModel(this.pedidoService.buscarPorId(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoModel salvar(@RequestBody @Valid PedidoInput pedidoInput) {
		Pedido pedido = dtoManager.converterToDomainObject(pedidoInput);
		return this.dtoManager.conveterToDtoModel(this.pedidoService.salvar(pedido));
	}
}
