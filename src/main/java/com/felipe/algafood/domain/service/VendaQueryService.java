package com.felipe.algafood.domain.service;

import java.util.List;

import com.felipe.algafood.domain.filter.VendaDiariaFilter;
import com.felipe.algafood.domain.model.aggregation.VendaDiaria;

public interface VendaQueryService {

	public List<VendaDiaria> consultarVendaDiaria(VendaDiariaFilter vendaDiariaFilter, String timeOffset);
}
