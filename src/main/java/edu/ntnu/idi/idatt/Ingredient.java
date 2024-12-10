package edu.ntnu.idi.idatt;

import java.time.LocalDate;

/**
 * This class represents an ingredient with a name, amount, measurement unit, an expiration date and
 * a price.
 *
 * <p>The class contains a constructor to create an ingredient. It includes get methods for each
 * parameter and a set method for amount.</p>
 *
 * @author Tindra
 * @version 3.0
 * @since 0.1
 */
public class Ingredient {

  private final String name;
  private double amount;
  private final String measureUnit;
  private final LocalDate expirationDate;
  private final double price;

  /**
   * Constructs an instance of an ingredient with the specified name, amount, unit of measurement,
   * expiration date and price.
   *
   * @param name           is a String representing the name of the ingredient.
   * @param amount         is a double representing the amount of the ingredient.
   * @param measureUnit    is a String representing the unit of measurement used for the amount.
   * @param expirationDate is a LocalDate representing the expiration date of the ingredient.
   * @param price          Is a double representing the price of the ingredient in SEK, kr.
   * @throws IllegalArgumentException if the String 'name' is null or only whitespace, the double
   *                                  'amount' is less or equal to 0, the String 'measureUnit' is
   *                                  null or only whitespace, the LocalDate 'expirationDate' is
   *                                  null, or price is negative.
   */
  public Ingredient(String name, double amount, String measureUnit, LocalDate expirationDate,
      double price) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException(
          "The string for the parameter 'Name' was blank, empty or null. Try again.");
    }
    if (amount <= 0) {
      throw new IllegalArgumentException(
          "The double for the parameter 'Amount' must be greater than 0, try again.");
    }
    if (measureUnit == null || measureUnit.isBlank()) {
      throw new IllegalArgumentException(
          "The string for the parameter 'MeasureUnit' was blank, empty or null. Try again.");
    }
    if (expirationDate == null) {
      throw new IllegalArgumentException(
          "The LocalDate for the parameter 'Expiration Date' is null, try again.");
    }
    if (price < 0) {
      throw new IllegalArgumentException(
          "The double for the parameter 'Price' cannot be negative, try again.");
    }
    this.name = name.toLowerCase();
    this.amount = amount;
    this.measureUnit = measureUnit.toLowerCase();
    this.expirationDate = expirationDate;
    this.price = price;
  }


  /**
   * Returns a String representing the name of the ingredient.
   *
   * @return String with ingredient name.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns a double representing the amount of an ingredient
   *
   * @return double representing ingredient amount.
   */
  public double getAmount() {
    return amount;
  }

  /**
   * Sets a new ingredient amount as the ingredients is being used or added.
   *
   * @param amount is a double value grater or equal to 0.
   * @throws IllegalArgumentException if the double parameter is negative.
   */
  public void setAmount(double amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("The parameter 'amount' cannot be negative, try again.");
    }
    this.amount = amount;
  }

  /**
   * Returns LocalDate representing the expiration date of the ingredient.
   *
   * @return LocalDate representing expiration date.
   */
  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  /**
   * Returns a double representing the price of the ingredient in SEK kr.
   *
   * @return double representing price.
   */
  public double getPrice() {
    return price;
  }

  /**
   * Prints a String representation of the ingredient object with all its attribute information.
   *
   * @return a String that represent the ingredient information.
   */
  @Override
  public String toString() {
    return " grocery{ " + name + ", " + amount + " " + measureUnit + ", Expiration date: "
        + expirationDate + ", " + price + " kr.}\n";
  }
}
