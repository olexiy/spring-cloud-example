package de.olexiy.in28minutes.microservices.currencyconversionservice;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrecnyConversionController {

  @Autowired
  private CurrencyExchangeServiceProxy proxy;

  @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
  public CurrencyConversion calculateCurrencyConversionFeign(
      @PathVariable String from,
      @PathVariable String to,
      @PathVariable BigDecimal quantity){



    CurrencyConversion response = proxy.retriveExchangeValue(from, to);

    return new CurrencyConversion(response.getId(),
        from,
        to,
        response.getConversionMultiple(),
        quantity,
        quantity.multiply(response.getConversionMultiple()),
        response.getPort());

  }

}
