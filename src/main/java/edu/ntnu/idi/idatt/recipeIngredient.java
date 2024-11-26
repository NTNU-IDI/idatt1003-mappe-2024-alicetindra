package edu.ntnu.idi.idatt;

public record recipeIngredient(String name, double amount, String unit) {

  public recipeIngredient {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Name is null or empty");
    }
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount must be greater than 0");
    }
    if (unit == null || unit.trim().isEmpty()) {
      throw new IllegalArgumentException("Unit is null or empty");
    }
  }

  @Override
  public String toString() {
    return +amount + " " + unit + " " + name + "\n";
  }

}
