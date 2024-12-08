
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
 *   <li>Testing that is it possible to create a recipe with valid parameters. </li>
 *   <li> Testing that is it possible to retrieve recipe name.</li>
 *   <li> Testing that is it possible to retrieve recipe ingredients.</li>
 *
 * </ul>
 *
 * <p>Negative tests:</p>
 *
 * <ul>
 *   <li>Testing that it is not possible to create a recipe with a name that is empty, blank,
 *   or has the value 'null'. </li>
 *   <li>Testing that it is not possible to create a recipe with a description that is empty,
 *   blank, or has the value 'null'. </li>
 *   <li>Testing that it is not possible to create a recipe with a howTo that is empty,
 *   blank, or has the value 'null'. </li>
 *   <li>Testing that it is not possible to create a recipe with a howTo that is empty,
 *   blank, or has the value 'null'. </li>
 *   <li>Testing that it is not possible to create a recipe where the list of ingredients are null
 *   or empty.</li>
 *   <li>Testing that it is not possible to create a recipe where the portions are less than one,
 *   or grater than 50.</li>
 * </ul>
 */
public class RecipeTest {

  private List<recipeIngredient> ingredients;
  private List<recipeIngredient> emptyIngredients;

  /**
   * Sets up the test environment before each test.
   */
  @BeforeEach
  void setUp() {
    recipeIngredient ingredient1 = new recipeIngredient("tomato", 200.0, "g");
    recipeIngredient ingredient2 = new recipeIngredient("carrot", 100.0, "g");

    ingredients = List.of(ingredient1, ingredient2);
  }


  /**
   * Test creation of an instance of recipe where we provide valid parameters.
   *
   * <p>This is a <b>positive</b> test since we are testing the functionality that the class
   * recipe is expected to have/implement.</p>
   *
   * <p>Expected outcome: An instance is created, and when asking the instance for name, and
   * ingredients, we expect the parameters returned to be the same as provided. </p>
   */
  @Test
  void createInstanceWithValidParameters() {

    edu.ntnu.idi.idatt.recipe recipe = new recipe("salad", "easy salad",
        "chop and mix", ingredients, 2);

    assertEquals("salad", recipe.getName());
    assertEquals(ingredients, recipe.getIngredients());
  }

  //-------------------------------NEGATIVE TESTS-------------------------------------

  /**
   * Test creation of an instance of recipe where the name provided has an invalid value null.
   *
   * <p>Expected outcome: An instance should not be created if the name is set to null,
   * and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  void createInstanceWithInvalidNullName() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipe(null, "easy salad", "chop and mix", ingredients, 2));
    assertEquals("The string for the parameter 'Name' was blank, try again.",
        exception.getMessage());
  }

  /**
   * Test creation of an instance of recipe where the name provided is an invalid empty string.
   *
   * <p>Expected outcome: An instance should not be created if the name is set to an empty string,
   * and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  void createInstanceWithInvalidEmptyName() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipe("", "easy salad", "chop and mix", ingredients, 2));
    assertEquals("The string for the parameter 'Name' was blank, try again.",
        exception.getMessage());
  }

  /**
   * Test creation of an instance of recipe where the name provided is an invalid blank string.(A
   * string of only white spaces)
   *
   * <p>Expected outcome: An instance should not be created if the name is set to a blank string,
   * and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  void createInstanceWithInvalidBlankName() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipe("  ", "easy salad", "chop and mix", ingredients, 2));
    assertEquals("The string for the parameter 'Name' was blank, try again.",
        exception.getMessage());
  }

  /**
   * Test creation of an instance of recipe where the description provided has an invalid value
   * null.
   *
   * <p>Expected outcome: An instance should not be created if the description is set to null,
   * and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  void createInstanceWithInvalidNullDescription() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipe("salad", null, "chop and mix", ingredients, 2));
    assertEquals("The string for the parameter 'Description' was blank, try again.",
        exception.getMessage());
  }

  /**
   * Test creation of an instance of recipe where the description provided is an invalid empty
   * string.
   *
   * <p>Expected outcome: An instance should not be created if the description is set to an empty
   * string, and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  void createInstanceWithInvalidEmptyDescription() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipe("salad", "", "chop and mix", ingredients, 2));
    assertEquals("The string for the parameter 'Description' was blank, try again.",
        exception.getMessage());
  }

  /**
   * Test creation of an instance of recipe where the description provided is an invalid blank
   * string.(A string of only white spaces)
   *
   * <p>Expected outcome: An instance should not be created if the description is set to a blank
   * string, and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  void createInstanceWithInvalidBlankDescription() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipe("salad", "   ", "chop and mix", ingredients, 2));
    assertEquals("The string for the parameter 'Description' was blank, try again.",
        exception.getMessage());
  }


  /**
   * Test creation of an instance of recipe where the howTo provided has an invalid value null.
   *
   * <p>Expected outcome: An instance should not be created if the howTo is set to null,
   * and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  void createInstanceWithInvalidNullHowTo() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipe("salad", "easy salad", null, ingredients, 2));
    assertEquals("The string for the parameter 'HowTo' was blank, try again.",
        exception.getMessage());
  }

  /**
   * Test creation of an instance of recipe where the howTo provided is an invalid empty string.
   *
   * <p>Expected outcome: An instance should not be created if the howTo is set to an empty string,
   * and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  void createInstanceWithInvalidEmptyHowTo() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipe("salad", "easy salad", "", ingredients, 2));
    assertEquals("The string for the parameter 'HowTo' was blank, try again.",
        exception.getMessage());
  }

  /**
   * Test creation of an instance of recipe where the howTo provided is an invalid blank string.(A
   * string of only white spaces)
   *
   * <p>Expected outcome: An instance should not be created if the howTo is set to a blank string,
   * and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  void createInstanceWithInvalidBlankHowTo() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipe("salad", "easy salad", "   ", ingredients, 2));
    assertEquals("The string for the parameter 'HowTo' was blank, try again.",
        exception.getMessage());
  }

  /**
   * Test creation of an instance of recipe where the list of ingredients provided is empty.
   *
   * <p>Expected outcome: An instance should not be created if the list of ingredients is empty,
   * and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  void createInstanceWithInvalidEmptyIngredients() {
    emptyIngredients = new ArrayList<>();
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipe("salad", "easy salad", "chop and mix", emptyIngredients, 2));
    assertEquals("The List 'Ingredients' cannot be null or empty, try again.",
        exception.getMessage());
  }

  /**
   * Test creation of an instance of recipe where the list of ingredients provided is set to null.
   *
   * <p>Expected outcome: An instance should not be created if the list of ingredients is set to
   * null, and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  void createInstanceWithInvalidNullIngredients() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipe("salad", "easy salad", "chop and mix", null, 2));
    assertEquals("The List 'Ingredients' cannot be null or empty, try again.",
        exception.getMessage());
  }

  /**
   * Test creation of an instance of recipe where the portions are set to less than one.
   *
   * <p>Expected outcome: An instance should not be created if the portions are set to less than
   * one, and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  void createInstanceWithInvalidLessThanOnePortions() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipe("salad", "easy salad", "chop and mix", ingredients, 0));
    assertEquals("The integer 'Portions' must be between 1 and 50, try again.",
        exception.getMessage());
  }

  /**
   * Test creation of an instance of recipe where the portions are set to greater than fifty.
   *
   * <p>Expected outcome: An instance should not be created if the portions are set to greater than
   * fifty, and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  void createInstanceWithInvalidGreaterThanFiftyPortions() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new recipe("salad", "easy salad", "chop and mix", ingredients, 51));
    assertEquals("The integer 'Portions' must be between 1 and 50, try again.",
        exception.getMessage());
  }


}
