import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idi.idatt.CookBook;
import edu.ntnu.idi.idatt.Recipe;
import edu.ntnu.idi.idatt.RecipeIngredient;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the class recipeIngredient.
 *
 * <p>The following is tested:</p>
 *
 * <p>Positive tests:</p>
 *
 * <ul>
 *   <li>Tests that it is possible to register a recipe with valid parameters to cookBook. </li>
 *   <li>Testing that it is possible to retrieve registered recipes from cookbook.</li>
 *   <li>Testing that it is possible to find recipes in cookbook by name.</li>
 * </ul>
 *
 * <p>Negative tests:</p>
 *
 * <ul>
 *
 *   <li>Testing that it is not possible to register a recipe with the same name as an already existing recipe. </li>
 *   <li>Testing that it is not possible to retrieve recipes if cookbook is empty.</li>
 *   <li>Testing that it is not possible to find a recipe by name if name is null, empty or blank.</li>
 *
 * </ul>
 */

public class CookBookTest {

  private CookBook book;
  private Recipe recipe1;
  private Recipe recipe2;

  /**
   * Sets up the test environment before each test.
   */
  @BeforeEach
  void setUp() {

    book = new CookBook();
    RecipeIngredient ingredient1 = new RecipeIngredient("milk", 0.2, "litres");
    RecipeIngredient ingredient2 = new RecipeIngredient("coco powder", 50.0, "g");

    List<RecipeIngredient> ingredientsCoco = List.of(ingredient1, ingredient2);
    List<RecipeIngredient> ingredientsMilk = List.of(ingredient1);

    recipe1 = new Recipe("chocolate milk", "easy drink", "stir everything", ingredientsCoco,
        1);
    recipe2 = new Recipe("just milk", "plain milk", "pour a glass", ingredientsMilk, 1);

  }


  /**
   * Test registering a recipe in cookbook where we provide valid parameters.
   *
   * <p>This is a <b>positive</b> test since we are testing the functionality that the class
   * recipe is expected to have/implement.</p>
   *
   * <p>Expected outcome: A recipe is registered, and when asking for size of the list, we
   * expect the number of recipes we have registered. And when asking for a recipe, we expect the
   * parameters returned to be the same as provided.
   * </p>
   */
  @Test
  void registerRecipeWithValidInput() {
    book.registerRecipe(recipe1);
    assertEquals(1, book.getRecipes().size());
    assertEquals(recipe1, book.getRecipes().getFirst());

    book.registerRecipe(recipe2);
    assertEquals(2, book.getRecipes().size());
    assertEquals(recipe2, book.getRecipes().get(1));

  }

  /**
   * Test retrieving a recipe in cookbook.
   *
   * <p>This is a <b>positive</b> test since we are testing the functionality that the class
   * recipe is expected to have/implement.</p>
   *
   * <p>Expected outcome: A recipe is registered, and put in an array list with the .getRecipes
   * method. And when asking if the list contains the recipes in the list, we expect the boolean to
   * be true.
   * </p>
   */
  @Test
  void testGetRecipes() {
    book.registerRecipe(recipe1);
    book.registerRecipe(recipe2);
    List<Recipe> recipes = book.getRecipes();
    assertEquals(2, recipes.size());
    assertTrue(recipes.contains(recipe1));
    assertTrue(recipes.contains(recipe2));
  }

  /**
   * Test retrieving a recipe in cookbook by name where we provide valid parameters .
   *
   * <p>This is a <b>positive</b> test since we are testing the functionality that the class
   * recipe is expected to have/implement.</p>
   *
   * <p>Expected outcome: A recipe is retrieved, we expect the correct recipe to be returned.
   * </p>
   */
  @Test
  void testFindRecipeByName() {
    book.registerRecipe(recipe1);
    book.registerRecipe(recipe2);
    assertEquals(recipe1, book.findRecipeByName("chocolate milk"));
    assertEquals(recipe2, book.findRecipeByName("just milk"));
  }

  //-------------------------------NEGATIVE TESTS-------------------------------------

  /**
   * Test registration of recipes in cookbook where recipe parameter has the name of an already
   * existing recipe.
   *
   * <p>Expected outcome: An instance should not be registered if name already exists,
   * and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  void registerRecipeWithInvalidInput() {
    book.registerRecipe(recipe1);
    book.registerRecipe(recipe2);

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> book.registerRecipe(recipe1));
    assertEquals("Recipe name already exists, try with another name.", exception.getMessage());
  }

  /**
   * Test retrieving recipes in cookbook where cookbook is empty.
   *
   * <p>Expected outcome: An instance should not be retrieved if cookbook is empty,
   * and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  void getRecipesEmpty() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> book.getRecipes());
    assertEquals("No recipes found.", exception.getMessage());

  }

  /**
   * Test retrieving a recipe in cookbook where the name provided has an invalid value null, is
   * empty or blank (a string of only white spaces).
   *
   * <p>Expected outcome: An instance should not be retrieved if the name is set to null, is empty
   * or blank, and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  void findRecipeByNameInvalidName() {
    book.registerRecipe(recipe1);
    book.registerRecipe(recipe2);

    assertThrows(IllegalArgumentException.class, () -> book.findRecipeByName(""));
    assertThrows(IllegalArgumentException.class, () -> book.findRecipeByName("  "));
    assertThrows(IllegalArgumentException.class, () -> book.findRecipeByName(null));


  }


}
