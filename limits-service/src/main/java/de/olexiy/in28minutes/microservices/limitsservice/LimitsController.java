package de.olexiy.in28minutes.microservices.limitsservice;

import de.olexiy.in28minutes.microservices.limitsservice.bean.Limits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

  @Autowired
  private Configuration configuration;

  @GetMapping("/limits")
  public Limits retriveLimits(){
    Limits limits = new Limits(configuration.getMinimum(), configuration.getMaximum());
    return limits;
  }
}
