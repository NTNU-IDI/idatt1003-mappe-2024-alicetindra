package edu.ntnu.idi.idatt;

import java.time.LocalDate;

public class ingredient {

  private final String name;
  private double amount;
  private final String measureUnit;
  private final LocalDate expirationDate;
  private final double price;

  public ingredient(String name, double amount, String measureUnit, LocalDate expirationDate,
      double price) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Name is null or empty");
    }
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount must be greater than 0");
    }
    if (measureUnit == null || measureUnit.trim().isEmpty()) {
      throw new IllegalArgumentException("MeasureUnit is null or empty");
    }
    if (expirationDate == null || expirationDate.isBefore(LocalDate.now())) {
      throw new IllegalArgumentException("Expiration Date is null or in the past");
    }
    if (price < 0) {
      throw new IllegalArgumentException("Price cannot be negative");
    }
    this.name = name.toLowerCase();
    this.amount = amount;
    this.measureUnit = measureUnit.toLowerCase();
    this.expirationDate = expirationDate;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("Amount cannot be negative");
    }
    this.amount = amount;
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
        + expirationDate + ", " + price + " kr.}\n";
  }
}
