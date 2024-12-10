import edu.ntnu.idi.idatt.RecipeIngredient;
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
 *   <li> Testing that is it possible to create a recipeIngredient with valid parameters. </li>
 *   <li> Testing that is it possible to retrieve recipeIngredient name.</li>
 *   <li> Testing that is it possible to retrieve recipeIngredient amount.</li>
 *   <li> Testing that is it possible to retrieve recipeIngredient unit of measurement.</li>
 * </ul>
 *
 * <p>Negative tests:</p>
 *
 * <ul>
 *   <li>Testing that it is not possible to create a recipeIngredient with a name that is empty, blank,
 *   or has the value 'null'. </li>
 *   <li>Testing that it is not possible to create a recipeIngredient with an amount that is less than 1. </li>
 *   <li>Testing that it is not possible to create a recipeIngredient with a unit of measurement that is empty, blank,
 *   or has the value 'null'.  </li>
 *
 * </ul>
 */
public class RecipeIngredientTest {

  //-------------------------------POSITIVE TESTS-------------------------------------

  /**
   * Test creation of an instance of recipe where we provide valid parameters.
   *
   * <p>This is a <b>positive</b> test since we are testing the functionality that the class
   * recipe is expected to have/implement.</p>
   *
   * <p>Expected outcome: An instance is created, and when asking the instance for name, ingredient
   * amount, and measurement unit, we expect the parameters returned to be the same as provided.
   * </p>
   */
  @Test
  void createInstanceWithValidParameters() {
    RecipeIngredient ingredient = new RecipeIngredient("milk", 2.0, "litres");

    assertEquals("milk", ingredient.name());
    assertEquals(2.0, ingredient.amount());
    assertEquals("litres", ingredient.unit());
  }

  /**
   * Test an instance of recipeIngredient toString.
   *
   * <p>Expected outcome: A string "2.0 litres milk\n" is expected.</p>
   */
  @Test
  void testToString() {
    RecipeIngredient ingredient = new RecipeIngredient("milk", 2.0, "litres");
    assertEquals("2.0 litres milk\n", ingredient.toString());
  }

  //-------------------------------NEGATIVE TESTS-------------------------------------

  /**
   * Test creation of an instance of recipeIngredient where the name provided has an invalid value
   * null.
   *
   * <p>Expected outcome: An instance should not be created if the name is set to null,
   * and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  void createInstanceWithInvalidNullName() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new RecipeIngredient(null, 2.0, "litres"));
    assertEquals("The string for the parameter 'Name' was blank, try again.",
        exception.getMessage());
  }

  /**
   * Test creation of an instance of recipeIngredient where the name provided is an invalid empty
   * string.
   *
   * <p>Expected outcome: An instance should not be created if the name is set to an empty string,
   * and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  void createInstanceWithInvalidEmptyName() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new RecipeIngredient("", 2.0, "litres"));
    assertEquals("The string for the parameter 'Name' was blank, try again.",
        exception.getMessage());
  }

  /**
   * Test creation of an instance of recipeIngredient where the name provided is an invalid blank
   * string.(A string of only white spaces)
   *
   * <p>Expected outcome: An instance should not be created if the name is set to a blank string,
   * and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  void createInstanceWithInvalidBlankName() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new RecipeIngredient("  ", 2.0, "litres"));
    assertEquals("The string for the parameter 'Name' was blank, try again.",
        exception.getMessage());
  }


  @Test
  void createInstanceWithInvalidNegativeAmount() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new RecipeIngredient("milk", -1.0, "litres"));
    assertEquals("The double for the parameter 'Amount' must be greater than 0, try again.",
        exception.getMessage());
  }

  @Test
  void createInstanceWithInvalidZeroAmount() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new RecipeIngredient("milk", 0, "litres"));
    assertEquals("The double for the parameter 'Amount' must be greater than 0, try again.",
        exception.getMessage());
  }

  /**
   * Test creation of an instance of recipeIngredient where the unit of measurement provided has an
   * invalid value null.
   *
   * <p>Expected outcome: An instance should not be created if the unit of measurement is set to
   * null, and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  void createInstanceWithInvalidNullMeasureUnit() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new RecipeIngredient("milk", 2.0, null));
    assertEquals("The string for the parameter 'unit' was blank, try again.",
        exception.getMessage());
  }

  /**
   * Test creation of an instance of recipeIngredient where the unit of measurement provided is an
   * invalid empty string.
   *
   * <p>Expected outcome: An instance should not be created if the unit of measurement is set to an
   * empty string, and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  void createInstanceWithInvalidEmptyMeasureUnit() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new RecipeIngredient("milk", 2.0, ""));
    assertEquals("The string for the parameter 'unit' was blank, try again.",
        exception.getMessage());
  }

  /**
   * Test creation of an instance of recipeIngredient where the unit of measurement provided is an
   * invalid blank string.(A string of only white spaces)
   *
   * <p>Expected outcome: An instance should not be created if the unit of measurement is set to a
   * blank string, and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  void createInstanceWithInvalidBlankMeasureUnit() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new RecipeIngredient("milk", 2.0, " "));
    assertEquals("The string for the parameter 'unit' was blank, try again.",
        exception.getMessage());
  }


}
