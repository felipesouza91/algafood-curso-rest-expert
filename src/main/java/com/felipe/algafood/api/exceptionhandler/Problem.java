package com.felipe.algafood.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel("Problema")
@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {
	
	@ApiModelProperty(example = "400")
	private Integer status;
	
	@ApiModelProperty(example = "https://algaworksapi.com/recurso-nao-encontrado")
	private String type;
	
	@ApiModelProperty(example = "Recurso não encontrado")
	private String title;
	
	@ApiModelProperty(example = "Não existe um cadastro de cozinha com o codigo XX")
	private String detail;
	
	@ApiModelProperty(example = "Não existe um cadastro de cozinha com o codigo XX")
	private String userMessage;
	
	@ApiModelProperty(example = "2019-12-10T18:30:02.70444Z")
	private OffsetDateTime timestamp;
	
	@ApiModelProperty("Lista objetos ou campos que geraram erros")
	private List<Field> objects;
	
	@ApiModel("Objeto Problema")
	@Getter
	@Builder
	public static class Field {
		@ApiModelProperty(example = "Preço")
		private String nome;
		@ApiModelProperty(example = "Preço é obrigatorio")
		private String userMessage;
	}

}
