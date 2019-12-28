package com.felipe.algafood.api.dto.converters;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.felipe.algafood.api.dto.inputs.PermissaoInput;
import com.felipe.algafood.api.dto.model.PermissaoModel;
import com.felipe.algafood.domain.model.Permissao;
import com.felipe.algafood.infrastructure.dto.ApplicationDtoManagerInterface;

@Component
public class PermissaoDtoManager implements ApplicationDtoManagerInterface<Permissao, PermissaoModel, PermissaoInput> {
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PermissaoModel conveterToDtoModel(Permissao permissao) {
		return modelMapper.map(permissao, PermissaoModel.class);
	}
	
	@Override
	public List<PermissaoModel> toCollectionDtoModel( Collection<Permissao> listPermissao) {
		return listPermissao
				.stream()
				.map(permissao -> conveterToDtoModel(permissao)).collect(Collectors.toList());
	}
	
	@Override
	public Permissao converterToDomainObject(PermissaoInput permissaoInput) {
		return modelMapper.map(permissaoInput, Permissao.class);
	}

	@Override
	public void copyToDomainObject(PermissaoInput objectInput, Permissao object) {
		modelMapper.map(objectInput, object);
	}

}
