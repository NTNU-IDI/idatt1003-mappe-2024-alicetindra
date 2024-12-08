package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.List;

/**
 * The cook book class manages a collection of recipes, making operations such as registration, and
 * retrieving possible.
 *
 * <p>The class provides methods for registering a recipe to cook book, retrieving a recipe from
 * the cookbook and retrieving a recipe by name.</p>
 *
 * @author Tindra
 * @version 3.0
 * @since 1.3
 */
public class cookBook {

  private final List<recipe> cookBook = new ArrayList<>();

  /**
   * Register recipe in cookbook.
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
   * Retrieves a list of recipes in the cookbook.
   *
   * @return a list representing all the recipes in cook book.
   */
  public List<recipe> getRecipes() {
    if (cookBook.isEmpty()) {
      throw new IllegalArgumentException("No recipes found.");
    }
    return cookBook;
  }

  /**
   * Retrieves recipes by name.
   *
   * @param name is a string representing the recipe name.
   * @return a recipe with the given name.
   */
  public recipe findRecipeByName(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Recipe name is null or empty.");
    }
    for (recipe recipe : cookBook) {
      if (recipe.getName().equalsIgnoreCase(name)) {
        return recipe;
      }
    }
    return null;
  }

}
