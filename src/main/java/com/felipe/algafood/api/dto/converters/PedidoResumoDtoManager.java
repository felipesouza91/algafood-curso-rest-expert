package com.felipe.algafood.api.dto.converters;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.felipe.algafood.api.controller.PedidoController;
import com.felipe.algafood.api.controller.RestauranteController;
import com.felipe.algafood.api.controller.UsuarioController;
import com.felipe.algafood.api.dto.model.resumo.PedidoResumoModel;
import com.felipe.algafood.domain.model.Pedido;


@Component
public class PedidoResumoDtoManager extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel>{

	
	@Autowired
	private ModelMapper modelMapper;
	
	public PedidoResumoDtoManager() {
		super(PedidoController.class, PedidoResumoModel.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public PedidoResumoModel toModel(Pedido entity) {
		PedidoResumoModel pedidoResumoModel = createModelWithId(entity.getCodigo(), entity);
		modelMapper.map(entity, pedidoResumoModel);
		pedidoResumoModel.getCliente().add(linkTo(
					methodOn(UsuarioController.class).listarPorCodigo(pedidoResumoModel.getCliente().getId())
				).withSelfRel());
		pedidoResumoModel.getRestaurante().add(linkTo(
				methodOn(RestauranteController.class).buscarPorId(pedidoResumoModel.getRestaurante().getId())
			).withSelfRel());
		return pedidoResumoModel;
	}

}
