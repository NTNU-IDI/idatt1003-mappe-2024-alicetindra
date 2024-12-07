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
public class defaultData {

  /**
   * Registers ingredients and returns a list of default ingredients with name, amount, unit of
   * measurement, expiration date, and price.
   *
   * @return a list of ingredients.
   */
  public static List<ingredient> getDefaultIngredients() {
    return List.of(
        new ingredient("milk", 2, "litres",
            LocalDate.of(2025, 1, 14), 35.50),
        new ingredient("yoghurt", 1.5, "litres",
            LocalDate.of(2024, 12, 30), 28.90),
        new ingredient("bread", 1, "pc",
            LocalDate.of(2025, 1, 15), 18.90),
        new ingredient("frozen berries", 500, "grams",
            LocalDate.of(2025, 5, 10), 42.0),
        new ingredient("honey", 250, "grams",
            LocalDate.of(2030, 1, 20), 89.90),
        new ingredient("chia seeds", 500, "grams",
            LocalDate.of(2025, 5, 10), 80),
        new ingredient("oats", 1000, "grams",
            LocalDate.of(2025, 6, 11), 25),
        new ingredient("peanut butter", 400, "grams",
            LocalDate.of(2025, 8, 12), 65.6),
        new ingredient("butter", 500, "grams",
            LocalDate.of(2025, 2, 12), 56.90),
        new ingredient("egg", 12, "pc",
            LocalDate.of(2025, 2, 24), 46.0),
        new ingredient("flour", 1000, "grams",
            LocalDate.of(2025, 3, 18), 38.9),
        new ingredient("ground beef", 800, "grams",
            LocalDate.of(2025, 1, 10), 70.0),
        new ingredient("salt", 500, "grams",
            LocalDate.of(2026, 10, 23), 60),
        new ingredient("pepper", 500, "grams",
            LocalDate.of(2026, 12, 3), 45),
        new ingredient("cream", 0.4, "litres",
            LocalDate.of(2025, 1, 15), 35.6));
  }

  /**
   * Creates and returns a list of default recipe with name, description, instructions, ingredients,
   * and servings.
   *
   * @return list of recipes.
   */
  public static List<recipe> getDefaultRecipes() {
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
  private static recipe createOats() {
    List<recipeIngredient> oats = List.of(new recipeIngredient("oats", 35, "grams"),
        new recipeIngredient("milk", 0.1, "litres"),
        new recipeIngredient("yoghurt", 0.1, "litres"),
        new recipeIngredient("honey", 20, "grams"),
        new recipeIngredient("chia seeds", 15, "grams"),
        new recipeIngredient("frozen berries", 40, "grams"),
        new recipeIngredient("peanut butter", 30, "grams"));
    return new recipe("Overnight oats", "A filing and refreshing breakfast.",
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
  private static recipe createBananaPancake() {
    List<recipeIngredient> bananaPancakes = List.of(new recipeIngredient("banana", 1, "pc"),
        new recipeIngredient("egg", 2, "pc"),
        new recipeIngredient("peanut butter", 30, "grams"),
        new recipeIngredient("butter", 10, "grams"));
    return new recipe("Banana pancake", "Easy and quick breakfast",
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
  private static recipe createEggs() {
    List<recipeIngredient> scrambledEggs = List.of(new recipeIngredient("egg", 3, "pc"),
        new recipeIngredient("cream", 0.1, "litres"),
        new recipeIngredient("salt", 5, "grams"),
        new recipeIngredient("pepper", 5, "grams"),
        new recipeIngredient("butter", 10, "grams"));
    return new recipe("Scrambled eggs",
        "High protein breakfast on its own or on a piece of toast.",
        """
            1. Mix all the ingredients well with a fork or whisk.
            2. Turn frying pan on low to medium heat.
            3. Pour in egg mixture and stir with a wooden spoon until the consistency is close to what you want.
            4. Take pan off the heat and continue stirring.""", scrambledEggs, 2);
  }

  /**
   * Creates and returns a recipe for 'Red curry chicken' with name, description, instructions,
   * ingredients and servings.
   *
   * @return recipe for red curry chicken.
   */
  private static recipe createRedCurry() {
    List<recipeIngredient> redCurryChicken = List.of(new recipeIngredient("chicken", 600, "grams"),
        new recipeIngredient("yellow onion", 1, "pc"),
        new recipeIngredient("garlic", 3, "pc"),
        new recipeIngredient("bell pepper", 0.5, "pc"),
        new recipeIngredient("cooking oil", 10, "grams"),
        new recipeIngredient("coconut milk", 0.4, "litres"),
        new recipeIngredient("red curry paste", 50, "grams"),
        new recipeIngredient("water", 0.2, "litres"),
        new recipeIngredient("rice", 340, "grams"));
    return new recipe("Red curry chicken", "A classic thai curry served with rice",
        """
            1. Cut the chicken in small pieces.
            2. Peal and finely chop onion and garlic as well as shred the bell pepper
            3. Fry the chicken in cooking oil in a frying pan
            4. Fry the garlic with the red curry paste. Add onion, bell pepper, coconut milk and water
            5. Add the chicken and let it simmer, 5-10 minutes.
            6. Cook the rice according to the package and serve""", redCurryChicken, 4);
  }

  /**
   * Creates and returns a recipe for 'Bolognese' with name, description, instructions, ingredients
   * and servings.
   *
   * @return recipe for bolognese.
   */
  private static recipe createBolognese() {
    List<recipeIngredient> bolognese = List.of(new recipeIngredient("minced meat", 500, "grams"),
        new recipeIngredient("tomato sauce", 0.5, "litres"),
        new recipeIngredient("yellow onion", 1, "pc"),
        new recipeIngredient("bell pepper", 0.5, "pc"),
        new recipeIngredient("carrot", 1, "pc"),
        new recipeIngredient("garlic", 2, "pc"),
        new recipeIngredient("pasta", 340, "grams"),
        new recipeIngredient("butter", 10, "grams"),
        new recipeIngredient("salt", 10, "grams"),
        new recipeIngredient("pepper", 10, "grams"));
    return new recipe("Pasta bolognese",
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
  private static recipe createSpinachStew() {
    List<recipeIngredient> spinachStew = List.of(new recipeIngredient("spinach", 1000, "grams"),
        new recipeIngredient("minced meat", 500, "grams"),
        new recipeIngredient("yellow onion", 1, "pc"),
        new recipeIngredient("garlic", 4, "pc"),
        new recipeIngredient("bullion cube", 2, "pc"),
        new recipeIngredient("lemon", 1, "pc"),
        new recipeIngredient("salt", 10, "grams"),
        new recipeIngredient("pepper", 10, "grams"),
        new recipeIngredient("rice", 340, "grams"));
    return new recipe("Lebanese Spinach stew",
        "Sabenegh wo roz, which means spinach and rice, is an easy-to-make, fresh Lebanese spinach stew.",
        """
            1. Chop the spinach, onion and mince the garlic.
            2. Heat oil in a big pot and fry the onion until soft.
            3. Add the meat until colored, add garlic and the spinach and stir while frying.
            4. Crumble the bullion cubes and add salt and pepper to taste.
            5. If you have used frozen spinach bring to a boil, if you have used fresh spinach add a little water adn bring to a boil.
            6. Lower heat and let simmer for 20-30 minutes.
            7. Cook rice according to package instructions.
            8. As a final touch press lemon juice to taste and enjoy.""", spinachStew, 4);
  }

}
