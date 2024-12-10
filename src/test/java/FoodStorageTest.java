import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idi.idatt.FoodStorage;
import edu.ntnu.idi.idatt.Ingredient;
import edu.ntnu.idi.idatt.Recipe;
import edu.ntnu.idi.idatt.RecipeIngredient;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the class foodStorage.
 *
 * <p>The following is tested:</p>
 *
 * <p>Positive tests:</p>
 *
 * <ul>
 *   <li>Testing that it is possible for an ingredient to register to the foodStorage. </li>
 *   <li>Testing that the list of ingredients can be sorted in alphabetical order.</li>
 *   <li>Testing that it is possible to calculate the price for all expired ingredients.</li>
 *   <li>Testing that it is possible to calculate the price for all ingredients.</li>
 * </ul>
 *
 * <p>Negative tests:</p>
 *
 * <ul>
 *   <li>Testing that it is not possible to retrieve ingredient with a name that is empty, blank,
 *   or has the value 'null'.</li>
 *   <li>Testing that it is not possible to remove an ingredient with a name that is empty, blank,
 *   or has the value 'null', the ingredient doesn't exist or has a value less than the remove
 *   amount, or the removal amount is negative or 0.</li>
 *   <li> Testing that is it not possible to collect ingredients with a certain expiration date if
 *   the date parameter is null.</li>
 *   <li>Testing that it is not possible to check recipe if the recipe parameter is null.</li>
 * </ul>
 */
public class FoodStorageTest {


  private FoodStorage storage;
  private Ingredient ingredient1;
  private Ingredient ingredient2;
  private Ingredient ingredient3;

  /**
   * Sets up the test environment before each test.
   */
  @BeforeEach
  void setUp() {
    storage = new FoodStorage();
    ingredient1 = new Ingredient("milk", 1.0, "litres",
        LocalDate.of(2024, 12, 15), 20.0);
    ingredient2 = new Ingredient("milk", 2.0, "litres",
        LocalDate.of(2024, 12, 15), 40.0);
    ingredient3 = new Ingredient("sugar", 1000.0, "g",
        LocalDate.of(2024, 12, 10), 10.0);
  }

  //-------------------------------POSITIVE TESTS-------------------------------------

  /**
   * Test registering an ingredient in foodStorage where we provide valid parameters.
   *
   * <p>This is a <b>positive</b> test since we are testing the functionality that the class
   * recipe is expected to have/implement.</p>
   *
   * <p>Expected outcome: An ingredient is registered, and when asking for size of the list, we
   * expect the number of ingredients we have registered. And when asking for the name of the
   * ingredient, we expect the parameters returned to be the same as provided.
   * </p>
   */
  @Test
  void registerIngredientWithValidInput() {
    storage.registerIngredient(ingredient1);
    assertEquals(1, storage.sortedList().size());
    assertEquals(ingredient1, storage.getIngredient("milk").getFirst());

    storage.registerIngredient(ingredient2);
    assertEquals(1, storage.sortedList().size());
    assertEquals(3, storage.getIngredient("milk").getFirst().getAmount());

    storage.registerIngredient(ingredient3);
    assertEquals(2, storage.sortedList().size());
    assertEquals(ingredient3, storage.getIngredient("sugar").getFirst());
  }

  /**
   * Test sorting list of ingredients in foodStorage in alphabetical order.
   *
   * <p>This is a <b>positive</b> test since we are testing the functionality that the class
   * recipe is expected to have/implement.</p>
   *
   * <p>Expected outcome: 2 ingredients are registered and when asking for the first element in the
   * list we expect the ingredient with a name that is the first in order. In this case milk in
   * first place and sugar in second.
   * </p>
   */
  @Test
  void sortedListAlphabetically() {
    storage.registerIngredient(ingredient1);
    storage.registerIngredient(ingredient3);
    List<Ingredient> sortedList = storage.sortedList();

    assertEquals(2, sortedList.size());
    assertEquals("milk", sortedList.get(0).getName());
    assertEquals("sugar", sortedList.get(1).getName());
  }

  /**
   * Test calculating total price for expired ingredients in foodStorage
   *
   * <p>This is a <b>positive</b> test since we are testing the functionality that the class
   * recipe is expected to have/implement.</p>
   *
   * <p>Expected outcome: We set an expiration date that will only expire onw ingredient, we then
   * expect the totalPrice to match the price of the expired ingredient. Later text includes both
   * ingredients and we expect the totalPrice to match the sum of the two ingredients.
   * </p>
   */
  @Test
  void totalPriceExpiration() {
    storage.registerIngredient(ingredient1);
    storage.registerIngredient(ingredient3);

    double totalPrice = storage.totalPriceExpiration(LocalDate.of(2024, 12, 12));
    assertEquals(10.0, totalPrice);

    totalPrice = storage.totalPriceExpiration(LocalDate.of(2024, 12, 16));
    assertEquals(30.0, totalPrice);
  }

  /**
   * Test calculating total price for ingredients in foodStorage
   *
   * <p>This is a <b>positive</b> test since we are testing the functionality that the class
   * recipe is expected to have/implement.</p>
   *
   * <p>Expected outcome: We expect the totalPrice to match the sum of the two ingredients.
   * </p>
   */
  @Test
  void totalPrice() {
    storage.registerIngredient(ingredient1);
    storage.registerIngredient(ingredient3);

    double totalPrice = storage.totalPrice();
    assertEquals(30.0, totalPrice);
  }

  //-------------------------------NEGATIVE TESTS-------------------------------------

  /**
   * Test retrieving an ingredient in foodStorage where the name provided has an invalid value null,
   * is empty or blank (a string of only white spaces).
   *
   * <p>Expected outcome: An instance should not be retrieved if the name is set to null, is empty
   * or blank, and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  void getIngredientWithInvalidInput() {
    storage.registerIngredient(ingredient1);
    List<Ingredient> result = storage.getIngredient("milk");
    assertEquals(1, result.size());
    assertEquals(ingredient1, result.getFirst());

    assertThrows(IllegalArgumentException.class, () -> storage.getIngredient(null));
    assertThrows(IllegalArgumentException.class, () -> storage.getIngredient(""));
    assertThrows(IllegalArgumentException.class, () -> storage.getIngredient("  "));
  }

  /**
   * Test removing an ingredient in foodStorage where the name provided has an invalid value null,
   * is empty or blank (a string of only white spaces). Or the ingredient doesn't exist or has a
   * less value than the remove amount. Or the removal amount is negative or 0.
   *
   * <p>Expected outcome: An instance should not be retrieved if the name is set to null, is empty
   * or blank, the ingredient doesn't exist or has a less
   * * value than the remove amount, or the removal amount is negative or 0 and an
   * IllegalArgumentException should be thrown.</p>
   */
  @Test
  void removeIngredientWithInvalidInput() {
    storage.registerIngredient(ingredient1);
    storage.registerIngredient(ingredient2);
    storage.registerIngredient(ingredient3);

    storage.removeIngredient("milk", 1.0);
    assertEquals(2.0, storage.getIngredient("milk").getFirst().getAmount());

    storage.removeIngredient("milk", 2.0);
    assertTrue(storage.getIngredient("milk").isEmpty());

    assertThrows(IllegalArgumentException.class, () -> storage.removeIngredient("milk", 1.0));
    assertThrows(IllegalArgumentException.class, () -> storage.removeIngredient("", 1.0));
    assertThrows(IllegalArgumentException.class, () -> storage.removeIngredient("   ", 1.0));
    assertThrows(IllegalArgumentException.class, () -> storage.removeIngredient(null, 1.0));
    assertThrows(IllegalArgumentException.class, () -> storage.removeIngredient("sugar", -1.0));
    assertThrows(IllegalArgumentException.class, () -> storage.removeIngredient("sugar", 0.0));
  }

  /**
   * Test collecting ingredients in foodStorage with an expiration date before the given parameter
   * with a value null.
   *
   * <p>Expected outcome: Ingredients should not be collected if the expiration date parameter is
   * set to null, and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  void expireBeforeWithNullDate() {
    storage.registerIngredient(ingredient1);
    storage.registerIngredient(ingredient3);

    List<Ingredient> expiringSoon = storage.expireBefore(LocalDate.of(2024, 12, 12));
    assertEquals(1, expiringSoon.size());
    assertEquals(ingredient3, expiringSoon.getFirst());

    assertThrows(IllegalArgumentException.class, () -> storage.expireBefore(null));
  }

  /**
   * Test checking if foodStorage has enough ingredients for a certain recipe with the recipe
   * parameter being null.
   *
   * <p>Expected outcome: Method should not be able to check the ingredients for a recipe when
   * parameter recipe is null, and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  void testCheckRecipe() {
    Ingredient Ing = new Ingredient("tomato", 200.0, "kg",
        LocalDate.of(2024, 12, 15), 20.0);

    RecipeIngredient recipeIng2 = new RecipeIngredient("tomato", 200.0, "g");
    RecipeIngredient recipeIng3 = new RecipeIngredient("carrot", 100.0, "g");

    List<RecipeIngredient> ingredients = List.of(recipeIng2, recipeIng3);
    Recipe recipe = new Recipe("Salad", "A simple salad", "Mix all ingredients",
        ingredients, 2);

    storage.registerIngredient(Ing);
    storage.registerIngredient(ingredient3);

    assertThrows(IllegalArgumentException.class, () -> storage.checkRecipe(null));
    assertDoesNotThrow(() -> storage.checkRecipe(recipe));
  }

}
