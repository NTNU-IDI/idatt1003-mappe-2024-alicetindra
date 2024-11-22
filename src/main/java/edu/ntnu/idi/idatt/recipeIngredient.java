package edu.ntnu.idi.idatt;

public class recipeIngredient {

  private final String name;
  private final double amount;
  private final String unit;

  public recipeIngredient(String name, double amount, String unit) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Name is null or empty");
    }
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount must be greater than 0");
    }
    if (unit == null || unit.trim().isEmpty()) {
      throw new IllegalArgumentException("Unit is null or empty");
    }
    this.name = name;
    this.amount = amount;
    this.unit = unit;
  }

  public String getName() {
    return name;
  }

  public double getAmount() {
    return amount;
  }

  public String getUnit() {
    return unit;
  }

  @Override
  public String toString() {
    return +amount + " " + unit + " " + name + "\n";
  }

}
