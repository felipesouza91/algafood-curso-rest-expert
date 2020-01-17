package com.felipe.algafood.infrastructure.utils;

import java.util.List;

import org.springframework.data.domain.PageImpl;

public class PageWrapper<T> extends PageImpl<T>{

	
	private static final long serialVersionUID = 1L;

	public PageWrapper(List<T> content) {
		super(content);
		// TODO Auto-generated constructor stub
	}

}
