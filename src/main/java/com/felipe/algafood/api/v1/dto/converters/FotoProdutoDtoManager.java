package com.felipe.algafood.api.v1.dto.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.felipe.algafood.api.v1.AlgaLinks;
import com.felipe.algafood.api.v1.controller.RestauranteProdutoFotoController;
import com.felipe.algafood.api.v1.dto.model.FotoProdutoModel;
import com.felipe.algafood.domain.model.FotoProduto;

@Component
public class FotoProdutoDtoManager extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoModel>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;

	public FotoProdutoDtoManager() {
		super(RestauranteProdutoFotoController.class, FotoProdutoModel.class);
	}
	
	@Override
	public FotoProdutoModel toModel(FotoProduto entity) {
		FotoProdutoModel model = modelMapper.map(entity, FotoProdutoModel.class);
		model.add(algaLinks.linkToProdutoFoto(entity.getRestauranteId(), entity.getId()));
		model.add(algaLinks.linkToProduto(entity.getRestauranteId(), entity.getId(), "produto"));
		return model;
	}
}
