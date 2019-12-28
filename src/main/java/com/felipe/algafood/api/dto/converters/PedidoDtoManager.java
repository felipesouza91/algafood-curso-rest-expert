package com.felipe.algafood.api.dto.converters;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.felipe.algafood.api.dto.inputs.PedidoInput;
import com.felipe.algafood.api.dto.model.PedidoModel;
import com.felipe.algafood.api.dto.model.resumo.PedidoResumoModel;
import com.felipe.algafood.domain.model.Pedido;
import com.felipe.algafood.infrastructure.dto.ApplicationDtoManagerInterface;

@Component
public class PedidoDtoManager implements ApplicationDtoManagerInterface<Pedido, PedidoModel, PedidoInput> {
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PedidoModel conveterToDtoModel(Pedido pedido) {
		return modelMapper.map(pedido, PedidoModel.class);
	}
	
	@Override
	public List<PedidoModel> toCollectionDtoModel( Collection<Pedido> listPedido) {
		return listPedido
				.stream()
				.map(pedido -> conveterToDtoModel(pedido)).collect(Collectors.toList());
	}
	
	public PedidoResumoModel conveterToDtoResumoModel(Pedido pedido) {
		return modelMapper.map(pedido, PedidoResumoModel.class);
	}
	
	public List<PedidoResumoModel> toCollectionDtoResumoModel( Collection<Pedido> listPedido) {
		return listPedido
				.stream()
				.map(pedido -> conveterToDtoResumoModel(pedido)).collect(Collectors.toList());
	}
	
	@Override
	public Pedido converterToDomainObject(PedidoInput pedidoInput) {
		return modelMapper.map(pedidoInput, Pedido.class);
	}

	@Override
	public void copyToDomainObject(PedidoInput objectInput, Pedido object) {
		modelMapper.map(objectInput, object);
	}

}
