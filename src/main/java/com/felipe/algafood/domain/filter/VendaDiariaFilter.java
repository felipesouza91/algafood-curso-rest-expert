package com.felipe.algafood.domain.filter;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaDiariaFilter {
	
	private Long restId;
	
	private LocalDate dataCriacaoInicio;
	
	private LocalDate dataCriacaoFim;

}
