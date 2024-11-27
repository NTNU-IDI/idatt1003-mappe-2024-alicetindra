package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.List;

public class cookBook {

  private final List<recipe> recipes;

  public foodStorage foodStorage;

  public cookBook() {
    recipes = new ArrayList<>();
    foodStorage = new foodStorage();
  }

  public void addRecipe(recipe recipe) {
    recipes.add(recipe);
  }

  public List<recipe> getRecipes() {
    return recipes;
  }

  public recipe findRecipeByName(String name) {
    for (recipe recipe : recipes) {
      if (recipe.getName().equalsIgnoreCase(name)) {
        return recipe;
      }
    }
    return null;
  }

  //give suggestions on a recipe according to what you have in food storage
}
