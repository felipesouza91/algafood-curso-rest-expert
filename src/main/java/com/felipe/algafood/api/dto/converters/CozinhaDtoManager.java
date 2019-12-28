package com.felipe.algafood.api.dto.converters;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.felipe.algafood.api.dto.inputs.CozinhaInput;
import com.felipe.algafood.api.dto.model.CozinhaModel;
import com.felipe.algafood.domain.model.Cozinha;
import com.felipe.algafood.infrastructure.dto.ApplicationDtoManagerInterface;

@Component
public class CozinhaDtoManager implements ApplicationDtoManagerInterface<Cozinha, CozinhaModel, CozinhaInput> {
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CozinhaModel conveterToDtoModel(Cozinha object) {
		return modelMapper.map(object, CozinhaModel.class);
	}

	@Override
	public List<CozinhaModel> toCollectionDtoModel(Collection<Cozinha> listDomainObject) {
		return listDomainObject
				.stream()
				.map(cozinha -> conveterToDtoModel(cozinha)).collect(Collectors.toList());
	}

	@Override
	public Cozinha converterToDomainObject(CozinhaInput objectInput) {
		return modelMapper.map(objectInput, Cozinha.class);
	}

	@Override
	public void copyToDomainObject(CozinhaInput objectInput, Cozinha object) {
		modelMapper.map(objectInput, object);
	}
}
