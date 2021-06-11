package com.felipe.algafood.api;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hostcheck")
public class HostCheckController {
  
  @GetMapping
  public String checkHost() throws UnknownHostException {
    
    return InetAddress.getLocalHost().getHostAddress()
      + "-" + InetAddress.getLocalHost().getHostAddress();
  }
}
