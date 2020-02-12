package com.felipe.algafood.api.v1.dto.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.felipe.algafood.api.v1.AlgaLinks;
import com.felipe.algafood.api.v1.controller.PedidoController;
import com.felipe.algafood.api.v1.dto.inputs.PedidoInput;
import com.felipe.algafood.api.v1.dto.model.PedidoModel;
import com.felipe.algafood.domain.model.Pedido;

@Component
public class PedidoDtoManager extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {
	

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;

	public PedidoDtoManager() {
		super(PedidoController.class, PedidoModel.class);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public PedidoModel toModel(Pedido pedido) {
		PedidoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
		modelMapper.map(pedido, pedidoModel);
		if(pedido.podeSerConfirmado() ) {
			pedidoModel.add(algaLinks.linkToConfirmacaoPedido(pedido.getCodigo(), "confirmar"));
		}
		if(pedido.podeSerEntregue()) {
			pedidoModel.add(algaLinks.linkToEntregaPedido(pedido.getCodigo(), "entregar"));
		}
		if( pedido.podeSerCancelado() ) {
			pedidoModel.add(algaLinks.linkToCancelamentoPedido(pedido.getCodigo(), "cancelar"));	
		}
		pedidoModel.add(algaLinks.linkToPedidos("pedidos"));
		pedidoModel.getEnderecoEntrega().getCidade().add(algaLinks.linkToCidade(pedidoModel.getEnderecoEntrega().getCidade().getId()));
		pedidoModel.getFormaPagamento().add(algaLinks.linkToFormaPagamento(pedidoModel.getFormaPagamento().getId()));
		pedidoModel.getRestaurante().add(algaLinks.linkToRestaurante(pedidoModel.getRestaurante().getId()));
		pedidoModel.getCliente().add(algaLinks.linkToUsuario(pedidoModel.getCliente().getId()));
		pedidoModel.getItens().forEach(item -> {
			item.add(algaLinks.linkToProduto(pedidoModel.getRestaurante().getId(), item.getProdutoId(), "produto"));
		});
		return pedidoModel;
	}
	
	@Override
	public CollectionModel<PedidoModel> toCollectionModel(Iterable<? extends Pedido> entities) {
		// TODO Auto-generated method stub
		return super.toCollectionModel(entities).add(algaLinks.linkToPedidos());
	}
	
	public Pedido converterToDomainObject(PedidoInput pedidoInput) {
		return modelMapper.map(pedidoInput, Pedido.class);
	}

	public void copyToDomainObject(PedidoInput objectInput, Pedido object) {
		modelMapper.map(objectInput, object);
	}

}
