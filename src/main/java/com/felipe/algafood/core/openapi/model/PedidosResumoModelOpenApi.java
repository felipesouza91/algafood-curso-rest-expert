package com.felipe.algafood.core.openapi.model;

import com.felipe.algafood.api.dto.model.PedidoModel;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;


@ApiModel(value = "PedidosResumoModel")
@Getter
@Setter
public class PedidosResumoModelOpenApi extends PageModelOpenApi<PedidoModel> {

}
