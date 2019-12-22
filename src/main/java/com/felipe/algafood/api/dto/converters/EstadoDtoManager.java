package com.felipe.algafood.api.dto.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.felipe.algafood.api.dto.inputs.EstadoInput;
import com.felipe.algafood.api.dto.model.EstadoModel;
import com.felipe.algafood.domain.model.Estado;
import com.felipe.algafood.infrastructure.dto.ApplicationDtoManagerInterface;

@Component
public class EstadoDtoManager implements ApplicationDtoManagerInterface<Estado, EstadoModel, EstadoInput>{
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public EstadoModel conveterToDtoModel(Estado object) {
		return modelMapper.map(object, EstadoModel.class);
	}

	@Override
	public List<EstadoModel> toCollectionDtoModel(List<Estado> listDomainObject) {
		return listDomainObject
				.stream()
				.map(estado -> conveterToDtoModel(estado))
					.collect(Collectors.toList());
	}

	@Override
	public Estado converterToDomainObject(EstadoInput objectInput) {
		return modelMapper.map(objectInput, Estado.class);
	}

	@Override
	public void copyToDomainObject(EstadoInput objectInput, Estado object) {
		modelMapper.map(objectInput, object);
		
	}
}
