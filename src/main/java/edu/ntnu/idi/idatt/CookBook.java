package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.List;

/**
 * The cook book class manages a collection of recipes, allowing for operations such as registration
 * and retrieval of recipes.
 *
 * <p>The class provides methods for registering a recipe, retrieve all stored recipes, or find a
 * specific recipe by its name. It ensures recipe names are unique within the collection..</p>
 *
 * @author Tindra
 * @version 3.0
 * @since 1.3
 */
public class CookBook {

  private final List<Recipe> cookBook = new ArrayList<>();

  /**
   * Register recipe in cookbook.
   *
   * @param recipe is the recipe to be registered.
   * @throws IllegalArgumentException if recipe name already exist in cookBook array.
   */
  public void registerRecipe(Recipe recipe) {
    for (Recipe r : cookBook) {
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
  public List<Recipe> getRecipes() {
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
  public Recipe findRecipeByName(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Recipe name is null or empty.");
    }
    for (Recipe recipe : cookBook) {
      if (recipe.getName().equalsIgnoreCase(name)) {
        return recipe;
      }
    }
    return null;
  }

}
