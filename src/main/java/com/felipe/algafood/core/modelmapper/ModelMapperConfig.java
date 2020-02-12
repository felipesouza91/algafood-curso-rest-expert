package com.felipe.algafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.felipe.algafood.api.v1.dto.inputs.ItemPedidoInput;
import com.felipe.algafood.api.v1.dto.model.EnderecoModel;
import com.felipe.algafood.api.v2.dto.inputs.CidadeInputV2;
import com.felipe.algafood.domain.model.Cidade;
import com.felipe.algafood.domain.model.Endereco;
import com.felipe.algafood.domain.model.ItemPedido;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		var enderecoModelTyperMap = modelMapper.createTypeMap(Endereco.class, EnderecoModel.class);
		enderecoModelTyperMap.addMapping(
				src -> src.getCidade().getEstado().getNome(),
				(dest, val) -> dest.getCidade().setEstado((String)val) );
		modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class).addMappings(mapper -> mapper.skip(ItemPedido::setId));
		modelMapper.createTypeMap(CidadeInputV2.class, Cidade.class).addMappings(mapper -> mapper.skip(Cidade::setId));
		return modelMapper;
	}
}
