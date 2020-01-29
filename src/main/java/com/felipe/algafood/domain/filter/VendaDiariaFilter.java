package com.felipe.algafood.domain.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaDiariaFilter {
	
	private Long restId;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDate dataCriacaoInicio;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDate dataCriacaoFim;

}
