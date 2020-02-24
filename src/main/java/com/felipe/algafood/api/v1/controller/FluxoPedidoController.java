package com.felipe.algafood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.algafood.api.v1.docs.FluxoPedidoControllerOpenApi;
import com.felipe.algafood.core.security.CheckSecurity;
import com.felipe.algafood.domain.service.PedidoService;

@RestController
@RequestMapping(path = "/v1/pedidos/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
public class FluxoPedidoController implements FluxoPedidoControllerOpenApi {

	@Autowired
	private PedidoService pedidoService;

	@PutMapping("/confirmacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@CheckSecurity.Pedido.PodeGerenciarPedidos
	public ResponseEntity<Void> confirmar(@PathVariable String codigo) {
		pedidoService.confirmar(codigo);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/entregar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@CheckSecurity.Pedido.PodeGerenciarPedidos
	public ResponseEntity<Void> entregar(@PathVariable String codigo) {
		pedidoService.entregar(codigo);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/cancelar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@CheckSecurity.Pedido.PodeGerenciarPedidos
	public ResponseEntity<Void> cancelar(@PathVariable String codigo) {
		pedidoService.cancelar(codigo);
		return ResponseEntity.noContent().build();
	}
}
