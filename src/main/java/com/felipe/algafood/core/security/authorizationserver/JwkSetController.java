package com.felipe.algafood.core.security.authorizationserver;

import java.util.Map;

import com.nimbusds.jose.jwk.JWKSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/.well-know/jwks.json")
public class JwkSetController {

  @Autowired
  private JWKSet jwkSet;
  
  @GetMapping
  public Map<String, Object> keys() {
		return this.jwkSet.toJSONObject();
	}
}
