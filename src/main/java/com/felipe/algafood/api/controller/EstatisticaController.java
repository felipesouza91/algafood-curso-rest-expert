package com.felipe.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.algafood.api.docs.EstatisticasControllerOpenApi;
import com.felipe.algafood.domain.filter.VendaDiariaFilter;
import com.felipe.algafood.domain.model.aggregation.VendaDiaria;
import com.felipe.algafood.domain.service.VendaQueryService;
import com.felipe.algafood.domain.service.VendaReportService;


@RestController
@RequestMapping(path = "/estatisticas",  produces = MediaType.APPLICATION_JSON_VALUE)
public class EstatisticaController implements EstatisticasControllerOpenApi {

	@Autowired
	private VendaQueryService queryService;
	
	@Autowired
	private VendaReportService vendaReportService;
	
	@Override
	@GetMapping(path = "/vendas-diarias")
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro,
			@RequestParam(defaultValue = "+00:00", required = false) String timeOffset) {
		
		return queryService.consultarVendaDiaria(filtro, timeOffset);
	}
	
	@Override
	@GetMapping(path =  "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filtro,
			@RequestParam(defaultValue = "+00:00", required = false) String timeOffset) {
		byte[] bytesPdf = vendaReportService.emitirVendasDiarias(filtro, timeOffset); 
		var headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diarias.pdf");
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).headers(headers).body(bytesPdf);
	}
}
