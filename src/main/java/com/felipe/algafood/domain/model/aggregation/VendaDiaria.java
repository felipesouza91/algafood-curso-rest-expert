package com.felipe.algafood.domain.model.aggregation;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VendaDiaria {
	
	private Date data;
	
	private Long totalVenda;
	
	private BigDecimal totalFaturado;

}
