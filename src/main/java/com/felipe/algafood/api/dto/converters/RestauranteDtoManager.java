package com.felipe.algafood.api.dto.converters;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.felipe.algafood.api.dto.inputs.RestauranteInput;
import com.felipe.algafood.api.dto.model.RestauranteModel;
import com.felipe.algafood.domain.model.Cidade;
import com.felipe.algafood.domain.model.Cozinha;
import com.felipe.algafood.domain.model.Restaurante;
import com.felipe.algafood.infrastructure.dto.ApplicationDtoManagerInterface;

@Component
public class RestauranteDtoManager implements ApplicationDtoManagerInterface<Restaurante, RestauranteModel, RestauranteInput>{
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public RestauranteModel conveterToDtoModel(Restaurante object) {
		return modelMapper.map(object, RestauranteModel.class);
	}

	@Override
	public List<RestauranteModel> toCollectionDtoModel(Collection<Restaurante> listDomainObject) {
		return listDomainObject.stream().map(restaurante -> conveterToDtoModel(restaurante)).collect(Collectors.toList());
	}

	@Override
	public Restaurante converterToDomainObject(RestauranteInput objectInput) {
		return modelMapper.map(objectInput, Restaurante.class);
	}

	@Override
	public void copyToDomainObject(RestauranteInput objectInput, Restaurante object) {
		object.setCozinha(new Cozinha());
		if( object.getEndereco() != null ) {
			object.getEndereco().setCidade(new Cidade());	
		}
		modelMapper.map(objectInput, object);
	}
}
