package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a recipe including its, name, description, instructions, a list pf
 * ingredients and portions.
 *
 * <p>The class consists of a constructor to create a recipe, along with get methods for parameter
 * 'name' and 'ingredients'.</p>
 *
 * @author Tindra ????????????????????
 */
public class recipe {

  private final String name;
  private final String description;
  private final String howTo;
  private final List<recipeIngredient> ingredients;
  private final int portions;

  /**
   * Constructs a new recipe with a specified name, description, instructions, ingredients and
   * portions.
   *
   * @param name        is a string representing the recipe name.
   * @param description is a string representing the short description of the recipe.
   * @param howTo       is a string representing the instructions, the how-to, of the recipe.
   * @param ingredients is a list of ingredients that the recipe includes.
   * @param portions    is an integer representing the amount of portions of the recipe.
   * @throws IllegalArgumentException if the String 'name' is null or only whitespace, if the String
   *                                  'description' is null or only whitespace, if the String
   *                                  'hotTo' is null or only whitespace, the List is empty, or the
   *                                  portions are less than 1 or more than 50.
   */
  public recipe(String name, String description, String howTo, List<recipeIngredient> ingredients,
      int portions) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException(
          "The string for the parameter 'Name' was blank, try again.");
    }
    if (description == null || description.trim().isEmpty()) {
      throw new IllegalArgumentException(
          "The string for the parameter 'Description' was blank, try again.");
    }
    if (howTo == null || howTo.trim().isEmpty()) {
      throw new IllegalArgumentException(
          "The string for the parameter 'HowTo' was blank, try again.");
    }
    if (ingredients == null || ingredients.isEmpty()) {
      throw new IllegalArgumentException(
          "The List 'Ingredients' cannot be null or empty, try again.");
    }
    if (portions < 1 || portions > 50) {
      throw new IllegalArgumentException(
          "The integer 'Portions' must be between 1 and 50, try again.");
    }
    this.name = name;
    this.description = description;
    this.howTo = howTo;
    this.ingredients = new ArrayList<>(ingredients);
    this.portions = portions;
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
   * Returns a List of ingredients that the recipe includes. Each ingredient is an instance of the
   * {@link recipeIngredient} class.
   *
   * @return list of ingredients.
   */
  public List<recipeIngredient> getIngredients() {
    return ingredients;
  }

  /**
   * Prints a String representation of the recipe object with all its attribute information.
   *
   * @return a String that represent the recipe information.
   */
  @Override
  public String toString() {
    return "\nRecipe for " + name + " - " + portions + " portions" + "\nDescription: " +
        description + "\nIngredients:\n" + ingredients + "Instructions:\n" + howTo + "\n";
  }

}
