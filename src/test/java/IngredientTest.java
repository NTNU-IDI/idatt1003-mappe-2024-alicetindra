
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import edu.ntnu.idi.idatt.ingredient;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

/**
 * Tests the class ingredient.
 *
 * <p>The following is tested:</p>
 *
 * <p>Positive tests:</p>
 *
 * <ul>
 *   <li> Testing that a creation of an ingredient object with valid parameters is possible.</li>
 *   <li> Testing that setting the amount results in the correct amount being set </li>
 * </ul>
 *
 * <p>Negative tests:</p>
 *
 * <ul>
 *   <li>Testing that it is not possible to set amount to less than 0.</li>
 *   <li>Testing that it is not possible to create an ingredient with a name that is empty, blank,
 *   or has the value 'null'. </li>
 *   <li>Testing that it is not possible to crate an ingredient with a negative amount or an amount
 *   that is zero. </li>
 *   <li>Testing that it is not possible to crate an ingredient with a measureUnit that is empty,
 *   blank, or has the value 'null'. </li>
 *  <li>Testing that it is not possible to crate an ingredient with an expirationDate that is null.</li>
 *  <li>Testing that it is not possible to crate an ingredient with a negative price.</li>
 * </ul>
 */
public class IngredientTest {

  //-------------------------------POSITIVE TESTS-------------------------------------

  /**
   * Test creation of an instance of ingredient where we provide valid parameters.
   *
   * <p>This is a <b>positive</b> test since we are testing the functionality that the class
   * ingredient is expected to have/implement.</p>
   *
   * <p>Expected outcome: An instance is created, and when asking the instance for name, amount,
   * expiration date and price, we expect the parameters returned to be the same as provided. </p>
   */
  @Test
  void createInstanceWithValidParameters() {
    ingredient ingredient = new ingredient("milk", 2, "litres",
        LocalDate.of(2025, 1, 2), 20);

    assertEquals("milk", ingredient.getName());
    assertEquals(2, ingredient.getAmount());
    assertEquals(LocalDate.of(2025, 1, 2), ingredient.getExpirationDate());
    assertEquals(20, ingredient.getPrice());
  }

  /**
   * Test setting a valid amount.
   *
   * <p>Expected outcome: The amount of the ingredient should change to the set valid value.</p>
   */
  @Test
  void setValidAmount() {
    ingredient ingredient = new ingredient("milk", 2, "litres",
        LocalDate.of(2025, 1, 2), 20);
    ingredient.setAmount(10);
    assertEquals(10, ingredient.getAmount());
  }

  /**
   * Test an instance of ingredient toString.
   *
   * <p>Expected outcome: A string " grocery{ milk, 2.0 litres, Expiration date: 2025-01-20, 20.0
   * kr.}\n" is expected.</p>
   */
  @Test
  void testToString() {
    ingredient ingredient = new ingredient("milk", 2.0, "litres",
        LocalDate.of(2025, 1, 20), 20);
    String expected = " grocery{ milk, 2.0 litres, Expiration date: 2025-01-20, 20.0 kr.}\n";
    assertEquals(expected, ingredient.toString());
  }

  //-------------------------------NEGATIVE TESTS-------------------------------------

  /**
   * Test creation of an instance of ingredient where the name provided is an invalid empty string.
   *
   * <p>Expected outcome: An instance should not be created if the name is set to an empty string,
   * and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  public void createInstanceWithInvalidEmptyName() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new ingredient("", 2, "litres",
            LocalDate.of(2025, 1, 2), 20));
    assertEquals("The string for the parameter 'Name' was blank, empty or null. Try again.",
        exception.getMessage());
  }

  /**
   * Test creation of an instance of ingredient where the name provided is an invalid blank
   * string.(A string of only white spaces)
   *
   * <p>Expected outcome: An instance should not be created if the name is set to a blank string,
   * and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  public void createInstanceWithInvalidBlankName() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new ingredient("  ", 2, "litres",
            LocalDate.of(2025, 1, 2), 20));
    assertEquals("The string for the parameter 'Name' was blank, empty or null. Try again.",
        exception.getMessage());
  }

  /**
   * Test creation of an instance of ingredient where the name provided has an invalid value null.
   *
   * <p>Expected outcome: An instance should not be created if the name is set to null,
   * and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  public void createInstanceWithInvalidNullName() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new ingredient(null, 2, "litres",
            LocalDate.of(2025, 1, 2), 20));
    assertEquals("The string for the parameter 'Name' was blank, empty or null. Try again.",
        exception.getMessage());
  }

  /**
   * Test creation of an instance of ingredient where the amount provided is an invalid negative
   * double.
   *
   * <p>Expected outcome: An instance should not be created if the amount is set to a negative
   * double, and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  public void createInstanceWithInvalidNegativeAmount() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new ingredient("milk", -3, "litres",
            LocalDate.of(2025, 1, 2), 20));
    assertEquals("The double for the parameter 'Amount' must be greater than 0, try again.",
        exception.getMessage());
  }

  /**
   * Test creation of an instance of ingredient where the amount provided is zero.
   *
   * <p>Expected outcome: An instance should not be created if the amount is set to zero,
   * and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  public void createInstanceWithInvalidZeroAmount() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new ingredient("milk", 0, "litres",
            LocalDate.of(2025, 1, 2), 20));
    assertEquals("The double for the parameter 'Amount' must be greater than 0, try again.",
        exception.getMessage());
  }

  /**
   * Test creation of an instance of ingredient where the measureUnit provided is an invalid empty
   * string.
   *
   * <p>Expected outcome: An instance should not be created if the measureUnit is set to an empty
   * string, and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  public void createInstanceWithInvalidEmptyMeasureUnit() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new ingredient("milk", 2, "",
            LocalDate.of(2025, 1, 2), 20));
    assertEquals("The string for the parameter 'MeasureUnit' was blank, empty or null. Try again.",
        exception.getMessage());
  }

  /**
   * Test creation of an instance of ingredient where the measureUnit provided is an invalid blank
   * string.(A string of only white spaces)
   *
   * <p>Expected outcome: An instance should not be created if the measureUnit is set to aa blank
   * string, and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  public void createInstanceWithInvalidBlankMeasureUnit() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new ingredient("milk", 2, "  ",
            LocalDate.of(2025, 1, 2), 20));
    assertEquals("The string for the parameter 'MeasureUnit' was blank, empty or null. Try again.",
        exception.getMessage());
  }

  /**
   * Test creation of an instance of ingredient where the measureUnit provided has an invalid value
   * null.
   *
   * <p>Expected outcome: An instance should not be created if the measureUnit is set to null,
   * and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  public void createInstanceWithInvalidNullMeasureUnit() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new ingredient("milk", 2, null,
            LocalDate.of(2025, 1, 2), 20));
    assertEquals("The string for the parameter 'MeasureUnit' was blank, empty or null. Try again.",
        exception.getMessage());
  }

  /**
   * Test creation of an instance of ingredient where the expirationDate provided has an invalid
   * value null.
   *
   * <p>Expected outcome: An instance should not be created if the expirationDate is set to null,
   * and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  public void createInstanceWithInvalidNullExpirationDate() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new ingredient("milk", 2, "litres", null, 20));
    assertEquals("The LocalDate for the parameter 'Expiration Date' is null, try again.",
        exception.getMessage());
  }

  /**
   * Test creation of an instance of ingredient where the price provided is an invalid negative
   * double.
   *
   * <p>Expected outcome: An instance should not be created if the price is set to a negative
   * double, and an IllegalArgumentException should be thrown.</p>
   */
  @Test
  public void createInstanceWithInvalidNegativePrice() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new ingredient("milk", 2, "litres",
            LocalDate.of(2025, 1, 2), -20));
    assertEquals("The double for the parameter 'Price' cannot be negative, try again.",
        exception.getMessage());
  }


}
