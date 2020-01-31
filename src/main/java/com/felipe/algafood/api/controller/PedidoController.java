package com.felipe.algafood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.algafood.api.docs.PedidoControllerOpenApi;
import com.felipe.algafood.api.dto.converters.PedidoDtoManager;
import com.felipe.algafood.api.dto.converters.PedidoResumoDtoManager;
import com.felipe.algafood.api.dto.inputs.PedidoInput;
import com.felipe.algafood.api.dto.model.PedidoModel;
import com.felipe.algafood.api.dto.model.resumo.PedidoResumoModel;
import com.felipe.algafood.domain.filter.PedidoFilter;
import com.felipe.algafood.domain.model.Pedido;
import com.felipe.algafood.domain.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController implements PedidoControllerOpenApi {

	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private PedidoDtoManager dtoManager;
	
	@Autowired
	private PedidoResumoDtoManager resumoDtoManager;
	
	@Autowired
	private PagedResourcesAssembler<Pedido> pagedResourceAssembled  ;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public PagedModel<PedidoResumoModel> listarTodos( PedidoFilter filter, Pageable pageable) {
		Page<Pedido> page = this.pedidoService.buscarTodos(filter, pageable);
		PagedModel<PedidoResumoModel> pedidoResumoPaged = pagedResourceAssembled.toModel(page, resumoDtoManager); 
		
		return pedidoResumoPaged;
	}
	
	@GetMapping("/{codigo}")
	@ResponseStatus(HttpStatus.OK)
	public PedidoModel listarPorId(@PathVariable String codigo) {
		return dtoManager.toModel(this.pedidoService.buscarPorCodigo(codigo));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoModel salvar(@RequestBody @Valid PedidoInput pedidoInput) {
		Pedido pedido = dtoManager.converterToDomainObject(pedidoInput);
		return this.dtoManager.toModel(this.pedidoService.salvar(pedido));
	}
}
