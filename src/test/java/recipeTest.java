import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.idi.idatt.recipe;
import edu.ntnu.idi.idatt.recipeIngredient;
import java.util.ArrayList;
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
 *   <li> </li>
 * </ul>
 *
 * <p>Negative tests:</p>
 *
 * <ul>
 *   <li></li>
 *
 * </ul>
 */
public class recipeTest {

  private List<recipeIngredient> ingredients;
  private List<recipeIngredient> emptyIngredients;

  @BeforeEach
  void setUp() {
    recipeIngredient ingredient1 = new recipeIngredient("tomato", 200.0, "g");
    recipeIngredient ingredient2 = new recipeIngredient("carrot", 100.0, "g");

    ingredients = List.of(ingredient1, ingredient2);
  }


  @Test
  void createInstanceWithValidParameters() {

    edu.ntnu.idi.idatt.recipe recipe = new recipe("salad", "easy salad",
        "chop and mix", ingredients, 2);

    assertEquals("salad", recipe.getName());
    assertEquals(ingredients, recipe.getIngredients());
  }


  @Test
  void createInstanceWithInvalidNullName() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipe(null, "easy salad", "chop and mix", ingredients, 2));
    assertEquals("The string for the parameter 'Name' was blank, try again.",
        exception.getMessage());
  }

  @Test
  void createInstanceWithInvalidEmptyName() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipe("", "easy salad", "chop and mix", ingredients, 2));
    assertEquals("The string for the parameter 'Name' was blank, try again.",
        exception.getMessage());
  }

  @Test
  void createInstanceWithInvalidBlankName() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipe("  ", "easy salad", "chop and mix", ingredients, 2));
    assertEquals("The string for the parameter 'Name' was blank, try again.",
        exception.getMessage());
  }

  @Test
  void createInstanceWithInvalidNullDescription() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipe("salad", null, "chop and mix", ingredients, 2));
    assertEquals("The string for the parameter 'Description' was blank, try again.",
        exception.getMessage());
  }

  @Test
  void createInstanceWithInvalidEmptyDescription() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipe("salad", "", "chop and mix", ingredients, 2));
    assertEquals("The string for the parameter 'Description' was blank, try again.",
        exception.getMessage());
  }

  @Test
  void createInstanceWithInvalidBlankDescription() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipe("salad", "   ", "chop and mix", ingredients, 2));
    assertEquals("The string for the parameter 'Description' was blank, try again.",
        exception.getMessage());
  }

  @Test
  void createInstanceWithInvalidNullHowTo() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipe("salad", "easy salad", null, ingredients, 2));
    assertEquals("The string for the parameter 'HowTo' was blank, try again.",
        exception.getMessage());
  }

  @Test
  void createInstanceWithInvalidEmptyHowTo() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipe("salad", "easy salad", "", ingredients, 2));
    assertEquals("The string for the parameter 'HowTo' was blank, try again.",
        exception.getMessage());
  }

  @Test
  void createInstanceWithInvalidBlankHowTo() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipe("salad", "easy salad", "   ", ingredients, 2));
    assertEquals("The string for the parameter 'HowTo' was blank, try again.",
        exception.getMessage());
  }

  @Test
  void createInstanceWithInvalidEmptyIngredients() {
    emptyIngredients = new ArrayList<>();
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipe("salad", "easy salad", "chop and mix", emptyIngredients, 2));
    assertEquals("The List 'Ingredients' cannot be null or empty, try again.",
        exception.getMessage());
  }

  @Test
  void createInstanceWithInvalidNullIngredients() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipe("salad", "easy salad", "chop and mix", null, 2));
    assertEquals("The List 'Ingredients' cannot be null or empty, try again.",
        exception.getMessage());
  }

  @Test
  void createInstanceWithInvalidLessThanOnePortions() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipe("salad", "easy salad", "chop and mix", ingredients, 0));
    assertEquals("The integer 'Portions' must be between 1 and 50, try again.",
        exception.getMessage());
  }

  @Test
  void createInstanceWithInvalidGreaterThanFiftyPortions() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipe("salad", "easy salad", "chop and mix", ingredients, 51));
    assertEquals("The integer 'Portions' must be between 1 and 50, try again.",
        exception.getMessage());
  }


}
