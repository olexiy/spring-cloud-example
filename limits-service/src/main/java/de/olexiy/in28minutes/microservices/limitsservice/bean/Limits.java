package de.olexiy.in28minutes.microservices.limitsservice.bean;
public class Limits {
  private int minimum;
  private int maximum;

  protected Limits() {
  }

  public Limits(int minimum, int maximum) {
    super();
    this.minimum = minimum;
    this.maximum = maximum;
  }

  public int getMaximum() {
    return maximum;
  }

  public int getMinimum() {
    return minimum;
  }

}