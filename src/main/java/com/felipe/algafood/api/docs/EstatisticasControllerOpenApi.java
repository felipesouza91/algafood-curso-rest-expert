package com.felipe.algafood.api.docs;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.felipe.algafood.domain.filter.VendaDiariaFilter;
import com.felipe.algafood.domain.model.aggregation.VendaDiaria;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Estatisticas")
public interface EstatisticasControllerOpenApi {

	@ApiOperation(value = "Buscar estatisticas", produces = "application/json, application/pdf")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "restId", value = "Codigo de um restaurante", dataType  = "int", example = "1"),
		@ApiImplicitParam(name = "dataCriacaoInicio", value = "Data de inicio do filtro", dataType = "date-time", example = "2019-12-01T00:00:00Z"),
		@ApiImplicitParam(name = "dataCriacaoFim", value = "Data do termino do filtro", dataType = "date-time", example = "2019-12-01T00:00:00Z"),
	})
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro,
			@ApiParam(value = "Deslocamento de horário a ser considerado na consulta em relação ao UTC", defaultValue = "+00:00") String timeOffset) ;
	
	@ApiOperation(value = "Buscar estatisticas", hidden = true)
	public ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filtro,
			@ApiParam(value = "Time offset da data", defaultValue =  "+00:00") String timeOffset) ;
}