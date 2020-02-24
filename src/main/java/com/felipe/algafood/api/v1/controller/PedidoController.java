package com.felipe.algafood.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.algafood.api.v1.docs.PedidoControllerOpenApi;
import com.felipe.algafood.api.v1.dto.converters.PedidoDtoManager;
import com.felipe.algafood.api.v1.dto.converters.PedidoResumoDtoManager;
import com.felipe.algafood.api.v1.dto.inputs.PedidoInput;
import com.felipe.algafood.api.v1.dto.model.PedidoModel;
import com.felipe.algafood.api.v1.dto.model.resumo.PedidoResumoModel;
import com.felipe.algafood.core.security.CheckSecurity;
import com.felipe.algafood.domain.filter.PedidoFilter;
import com.felipe.algafood.domain.model.Pedido;
import com.felipe.algafood.domain.service.PedidoService;

@RestController
@RequestMapping(path = "/v1/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
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
	@CheckSecurity.Pedido.PodePesquisar
	public PagedModel<PedidoResumoModel> listarTodos( PedidoFilter filter, Pageable pageable) {
		Page<Pedido> page = this.pedidoService.buscarTodos(filter, pageable);
		PagedModel<PedidoResumoModel> pedidoResumoPaged = pagedResourceAssembled.toModel(page, resumoDtoManager); 
		
		return pedidoResumoPaged;
	}
	
	@GetMapping("/{codigo}")
	@ResponseStatus(HttpStatus.OK)
	@CheckSecurity.Pedido.PodeBuscar
	public PedidoModel listarPorId(@PathVariable String codigo) {
		return dtoManager.toModel(this.pedidoService.buscarPorCodigo(codigo));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@CheckSecurity.Pedido.PodeCriar
	public PedidoModel salvar(@RequestBody @Valid PedidoInput pedidoInput) {
		Pedido pedido = dtoManager.converterToDomainObject(pedidoInput);
		return this.dtoManager.toModel(this.pedidoService.salvar(pedido));
	}
}
