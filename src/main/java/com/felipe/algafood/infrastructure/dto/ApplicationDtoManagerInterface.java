package com.felipe.algafood.infrastructure.dto;

import java.util.List;

/**
 * This Interface applai a default methods for DtoManagents
 * @param T Default model Class of appication
 * @param D Dto Model class of aplication
 * @param I InputDto Type Of apllicaticon
 * */
public interface ApplicationDtoManagerInterface<T, D, I> {

	public D conveterToDtoModel(T object) ;
	
	public List<D> toCollectionDtoModel( List<T> listDomainObject) ;

	public T converterToDomainObject(I objectInput) ;

	public void copyToDomainObject(I objectInput, T object) ;
}
