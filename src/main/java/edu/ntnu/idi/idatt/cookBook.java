package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.List;

public class cookBook {

  private final List<recipe> recipes;

  public cookBook() {
    recipes = new ArrayList<>();
  }

  public void addRecipe(recipe recipe) {
    recipes.add(recipe);
  }

  public List<recipe> printRecipes() {
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

  //check if food storage has enough for a recipe, 1 pick a recipe,
  // 2 check all ingredients and amount in the recipe, check if there is enough in foodStorage,
  // if there is enough return true, if something is missing print out what you are missing

  //give suggestions on a recipe according to what you have in food storage
}
