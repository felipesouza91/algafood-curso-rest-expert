package com.felipe.algafood.infrastructure.service.email;


import com.felipe.algafood.domain.exception.EmailException;
import com.felipe.algafood.domain.service.EnvioEmailService.Mensagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Component
public class EmailTemplateProcessor {
  
  @Autowired
	private Configuration freemarkConfig;
	
	public String processarTemplate(Mensagem mensagem) {
		try {
			Template template = freemarkConfig.getTemplate(mensagem.getCorpo());
			 
			return FreeMarkerTemplateUtils.processTemplateIntoString(template, mensagem.getVariaveis());
		} catch (Exception e) {
			throw new  EmailException("Não foi possivel montar o template do e-mail", e);
		} 
	}
}
