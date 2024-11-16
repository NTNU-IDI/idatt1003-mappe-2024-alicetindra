package edu.ntnu.idi.idatt;

import java.time.LocalDate; //to be able to use best before date

public class ingredient {

  private final String name;//name of ingredient
  private double amount;//amount of ingredient, user will be able to change amount
  private final String measureUnit; //what the amount is measured in, liters, grams, stk
  private final LocalDate expirationDate; //to check the expiration date
  private final double price; //price per ingredient unit

  public ingredient(String name, double amount, String measureUnit, LocalDate expirationDate,
      double price) {
    this.name = name;
    this.amount = amount;
    this.measureUnit = measureUnit;
    this.expirationDate = expirationDate;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {// change amount of the ingredient
    this.amount = amount;
  }

  public String getMeasureUnit() {
    return measureUnit;
  }

  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  public double getPrice() {
    return price;
  }

  @Override
  public String toString() {
    return " grocery{ " + name + ", " + amount + " " + measureUnit + ", Expiration date: "
        + expirationDate + ", " + price + " kr.}";
  }
}
