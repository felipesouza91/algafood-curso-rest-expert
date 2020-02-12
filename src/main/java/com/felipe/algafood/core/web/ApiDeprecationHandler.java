package com.felipe.algafood.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class ApiDeprecationHandler extends HandlerInterceptorAdapter{

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(request.getRequestURI().startsWith("/v1/")) {
			response.addHeader("X-AlgaFood-Deprecated", "Essa versão sera descontinuada em 90 dias");
		}
		return true;
	}
	
	
}
