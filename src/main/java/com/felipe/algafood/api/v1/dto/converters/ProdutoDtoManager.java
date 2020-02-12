package com.felipe.algafood.api.v1.dto.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.felipe.algafood.api.v1.AlgaLinks;
import com.felipe.algafood.api.v1.controller.RestauranteProdutoController;
import com.felipe.algafood.api.v1.dto.inputs.ProdutoInput;
import com.felipe.algafood.api.v1.dto.model.ProdutoModel;
import com.felipe.algafood.domain.model.Produto;

@Component
public class ProdutoDtoManager extends RepresentationModelAssemblerSupport<Produto, ProdutoModel>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public ProdutoDtoManager() {
		super(RestauranteProdutoController.class, ProdutoModel.class);
	}

	@Override
	public ProdutoModel toModel(Produto produto) {
		ProdutoModel model = createModelWithId(
				produto.getId(), produto, produto.getRestaurante().getId());
		modelMapper.map(produto, model);
		model.add(algaLinks.linkToProdutos(produto.getRestaurante().getId(), "produtos"));
		model.add(algaLinks.linkToProdutoFoto( produto.getRestaurante().getId(),produto.getId(), "foto"));
		return model;
	}
	
	@Override
	public CollectionModel<ProdutoModel> toCollectionModel(Iterable<? extends Produto> entities) {
		// TODO Auto-generated method stub
		return super.toCollectionModel(entities);
	}
	
	public Produto converterToDomainObject(ProdutoInput produtoInput) {
		return modelMapper.map(produtoInput, Produto.class);
	}

	public void copyToDomainObject(ProdutoInput objectInput, Produto object) {
		modelMapper.map(objectInput, object);
	}

}
