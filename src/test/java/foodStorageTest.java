import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idi.idatt.foodStorage;
import edu.ntnu.idi.idatt.ingredient;
import edu.ntnu.idi.idatt.recipe;
import edu.ntnu.idi.idatt.recipeIngredient;
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
 * </ul>
 *
 * <p>Negative tests:</p>
 *
 * <ul>
 *   <li></li>
 *
 * </ul>
 */
public class foodStorageTest {


  private foodStorage storage;
  private ingredient ingredient1;
  private ingredient ingredient2;
  private ingredient ingredient3;

  /**
   *
   */
  @BeforeEach
  void setUp() {
    storage = new foodStorage();
    ingredient1 = new ingredient("milk", 1.0, "litres",
        LocalDate.of(2024, 12, 15), 20.0);
    ingredient2 = new ingredient("milk", 2.0, "litres",
        LocalDate.of(2024, 12, 15), 40.0);
    ingredient3 = new ingredient("sugar", 1000.0, "g",
        LocalDate.of(2024, 12, 10), 10.0);
  }

  //-------------------------------POSITIVE TESTS-------------------------------------

  /**
   *
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

  @Test
  void sortedListAlphabetically() {
    storage.registerIngredient(ingredient1);
    storage.registerIngredient(ingredient3);
    List<ingredient> sortedList = storage.sortedList();

    assertEquals(2, sortedList.size());
    assertEquals("milk", sortedList.get(0).getName());
    assertEquals("sugar", sortedList.get(1).getName());
  }

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
   *
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
   *
   */
  @Test
  void getIngredientWithInvalidInput() {
    storage.registerIngredient(ingredient1);
    List<ingredient> result = storage.getIngredient("milk");
    assertEquals(1, result.size());
    assertEquals(ingredient1, result.getFirst());

    assertThrows(IllegalArgumentException.class, () -> storage.getIngredient(null));
    assertThrows(IllegalArgumentException.class, () -> storage.getIngredient(""));
    assertThrows(IllegalArgumentException.class, () -> storage.getIngredient("  "));
  }

  /**
   *
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
   *
   */
  @Test
  void expireBeforeWithNullDate() {
    storage.registerIngredient(ingredient1);
    storage.registerIngredient(ingredient3);

    List<ingredient> expiringSoon = storage.expireBefore(LocalDate.of(2024, 12, 12));
    assertEquals(1, expiringSoon.size());
    assertEquals(ingredient3, expiringSoon.getFirst());

    assertThrows(IllegalArgumentException.class, () -> storage.expireBefore(null));
  }

  /**
   *
   */
  @Test
  void testCheckRecipe() {
    ingredient Ing = new ingredient("tomato", 200.0, "kg",
        LocalDate.of(2024, 12, 15), 20.0);

    recipeIngredient recipeIng2 = new recipeIngredient("tomato", 200.0, "g");
    recipeIngredient recipeIng3 = new recipeIngredient("carrot", 100.0, "g");

    List<recipeIngredient> ingredients = List.of(recipeIng2, recipeIng3);
    recipe recipe = new recipe("Salad", "A simple salad", "Mix all ingredients",
        ingredients, 2);

    storage.registerIngredient(Ing);
    storage.registerIngredient(ingredient3);

    assertThrows(IllegalArgumentException.class, () -> storage.checkRecipe(null));
    assertDoesNotThrow(() -> storage.checkRecipe(recipe));
  }

}
