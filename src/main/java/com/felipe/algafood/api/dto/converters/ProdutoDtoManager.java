package com.felipe.algafood.api.dto.converters;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.felipe.algafood.api.dto.inputs.ProdutoInput;
import com.felipe.algafood.api.dto.model.ProdutoModel;
import com.felipe.algafood.domain.model.Produto;
import com.felipe.algafood.infrastructure.dto.ApplicationDtoManagerInterface;

@Component
public class ProdutoDtoManager implements ApplicationDtoManagerInterface<Produto, ProdutoModel, ProdutoInput> {
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProdutoModel conveterToDtoModel(Produto produto) {
		return modelMapper.map(produto, ProdutoModel.class);
	}
	
	@Override
	public List<ProdutoModel> toCollectionDtoModel( Collection<Produto> listProduto) {
		return listProduto
				.stream()
				.map(produto -> conveterToDtoModel(produto)).collect(Collectors.toList());
	}
	
	@Override
	public Produto converterToDomainObject(ProdutoInput produtoInput) {
		return modelMapper.map(produtoInput, Produto.class);
	}

	@Override
	public void copyToDomainObject(ProdutoInput objectInput, Produto object) {
		modelMapper.map(objectInput, object);
	}
}
