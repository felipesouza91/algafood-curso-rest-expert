package com.felipe.algafood.infrastructure.service.report;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.felipe.algafood.domain.filter.VendaDiariaFilter;
import com.felipe.algafood.domain.service.VendaQueryService;
import com.felipe.algafood.domain.service.VendaReportService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Repository
public class PdfVendaReportService implements VendaReportService{
	
	@Autowired
	private VendaQueryService queryService;

	@Override
	public byte[] emitirVendasDiarias(VendaDiariaFilter filter, String timeOffset)  {
		try {
			var inputStream = this.getClass().getResourceAsStream("/relatorios/vendas-diarias.jasper");
			var parametros = new HashMap<String, Object>();
			var dataSource = new JRBeanCollectionDataSource(queryService.consultarVendaDiaria(filter, timeOffset));
			parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
			var jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (Exception e) {
			throw new ReportException("Não foi possivel emitir relato de vendas diarias",e);
		}
		
	}

}
