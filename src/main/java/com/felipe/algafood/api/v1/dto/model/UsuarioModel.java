package com.felipe.algafood.api.v1.dto.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "usuarios")
public class UsuarioModel extends RepresentationModel<UsuarioModel> {
	
	@ApiModelProperty(value = "Id do Cliente", example = "1")
	private Long id;
	
	@ApiModelProperty(value = "Nome do Cliente", example = "Fulano da Silva")
	private String nome;
	
	@ApiModelProperty(value = "Email do Cliente", example = "fulano@email.com")
	private String email;
}
