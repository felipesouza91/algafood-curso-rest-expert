package com.felipe.algafood.core.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {

	public @interface Cozinha {
		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('EDITAR_COZINHA')")
		public @interface PodeEditar {
		}

		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("@algaSecurity.podeConsultarCozinha()")
		public @interface PodeBuscar {
		}

	}

	public @interface Restaurante {

		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("@algaSecutiry.podeConsultarRestaurante()")
		public @interface PodeBuscar {
		}

		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('EDITAR_RESTAURANTES') and hasAuthority('SCOPE_WRITE')")
		public @interface PodeGerenciarCadastro {
		}

		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('EDITAR_RESTAURANTES') or"
				+ "@algaSecurity.gerenciarRestaurante(#id))")
		public @interface PodeGerenciarFuncionamento {
		}

	}

	public @interface Pedido {

		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		public @interface PodeCriar {
		}

		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@PostAuthorize("hasAuthority('CONSULTAR_PEDIDOS')"
				+ "or @algaSecurity.getUsuarioId() == returnObject.cliente.id or"
				+ "@algaSecurity.gerenciarRestaurante(returnObject.restaurante.id)")
		public @interface PodeBuscar {
		}

		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("@algaSecurity.podeConsultarPedido(#filter.restauranteId,#filter.clienteId)")
		public @interface PodePesquisar {
		}

		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("@algaSecurity.podeGerenciarPedidos(#codigo)")
		public @interface PodeGerenciarPedidos {
		}
	}

	public @interface FormaPagamento {

		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and ( hasAuthority('EDITAR_FORMAS_PAGAMENTO') or "
				+ "@algaSecurity.gerenciarRestaurante(#filter.restauranteId) or "
				+ "@algaSecurity.usuarioAutenticadoIgual(#filter.clienteId) )")
		public @interface PodeEditar {
		}

		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("@algaSecutiry.podeConsultarFormaPagamento()")
		public @interface PodeConsultar {
		}
	}

	public @interface Cidade {

		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("@algaSecurity.podeConsultarCidade()")
		public @interface PodeConsultar {
		}

		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_CIDADES')")
		public @interface PodeEditar {
		}
	}

	public @interface Estado {

		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("@algaSecutiry.podeConsultarEstado()")
		public @interface PodeConsultar {
		}

		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_ESTADOS') ")
		public @interface PodeEditar {
		}
	}

	public @interface Estatistica {

		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('GERAR_RELATORIOS')")
		public @interface PodeConsultar {}
	}

	public @interface UsuarioGrupoPermissoes {

		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and @algaSecurity.getUsuarioId() == #id")
		public @interface PodeAlterarPropriaSenha {
		}

		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and ( hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES') or "
				+ "@algaSecurity.usuarioAutenticadoIgual(#id) )")
		public @interface PodeAlterarUsuario {
		}

		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("@algaSecutiry.podeConsultarUsuarioGrupoPermissao()")
		public @interface PodeConsultar {
		}

		@Retention(RUNTIME)
		@Target(METHOD)
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES') ")
		public @interface PodeEditar {
		}
	}
}
