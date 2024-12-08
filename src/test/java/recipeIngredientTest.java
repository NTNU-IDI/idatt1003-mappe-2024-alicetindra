import edu.ntnu.idi.idatt.recipeIngredient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
public class recipeIngredientTest {

  //-------------------------------POSITIVE TESTS-------------------------------------

  /**
   *
   */
  @Test
  void createInstanceWithValidParameters() {
    //assertDoesNotThrow(() -> new recipeIngredient("milk", 2.0, "litres"));

    recipeIngredient ingredient = new recipeIngredient("milk", 2.0, "litres");

    assertEquals("milk", ingredient.name());
    assertEquals(2.0, ingredient.amount());
    assertEquals("litres", ingredient.unit());
  }

  //-------------------------------NEGATIVE TESTS-------------------------------------

  @Test
  void createInstanceWithInvalidNullName() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipeIngredient(null, 2.0, "litres"));
    assertEquals("The string for the parameter 'Name' was blank, try again.",
        exception.getMessage());
  }

  @Test
  void createInstanceWithInvalidEmptyName() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipeIngredient("", 2.0, "litres"));
    assertEquals("The string for the parameter 'Name' was blank, try again.",
        exception.getMessage());
  }

  @Test
  void createInstanceWithInvalidBlankName() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipeIngredient("  ", 2.0, "litres"));
    assertEquals("The string for the parameter 'Name' was blank, try again.",
        exception.getMessage());
  }

  @Test
  void createInstanceWithInvalidNegativeAmount() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipeIngredient("milk", -1.0, "litres"));
    assertEquals("The double for the parameter 'Amount' must be greater than 0, try again.",
        exception.getMessage());
  }

  @Test
  void createInstanceWithInvalidZeroAmount() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipeIngredient("milk", 0, "litres"));
    assertEquals("The double for the parameter 'Amount' must be greater than 0, try again.",
        exception.getMessage());
  }

  @Test
  void createInstanceWithInvalidNullMeasureUnit() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipeIngredient("milk", 2.0, null));
    assertEquals("The string for the parameter 'unit' was blank, try again.",
        exception.getMessage());
  }

  @Test
  void createInstanceWithInvalidEmptyMeasureUnit() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipeIngredient("milk", 2.0, ""));
    assertEquals("The string for the parameter 'unit' was blank, try again.",
        exception.getMessage());
  }

  @Test
  void createInstanceWithInvalidBlankMeasureUnit() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipeIngredient("milk", 2.0, " "));
    assertEquals("The string for the parameter 'unit' was blank, try again.",
        exception.getMessage());
  }

  @Test
  void testToString() {
    recipeIngredient ingredient = new recipeIngredient("milk", 2.0, "litres");
    assertEquals("2.0 litres milk\n", ingredient.toString());
  }
}
