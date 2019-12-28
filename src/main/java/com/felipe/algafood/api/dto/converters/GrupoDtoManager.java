package com.felipe.algafood.api.dto.converters;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.felipe.algafood.api.dto.inputs.GrupoInput;
import com.felipe.algafood.api.dto.model.GrupoModel;
import com.felipe.algafood.domain.model.Grupo;
import com.felipe.algafood.infrastructure.dto.ApplicationDtoManagerInterface;

@Component
public class GrupoDtoManager implements ApplicationDtoManagerInterface<Grupo, GrupoModel, GrupoInput> {
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public GrupoModel conveterToDtoModel(Grupo grupo) {
		return modelMapper.map(grupo, GrupoModel.class);
	}
	
	@Override
	public List<GrupoModel> toCollectionDtoModel( Collection<Grupo> grupoList) {
		return grupoList
				.stream()
				.map(grupo -> conveterToDtoModel(grupo))
					.collect(Collectors.toList());
	}
	
	@Override
	public Grupo converterToDomainObject(GrupoInput grupoInput) {
		return modelMapper.map(grupoInput, Grupo.class);
	}

	@Override
	public void copyToDomainObject(GrupoInput objectInput, Grupo object) {
		modelMapper.map(objectInput, object);
	}

}
