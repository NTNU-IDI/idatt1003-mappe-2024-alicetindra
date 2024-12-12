package edu.ntnu.idi.idatt;

import java.time.LocalDate;
import java.util.List;

/**
 * Default data class provides default data for ingredients and recipes. It includes methods to
 * retrieve a list of default ingredients and recipes.
 *
 * @author Tindra
 * @version 3.0
 * @since 2.0
 */
public class DefaultData {

  /**
   * Registers ingredients and returns a list of default ingredients with name, amount, unit of
   * measurement, expiration date, and price.
   *
   * @return a list of ingredients.
   */
  public static List<Ingredient> getDefaultIngredients() {
    return List.of(
        new Ingredient("milk", 2, "litres",
            LocalDate.of(2025, 1, 14), 35.50),
        new Ingredient("yoghurt", 1.5, "litres",
            LocalDate.of(2024, 12, 30), 28.90),
        new Ingredient("bread", 1, "pc",
            LocalDate.of(2025, 1, 15), 18.90),
        new Ingredient("frozen berries", 500, "grams",
            LocalDate.of(2025, 5, 10), 42.0),
        new Ingredient("honey", 250, "grams",
            LocalDate.of(2030, 1, 20), 89.90),
        new Ingredient("chia seeds", 500, "grams",
            LocalDate.of(2025, 5, 10), 80),
        new Ingredient("oats", 1000, "grams",
            LocalDate.of(2025, 6, 11), 25),
        new Ingredient("peanut butter", 400, "grams",
            LocalDate.of(2025, 8, 12), 65.6),
        new Ingredient("butter", 500, "grams",
            LocalDate.of(2025, 2, 12), 56.90),
        new Ingredient("egg", 12, "pc",
            LocalDate.of(2025, 2, 24), 46.0),
        new Ingredient("flour", 1000, "grams",
            LocalDate.of(2025, 3, 18), 38.9),
        new Ingredient("ground beef", 800, "grams",
            LocalDate.of(2025, 1, 10), 70.0),
        new Ingredient("salt", 500, "grams",
            LocalDate.of(2026, 10, 23), 60),
        new Ingredient("pepper", 500, "grams",
            LocalDate.of(2026, 12, 3), 45),
        new Ingredient("cream", 0.4, "litres",
            LocalDate.of(2025, 1, 15), 35.6));
  }

  /**
   * Creates and returns a list of default recipe with name, description, instructions, ingredients,
   * and servings.
   *
   * @return list of recipes.
   */
  public static List<Recipe> getDefaultRecipes() {
    return List.of(
        createOats(),
        createBananaPancake(),
        createEggs(),
        createBolognese(),
        createRedCurry(),
        createSpinachStew());
  }

  /**
   * Creates and returns a recipe for 'Overnight oats' with name, description, instructions,
   * ingredients and servings.
   *
   * @return recipe for overnight oats.
   */
  private static Recipe createOats() {
    List<RecipeIngredient> oats = List.of(new RecipeIngredient("oats", 35, "grams"),
        new RecipeIngredient("milk", 0.1, "litres"),
        new RecipeIngredient("yoghurt", 0.1, "litres"),
        new RecipeIngredient("honey", 20, "grams"),
        new RecipeIngredient("chia seeds", 15, "grams"),
        new RecipeIngredient("frozen berries", 40, "grams"),
        new RecipeIngredient("peanut butter", 30, "grams"));
    return new Recipe("Overnight oats", "A filing and refreshing breakfast.",
        """
            1. Mix honey, oats and chia seeds together.
            2. Add milk and yoghurt and mix well.
            3. Add frozen berries and leave mixture in fridge overnight.
            4. In the morning add peanut butter and enjoy:)""",
        oats, 1);
  }

  /**
   * Creates and returns a recipe for 'Banana pancake' with name, description, instructions,
   * ingredients and servings.
   *
   * @return recipe for banana pancake.
   */
  private static Recipe createBananaPancake() {
    List<RecipeIngredient> bananaPancakes = List.of(new RecipeIngredient("banana", 1, "pc"),
        new RecipeIngredient("egg", 2, "pc"),
        new RecipeIngredient("peanut butter", 30, "grams"),
        new RecipeIngredient("butter", 10, "grams"));
    return new Recipe("Banana pancake", "Easy and quick breakfast",
        """
            1. Mash banana and mix with the eggs.
            2. Heat butter on middle heat in a frying pan and add mixture.
            3. Flip pancake after 5-10 minutes.
            4. Enjoy with peanut butter:)""", bananaPancakes, 1);
  }

  /**
   * Creates and returns a recipe for 'Scrambles eggs' with name, description, instructions,
   * ingredients and servings.
   *
   * @return recipe for scrambles eggs.
   */
  private static Recipe createEggs() {
    List<RecipeIngredient> scrambledEggs = List.of(new RecipeIngredient("egg", 3, "pc"),
        new RecipeIngredient("cream", 0.1, "litres"),
        new RecipeIngredient("salt", 5, "grams"),
        new RecipeIngredient("pepper", 5, "grams"),
        new RecipeIngredient("butter", 10, "grams"));
    return new Recipe("Scrambled eggs",
        "High protein breakfast on its own or on a piece of toast.",
        """
            1. Mix all the ingredients well.
            2. Turn frying pan on low to medium heat.
            3. Pour in egg mixture and stir until the consistency is close to what you want.
            4. Take pan off the heat and continue stirring.""", scrambledEggs, 2);
  }

  /**
   * Creates and returns a recipe for 'Red curry chicken' with name, description, instructions,
   * ingredients and servings.
   *
   * @return recipe for red curry chicken.
   */
  private static Recipe createRedCurry() {
    List<RecipeIngredient> redCurryChicken = List.of(new RecipeIngredient("chicken", 600, "grams"),
        new RecipeIngredient("yellow onion", 1, "pc"),
        new RecipeIngredient("garlic", 3, "pc"),
        new RecipeIngredient("bell pepper", 0.5, "pc"),
        new RecipeIngredient("cooking oil", 10, "grams"),
        new RecipeIngredient("coconut milk", 0.4, "litres"),
        new RecipeIngredient("red curry paste", 50, "grams"),
        new RecipeIngredient("water", 0.2, "litres"),
        new RecipeIngredient("rice", 340, "grams"));
    return new Recipe("Red curry chicken", "A classic thai curry served with rice",
        """
            1. Cut the chicken in small pieces.
            2. Finely chop onion and garlic, and shred the bell pepper.
            3. Fry the chicken in cooking oil.
            4. Fry the garlic with the red curry paste,onion, bell pepper, coconut milk and water.
            5. Add the chicken and let it simmer, 5-10 minutes.
            6. Cook the rice and serve.""", redCurryChicken, 4);
  }

  /**
   * Creates and returns a recipe for 'Bolognese' with name, description, instructions, ingredients
   * and servings.
   *
   * @return recipe for bolognese.
   */
  private static Recipe createBolognese() {
    List<RecipeIngredient> bolognese = List.of(new RecipeIngredient("minced meat", 500, "grams"),
        new RecipeIngredient("tomato sauce", 0.5, "litres"),
        new RecipeIngredient("yellow onion", 1, "pc"),
        new RecipeIngredient("bell pepper", 0.5, "pc"),
        new RecipeIngredient("carrot", 1, "pc"),
        new RecipeIngredient("garlic", 2, "pc"),
        new RecipeIngredient("pasta", 340, "grams"),
        new RecipeIngredient("butter", 10, "grams"),
        new RecipeIngredient("salt", 10, "grams"),
        new RecipeIngredient("pepper", 10, "grams"));
    return new Recipe("Pasta bolognese",
        "Hearty and comforting, meaty and easy bolognese sauce recipe",
        """
            1. Chop the onion, mince garlic and grate the carrot.
            2. Brown the minced meat in butter and minced garlic.
            3. Add the vegetables and tomato sauce.
            4. Let simmer, the longer it simmers the more taste will develop.
            5. Cook pasta according to package""", bolognese, 4);
  }

  /**
   * Creates and returns a recipe for 'Spinach stew' with name, description, instructions,
   * ingredients and servings.
   *
   * @return recipe for spinach stew.
   */
  private static Recipe createSpinachStew() {
    List<RecipeIngredient> spinachStew = List.of(new RecipeIngredient("spinach", 1000, "grams"),
        new RecipeIngredient("minced meat", 500, "grams"),
        new RecipeIngredient("yellow onion", 1, "pc"),
        new RecipeIngredient("garlic", 4, "pc"),
        new RecipeIngredient("bullion cube", 2, "pc"),
        new RecipeIngredient("lemon", 1, "pc"),
        new RecipeIngredient("salt", 10, "grams"),
        new RecipeIngredient("pepper", 10, "grams"),
        new RecipeIngredient("rice", 340, "grams"));
    return new Recipe("Lebanese Spinach stew",
        "Sabenegh wo roz, spinach and rice, is an easy-to-make, fresh Lebanese spinach stew.",
        """
            1. Chop the spinach, onion and mince the garlic.
            2. Heat oil in a big pot and fry the onion until soft.
            3. Add the meat until colored, add garlic and the spinach and stir while frying.
            4. Crumble the bullion cubes and add salt and pepper to taste.
            5. Bring to a boil.
            6. Lower heat and let simmer for 20-30 minutes.
            7. Cook rice according to package instructions.
            8. As a final touch press lemon juice to taste and enjoy.""", spinachStew, 4);
  }

}
