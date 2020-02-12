package com.felipe.algafood.api.v1.dto.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.felipe.algafood.api.v1.AlgaLinks;
import com.felipe.algafood.api.v1.controller.PedidoController;
import com.felipe.algafood.api.v1.dto.model.resumo.PedidoResumoModel;
import com.felipe.algafood.domain.model.Pedido;


@Component
public class PedidoResumoDtoManager extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel>{

	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public PedidoResumoDtoManager() {
		super(PedidoController.class, PedidoResumoModel.class);
	}

	@Override
	public PedidoResumoModel toModel(Pedido entity) {
		PedidoResumoModel pedidoResumoModel = createModelWithId(entity.getCodigo(), entity);
		modelMapper.map(entity, pedidoResumoModel);
		pedidoResumoModel.getCliente().add(algaLinks.linkToUsuario(pedidoResumoModel.getCliente().getId()));
		pedidoResumoModel.getRestaurante().add(algaLinks.linkToRestaurante(pedidoResumoModel.getRestaurante().getId()));
		return pedidoResumoModel;
	}

}
