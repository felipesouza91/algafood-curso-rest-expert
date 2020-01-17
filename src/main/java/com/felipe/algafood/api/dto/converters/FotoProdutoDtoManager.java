package com.felipe.algafood.api.dto.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.felipe.algafood.api.dto.model.FotoProdutoModel;
import com.felipe.algafood.domain.model.FotoProduto;

@Component
public class FotoProdutoDtoManager {
	
	@Autowired
	private ModelMapper modelMapper;

	
	public FotoProdutoModel conveterToDtoModel(FotoProduto foto) {
		return modelMapper.map(foto, FotoProdutoModel.class);
	}
}
