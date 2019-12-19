package com.felipe.algafood.api.dto.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.felipe.algafood.api.dto.inputs.CidadeInput;
import com.felipe.algafood.api.dto.model.CidadeModel;
import com.felipe.algafood.domain.model.Cidade;
import com.felipe.algafood.infrastructure.dto.ApplicationDtoManagerInterface;

@Component
public class CidadeDtoManager implements ApplicationDtoManagerInterface<Cidade, CidadeModel, CidadeInput> {
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CidadeModel conveterToDtoModel(Cidade cidade) {
		return modelMapper.map(cidade, CidadeModel.class);
	}
	
	@Override
	public List<CidadeModel> toCollectionDtoModel( List<Cidade> listCidade) {
		return listCidade
				.stream()
				.map(cidade -> conveterToDtoModel(cidade)).collect(Collectors.toList());
	}
	
	@Override
	public Cidade converterToDomainObject(CidadeInput cidadeInput) {
		return modelMapper.map(cidadeInput, Cidade.class);
	}

	@Override
	public void copyToDomainObject(CidadeInput objectInput, Cidade object) {
		
	}

}
