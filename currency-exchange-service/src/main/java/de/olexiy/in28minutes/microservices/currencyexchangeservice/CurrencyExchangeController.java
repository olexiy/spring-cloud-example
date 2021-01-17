package de.olexiy.in28minutes.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

  private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

  @Autowired
  private Environment environment;

  @Autowired
  private CurrencyExchangeRepository repository;

  @GetMapping("/currency-exchange/from/{from}/to/{to}")
  public CurrencyExchange retriveExchangeValue(@PathVariable String from, @PathVariable String to){

    logger.info("retriveExchangeValue called with {} to {}", from, to);

    CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
    if(currencyExchange == null){
      throw new RuntimeException(("Unable to Find data for "+from+" to "+to));
    }
    currencyExchange.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
    return currencyExchange;
  }
}
