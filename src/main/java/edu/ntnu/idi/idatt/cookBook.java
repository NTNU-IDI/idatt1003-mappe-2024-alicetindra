package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.List;

/**
 * The cook book class manages a collection of recipes, making operations such as registration, and
 * retrieving possible.
 *
 * <p>The class provides methods for registering a recipe to cook book, retrieving a recipe from
 * the
 * cook book and retrieving a recipe by name.</p>
 *
 * @author Tindra ??????????
 */
public class cookBook {

  private final List<recipe> cookBook = new ArrayList<>();

  /**
   * Register recipe in cook book.
   *
   * @param recipe is the recipe to be registered.
   * @throws IllegalArgumentException if recipe name already exist in cookBook array.
   */
  public void registerRecipe(recipe recipe) {
    for (recipe r : cookBook) {
      if (r.getName().equals(recipe.getName())) {
        throw new IllegalArgumentException("Recipe name already exists, try with another name.");
      }
    }
    cookBook.add(recipe);
  }

  /**
   * Retrieves a list of recipes in the cook book.
   *
   * @return a list representing all the recipes in cook book.
   */
  public List<recipe> getRecipes() {
    return cookBook;
  }

  /**
   * Retrieves recipes by name.
   *
   * @param name is a string representing the recipe name.
   * @return a recipe with the given name.
   */
  public recipe findRecipeByName(String name) {
    for (recipe recipe : cookBook) {
      if (recipe.getName().equalsIgnoreCase(name)) {
        return recipe;
      }
    }
    return null;
  }

}
