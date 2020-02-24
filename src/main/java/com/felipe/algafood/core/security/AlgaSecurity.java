package com.felipe.algafood.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import com.felipe.algafood.domain.service.PedidoService;
import com.felipe.algafood.domain.service.RestauranteService;

@Component
public class AlgaSecurity {
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private PedidoService pedidoService;
	
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public boolean isAutenticado() {
		return getAuthentication().isAuthenticated();
	}
		
	public Long getUsuarioId() {
		Jwt jwt = (Jwt) getAuthentication().getPrincipal();
		return jwt.getClaim("id");
	}
	
	public boolean gerenciarRestaurante(Long restaurante) {
		if (restaurante == null) { 
			return false;
		}
		return restauranteService.getRestauranteRepository().existsResponsavel(restaurante, getUsuarioId());
	}
	
	public boolean temScopeWrite() {
		return hasAuthority("SCOPE_WRITE");
	}
	
	public boolean temScopeRead() {
		return hasAuthority("SCOPE_READ");
	}
	
	public boolean gerenciarPedido(String codigoPedido) {
		return pedidoService.getPedidoRepository().gerenciarPedido(codigoPedido, getUsuarioId());
	}
	
	public boolean usuarioAutenticadoIgual(Long usuarioId) {
		return getUsuarioId() != null && usuarioId != null && getUsuarioId().equals(usuarioId);
	}
	
	public boolean hasAuthority(String authority) {
		return getAuthentication().getAuthorities().stream().anyMatch(autho-> autho.getAuthority().equals(authority));
	}
	
	public boolean podeGerenciarPedidos(String codigo) {
		return temScopeWrite()  && ( hasAuthority("GERENCIAR_PEDIDOS") || gerenciarPedido(codigo) );
	}
	
	public boolean podeConsultarCidade() {
		return isAutenticado() && temScopeRead();
	}

	public boolean podeConsultarEstado() {
		return isAutenticado() && temScopeRead();
	}

	public boolean podeConsultarCozinha() {
		return isAutenticado();
	}

	public boolean podeConsultarFormaPagamento() {
		return isAutenticado() && temScopeRead();
	}

	public boolean podeConsultarRestaurante() {
		return isAutenticado() && temScopeRead();
	}

	public boolean podeConsultarUsuarioGrupoPermissao() {
		return temScopeRead() && hasAuthority("CONSULTAR_USUARIOS_GRUPOS_PERMISSOES");
	}

	public boolean podeConsultarPedido(Long restauranteId, Long clienteId) {
		return temScopeRead() && ( hasAuthority("CONSULTAR_PEDIDOS")
				|| gerenciarRestaurante(restauranteId) || usuarioAutenticadoIgual(clienteId));
	}
	
}
