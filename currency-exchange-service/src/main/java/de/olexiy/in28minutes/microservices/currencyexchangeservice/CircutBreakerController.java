package de.olexiy.in28minutes.microservices.currencyexchangeservice;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircutBreakerController {

  private Logger logger = LoggerFactory.getLogger(CircutBreakerController.class);


  @GetMapping("/sample-api")
  //@Retry(name="sample-api", fallbackMethod = "hardcodedResponse")
  @CircuitBreaker(name="sample-api", fallbackMethod = "hardcodedResponse")
  //@RateLimiter(name="default")
  //10s => 1000 calls to the same api
  @Bulkhead(name="default")
  public String sampleApi(){
    logger.info("Sample api call started");
    ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:1111/some-rest-endpoint",
        String.class);
    return forEntity.getBody();
  }

  public String hardcodedResponse(Exception ex){
    return "fallback-responce";
  }
}
