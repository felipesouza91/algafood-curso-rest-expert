package com.felipe.algafood.api.v1;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

import com.felipe.algafood.api.v1.controller.CidadeController;
import com.felipe.algafood.api.v1.controller.CozinhaController;
import com.felipe.algafood.api.v1.controller.EstadoController;
import com.felipe.algafood.api.v1.controller.EstatisticaController;
import com.felipe.algafood.api.v1.controller.FluxoPedidoController;
import com.felipe.algafood.api.v1.controller.FormaPagamentoController;
import com.felipe.algafood.api.v1.controller.GrupoController;
import com.felipe.algafood.api.v1.controller.GrupoPermissaoController;
import com.felipe.algafood.api.v1.controller.PedidoController;
import com.felipe.algafood.api.v1.controller.PermissaoController;
import com.felipe.algafood.api.v1.controller.RestauranteController;
import com.felipe.algafood.api.v1.controller.RestauranteFormaPagamentoController;
import com.felipe.algafood.api.v1.controller.RestauranteProdutoController;
import com.felipe.algafood.api.v1.controller.RestauranteProdutoFotoController;
import com.felipe.algafood.api.v1.controller.RestauranteUsuarioController;
import com.felipe.algafood.api.v1.controller.UsuarioController;
import com.felipe.algafood.api.v1.controller.UsuarioGrupoController;

@Component
public class AlgaLinks {
	
	private static final TemplateVariables PAGINACAO_VARIABLES = new TemplateVariables(
			new TemplateVariable("page",VariableType.REQUEST_PARAM),
			new TemplateVariable("size",VariableType.REQUEST_PARAM),
			new TemplateVariable("sort",VariableType.REQUEST_PARAM));

	
	public Link linkToPedidos(String rel) {
		String pedidoUrl = linkTo(PedidoController.class).toUri().toString();

		TemplateVariables filterVariables = new TemplateVariables(
				new TemplateVariable("clienteId",VariableType.REQUEST_PARAM),
				new TemplateVariable("restauranteId",VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoInicio",VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoFim",VariableType.REQUEST_PARAM)
				);
		return new Link(UriTemplate.of(pedidoUrl, 
				PAGINACAO_VARIABLES.concat(filterVariables)), rel);
	}
	
	public Link linkToPedidos() {
		return linkToPedidos(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToConfirmacaoPedido( String codigo, String rel) {
		return linkTo(methodOn(FluxoPedidoController.class).confirmar(codigo)).withRel(rel);
	}
	
	public Link linkToEntregaPedido( String codigo, String rel) {
		return linkTo(methodOn(FluxoPedidoController.class).entregar(codigo)).withRel(rel);
	}
	
	public Link linkToCancelamentoPedido( String codigo, String rel) {
		return linkTo(methodOn(FluxoPedidoController.class).cancelar(codigo)).withRel(rel);
	}
	
	public Link LinkToCidades(String rel) {
		return linkTo(CidadeController.class).withRel(rel);
	}
	
	public Link linkToCidade(Long id, String rel) {
		return linkTo(methodOn(CidadeController.class).buscarPorId(id)).withRel(rel);
	}
	
	public Link LinkToCidades() {
		return LinkToCidades(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToCidade(Long id) {
		return linkToCidade(id, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToCozinhas(String rel) {
		return linkTo(CozinhaController.class).withRel(rel);
	}
	
	public Link linkToCozinha(Long id, String rel) {
		return linkTo(methodOn(CozinhaController.class).buscarPorId(id)).withRel(rel);
	}
	
	public Link linkToCozinhas() {
		return linkToCozinhas(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToCozinha(Long id) {
		return linkToCozinha(id, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToEstado(Long id) {
		return linkToEstado(id, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToEstado(Long id, String rel) {
		return linkTo(methodOn(EstadoController.class).buscarPorId(id)).withRel(rel);
	}
	
	public Link linkToEstados() {
		return linkToEstados(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToEstados(String rel) {
		return linkTo(EstadoController.class).withRel(rel);
	}
	
	public Link linkToFormaPagamento(Long id) {
		return linkToFormaPagamento(id, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToFormaPagamento(Long id, String rel) {
		return linkTo(methodOn(FormaPagamentoController.class).buscarPorId(id, null)).withRel(rel);
	}
	
	public Link linkToFormasPagamentos(String rel) {
		return linkTo(FormaPagamentoController.class).withRel(rel);
	}
	
	public Link linkToFormasPagamentos() {
		return linkToFormasPagamentos(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToGrupos(String rel) {
		return linkTo(GrupoController.class).withRel(rel);
	}
	
	public Link linkToGrupo(Long id, String rel) {
		return linkTo(methodOn(GrupoController.class).listaPorId(id)).withRel(rel);
	}
	
	public Link linkToGrupos() {
		return linkToGrupos(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToGrupo(Long id) {
		return linkToGrupo(id, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToGrupoPermissoes(Long idGrupo, String rel) {
		return linkTo(methodOn(GrupoPermissaoController.class).listarTodos(idGrupo)).withRel(rel);
	}
	
	public Link linkToGrupoPermissoes(Long idGrupo) {
		return linkToGrupoPermissoes(idGrupo, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToUsuario(Long id, String rel) {
		return linkTo(methodOn(UsuarioController.class).listarPorCodigo(id)).withRel(rel);
	}
	
	public Link linkToUsuarios(String rel ) {
		return linkTo(UsuarioController.class).withRel(rel);
	}
	
	public Link linkToUsuarios() {
		return linkToUsuarios(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToUsuario(Long id) {
		return linkToUsuario(id, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToGruposUsuarios(Long id, String rel) {
		return linkTo(methodOn(UsuarioGrupoController.class).listarTodos(id)).withRel(rel);
	}
	
	public Link linkToGruposUsuarios(Long id) {
		return linkToGruposUsuarios(id,IanaLinkRelations.SELF.value()); 
	}

	public Link linkToRestaurantesFormasPagamentos(Long idRestaurante, String rel)  {
		return linkTo(methodOn(RestauranteFormaPagamentoController.class).listarTodos(idRestaurante)).withRel(rel);
	}
	
	public Link linkToRestaurante(Long id) {
		return linkToRestaurante(id, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToRestaurantesFormasPagamentos(Long idRestaurante)  {
		return linkToRestaurantesFormasPagamentos(idRestaurante, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToRestauranteFormaPagamentoDesassociar(Long idRestaurante, Long idFormaPagamento, String rel ) {
		return linkTo(methodOn(RestauranteFormaPagamentoController.class).desassociar(idRestaurante, idFormaPagamento)).withRel(rel);
	}
	
	public Link linkToRestauranteFormaPagamentoDesassociar(Long idRestaurante, Long idFormaPagamento ) {
		return linkToRestauranteFormaPagamentoDesassociar(idRestaurante, idFormaPagamento, IanaLinkRelations.SELF.value() );
	}
	
	public Link linkToRestauranteFormaPagamentoAssociar(Long idRestaurante, String rel ) {
		return linkTo(methodOn(RestauranteFormaPagamentoController.class).associar(idRestaurante, null)).withRel(rel);
	}
	
	public Link linkToRestauranteFormaPagamentoAssociar(Long idRestaurante) {
		return linkToRestauranteFormaPagamentoAssociar(idRestaurante,  IanaLinkRelations.SELF.value() );
	}
	
	public Link linkToRestauranteResponsavelAssociar(Long idRestaurante, String rel ) {
		return linkTo(methodOn(RestauranteUsuarioController.class).associarResponsavel(idRestaurante, null)).withRel(rel);
	}
	
	public Link linkToRestauranteResponsavelAssociar(Long idRestaurante) {
		return linkToRestauranteResponsavelAssociar(idRestaurante,  IanaLinkRelations.SELF.value() );
	}
	
	public Link linkToRestauranteResponsavelDesassociar(Long idRestaurante, Long idResponsavel, String rel ) {
		return linkTo(methodOn(RestauranteUsuarioController.class).desassociarResponsavel(idRestaurante, idResponsavel)).withRel(rel);
	}
	
	public Link linkToRestauranteResponsavelDesassociar(Long idRestaurante, Long idResponsavel ) {
		return linkToRestauranteResponsavelDesassociar(idRestaurante, idResponsavel, IanaLinkRelations.SELF.value() );
	}
	
	public Link linkToRestaurante(Long id, String rel) {
		return linkTo(methodOn(RestauranteController.class).buscarPorId(id)).withRel(rel);
	}
	
	public Link linkToRestauranteUsuarios(Long idRestaurante, String rel) {
		return linkTo(methodOn(RestauranteUsuarioController.class).listarTodosUsuarios(idRestaurante)).withRel(rel);
	}
	
	public Link linkToRestauranteUsuarios(Long idRestaurante) {
		return linkToRestauranteUsuarios(idRestaurante, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToRestaurantes(String rel) {
		String restauranteUrl = linkTo(RestauranteController.class).toUri().toString();
		TemplateVariables variables = new TemplateVariables(
				new TemplateVariable("projecao",VariableType.REQUEST_PARAM));
		return new Link(UriTemplate.of(restauranteUrl, variables), rel);
	}
	
	public Link linkToRestaurantes() {
		return linkToRestaurantes(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToRestauranteAtivo(Long idRestaurante, String rel) {
		return linkTo(methodOn(RestauranteController.class).ativarRestaurante(idRestaurante)).withRel(rel);
	}
	
	public Link linkToRestauranteAtivo(Long idRestaurante) {
		return linkToRestauranteAtivo(idRestaurante, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToRestauranteInativo(Long idRestaurante, String rel) {
		return linkTo(methodOn(RestauranteController.class).inativarRestaurante(idRestaurante)).withRel(rel);
	}
	
	public Link linkToRestauranteInativo(Long idRestaurante) {
		return linkToRestauranteInativo(idRestaurante, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToRestauranteAbrir(Long idRestaurante, String rel) {
		return linkTo(methodOn(RestauranteController.class).abrirRestaurante(idRestaurante)).withRel(rel);
	}
	
	public Link linkToRestauranteAbrir(Long idRestaurante) {
		return linkToRestauranteAbrir(idRestaurante, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToRestauranteFechar(Long idRestaurante, String rel) {
		return linkTo(methodOn(RestauranteController.class).fecharRestaurante(idRestaurante)).withRel(rel);
	}
	
	public Link linkToRestauranteFechar(Long idRestaurante) {
		return linkToRestauranteFechar(idRestaurante, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToProduto(Long idRestaurante, Long idProduto) {
		return linkToProduto(idRestaurante, idProduto, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToProduto(Long idRestaurante, Long idProduto, String rel) {
		return linkTo(methodOn(RestauranteProdutoController.class).buscarPorId(idRestaurante, idProduto)).withRel(rel);
	}
	
	public Link linkToProdutos(Long idRestaurante, String rel) {
		String restauranteUrl = linkTo(methodOn(RestauranteProdutoController.class).buscarTodos(idRestaurante, null)).toUri().toString();
		TemplateVariables variables = new TemplateVariables(
				new TemplateVariable("incluirInativo",VariableType.REQUEST_PARAM));
		return new Link(UriTemplate.of(restauranteUrl, variables), rel);
	}
	
	public Link linkToProdutos(Long idRestaurante) {
		return linkToProdutos(idRestaurante, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToProdutoFoto(Long idRestaurante, Long idProduto) {
		return linkToProdutoFoto(idRestaurante, idProduto, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToProdutoFoto(Long idRestaurante, Long idProduto, String rel) {
		return linkTo(methodOn(RestauranteProdutoFotoController.class).buscarFoto(idRestaurante, idProduto)).withRel(rel);
	}

	public Link linkToPermissoes(String rel) {
		return linkTo(PermissaoController.class).withRel(rel);
	}
	
	public Link linkToPermissoes() {
		return linkTo(PermissaoController.class).withRel(IanaLinkRelations.SELF.value());
	}

	public Link linkToGrupoAssociarPermissao(Long id, String rel) {
		return linkTo(methodOn(GrupoPermissaoController.class).associar(id, null)).withRel(rel);
	}
	
	public Link linkToGrupoAssociarPermissao(Long id) {
		return linkToGrupoAssociarPermissao(id, IanaLinkRelations.SELF.value());
	}

	public Link linkToGrupoDesassociarPermissao(Long idGrupo, Long idPermissao, String rel) {
		return linkTo(methodOn(GrupoPermissaoController.class).desassociar(idGrupo, idPermissao)).withRel(rel);
	}

	public Link linkToUsuarioDesassociarGrupo(Long id, Long idGrupo, String rel) {
		return linkTo(methodOn(UsuarioGrupoController.class).desassociarGrupo(id, idGrupo)).withRel(rel);
	}

	public Link LinkToEstatisticas(String rel) {
		return linkTo(EstatisticaController.class).withRel(rel);
	}

	public Link linkToEstatisticaVendaDireta(String rel) {
		String restauranteUrl = linkTo(methodOn(EstatisticaController.class).consultarVendasDiarias(null, null)).toUri().toString();
		TemplateVariables variables = new TemplateVariables(
				new TemplateVariable("restId",VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoInicio",VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoFim",VariableType.REQUEST_PARAM),
				new TemplateVariable("timeOffset",VariableType.REQUEST_PARAM));
		return new Link(UriTemplate.of(restauranteUrl, variables), rel);
	}
}
