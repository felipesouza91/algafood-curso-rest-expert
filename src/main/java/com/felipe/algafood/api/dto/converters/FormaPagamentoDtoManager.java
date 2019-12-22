package com.felipe.algafood.api.dto.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.felipe.algafood.api.dto.inputs.FormaPagamentoInput;
import com.felipe.algafood.api.dto.model.FormaPagamentoModel;
import com.felipe.algafood.domain.model.FormaPagamento;
import com.felipe.algafood.infrastructure.dto.ApplicationDtoManagerInterface;

@Component
public class FormaPagamentoDtoManager implements
						ApplicationDtoManagerInterface<FormaPagamento, FormaPagamentoModel, FormaPagamentoInput> {
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public FormaPagamentoModel conveterToDtoModel(FormaPagamento formaPagamento) {
		return modelMapper.map(formaPagamento, FormaPagamentoModel.class);
	}
	
	@Override
	public List<FormaPagamentoModel> toCollectionDtoModel( List<FormaPagamento> listFormaPagamentos) {
		return listFormaPagamentos
				.stream()
				.map(formaPagamento -> conveterToDtoModel(formaPagamento)).collect(Collectors.toList());
	}
	
	@Override
	public FormaPagamento converterToDomainObject(FormaPagamentoInput formaPagamentoInput) {
		return modelMapper.map(formaPagamentoInput, FormaPagamento.class);
	}

	@Override
	public void copyToDomainObject(FormaPagamentoInput objectInput, FormaPagamento object) {
		modelMapper.map(objectInput, object);
	}

}
