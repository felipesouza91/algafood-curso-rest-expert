package com.felipe.algafood.api.dto.converters;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.felipe.algafood.api.controller.CidadeController;
import com.felipe.algafood.api.controller.FormaPagamentoController;
import com.felipe.algafood.api.controller.PedidoController;
import com.felipe.algafood.api.controller.RestauranteController;
import com.felipe.algafood.api.controller.RestauranteProdutoController;
import com.felipe.algafood.api.controller.UsuarioController;
import com.felipe.algafood.api.dto.inputs.PedidoInput;
import com.felipe.algafood.api.dto.model.PedidoModel;
import com.felipe.algafood.domain.model.Pedido;

@Component
public class PedidoDtoManager extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {
	

	@Autowired
	private ModelMapper modelMapper;

	public PedidoDtoManager() {
		super(PedidoController.class, PedidoModel.class);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public PedidoModel toModel(Pedido pedido) {
		PedidoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
		modelMapper.map(pedido, pedidoModel);
		pedidoModel.add(linkTo(PedidoController.class).withRel("pedidos"));
		pedidoModel.getEnderecoEntrega().getCidade().add(linkTo(
						methodOn(CidadeController.class).buscarPorId(pedidoModel.getEnderecoEntrega().getCidade().getId())).withSelfRel()
				);
		pedidoModel.getFormaPagamento().add(linkTo(
					methodOn(FormaPagamentoController.class).buscarPorId(pedidoModel.getFormaPagamento().getId(),null)).withSelfRel()
				);
		pedidoModel.getRestaurante().add(linkTo(
					methodOn(RestauranteController.class).buscarPorId(pedidoModel.getRestaurante().getId())).withSelfRel()
				);
		pedidoModel.getCliente().add(linkTo(
					methodOn(UsuarioController.class).listarPorCodigo(pedidoModel.getCliente().getId())).withSelfRel()
				);
		pedidoModel.getItens().forEach(item -> {
			item.add(linkTo(
					methodOn(RestauranteProdutoController.class).buscarPorId(pedidoModel.getRestaurante().getId(), item.getProdutoId())
					).withRel("produto"));
		});
		return pedidoModel;
	}
	
	@Override
	public CollectionModel<PedidoModel> toCollectionModel(Iterable<? extends Pedido> entities) {
		// TODO Auto-generated method stub
		return super.toCollectionModel(entities);
	}
	
	public Pedido converterToDomainObject(PedidoInput pedidoInput) {
		return modelMapper.map(pedidoInput, Pedido.class);
	}

	public void copyToDomainObject(PedidoInput objectInput, Pedido object) {
		modelMapper.map(objectInput, object);
	}

}
