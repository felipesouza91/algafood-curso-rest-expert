package com.felipe.algafood.domain.service;

import com.felipe.algafood.domain.filter.VendaDiariaFilter;

public interface VendaReportService {

	public byte[] emitirVendasDiarias(VendaDiariaFilter filter, String timeOffset);
}
