package com.felipe.algafood.core.springfox.model;

import com.felipe.algafood.api.dto.model.CozinhaModel;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;


@ApiModel(value = "CozinhasModel")
@Getter
@Setter
public class CozinhasModelOpenApi extends PageModelOpenApi<CozinhaModel> {

}
