package edu.ntnu.idi.idatt;

/**
 * This record represents an ingredient in a recipe, including its name, amount and unit of
 * measurement.
 *
 * <p>This record automatically provides getters, a constructor,
 * and standard implementations for equals, hashCode, and toString methods. The constructor creates
 * a recipe ingredient and ensures the string parameters are non null and that the amount is grater
 * than 0.</p>
 *
 * @param name   is a String representing the name of the recipe ingredient.
 * @param amount is a double representing the recipe ingredient amount.
 * @param unit   is a String representing the unit of measurement that is used for the amount.
 * @author Tindra ?????????????????????
 */
public record recipeIngredient(String name, double amount, String unit) {

  /**
   * Constructs a new recipe ingredient with a specified name, amount and unit of measurement.
   *
   * @throws IllegalArgumentException if the String 'name' is null or only whitespace, the double
   *                                  'amount' is less or equal to 0, or the String 'measureUnit' is
   *                                  null or only whitespace.
   */
  public recipeIngredient {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException(
          "The string for the parameter 'Name' was blank, try again.");
    }
    if (amount <= 0) {
      throw new IllegalArgumentException(
          "The double for the parameter 'Amount' must be greater than 0, try again.");
    }
    if (unit == null || unit.trim().isEmpty()) {
      throw new IllegalArgumentException(
          "The string for the parameter 'unit' was blank, try again.");
    }
  }

  /**
   * Prints a String representation of the recipe ingredient object with all its attribute
   * information.
   *
   * @return a String that represent the recipe ingredient information.
   */
  @Override
  public String toString() {
    return amount + " " + unit + " " + name + "\n";
  }

}
