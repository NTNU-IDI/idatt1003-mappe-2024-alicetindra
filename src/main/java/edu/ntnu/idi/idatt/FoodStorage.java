package edu.ntnu.idi.idatt;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The foodStorage class manages a collection of ingredients, making operations such as
 * registration, removal, and calculations possible.
 *
 * <p>The class provides functionalities such as registering, getting, removing, and printing
 * ingredients. As well as checking for expired ingredients, checking if there is enough ingredients
 * for a recipe, and calculate total price for all ingredients in addition to total price of expired
 * ingredients.</p>
 *
 * @author Tindra
 * @version 3.0
 * @since 0.3
 */
public class FoodStorage {

  private final List<Ingredient> foodStorage = new ArrayList<>();

  /**
   * Register ingredient in food storage. If there's already is an ingredient with the same name and
   * expiration date. The amount from the new ingredient is added to the already existing one. Else
   * the new ingredient is added.
   *
   * @param ing the ingredient to be registered.
   */
  public void registerIngredient(Ingredient ing) {

    for (Ingredient i : foodStorage) {
      if (i.getName().equalsIgnoreCase(ing.getName()) && i.getExpirationDate()
          .equals(ing.getExpirationDate())) {
        i.setAmount(i.getAmount() + ing.getAmount());
        return;
      }
    }
    foodStorage.add(ing);
  }

  /**
   * Retrieves a List of ingredients with a specified name.
   *
   * @param name is a string representing the name of the ingredient to search for.
   * @return is a list representing all ingredients with the parameter name.
   * @throws IllegalArgumentException if the String 'name' is null or only whitespace.
   */
  public List<Ingredient> getIngredient(String name) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException(
          "The string for the parameter 'Name' was blank, try again.");
    }
    List<Ingredient> search = new ArrayList<>();
    for (Ingredient i : foodStorage) {
      if (i.getName().equalsIgnoreCase(name)) {
        search.add(i);
      }
    }
    return search;
  }

  /**
   * Removes a specified amount of an ingredient from the food storage.
   *
   * @param name   is a string representing the name of the ingredient that are to be removed.
   * @param amount the quantity to remove.
   * @throws IllegalArgumentException if the String 'name' is null or only whitespace, the double
   *                                  'amount' is less or equal to 0, or the amount of the
   *                                  ingredient is less then the parameter amount.
   */
  public void removeIngredient(String name, double amount) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException(
          "The string for the parameter 'Name' was blank, try again.");
    }
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount must be greater than 0");
    }
    List<Ingredient> copies = new ArrayList<>();
    double total = 0;
    for (Ingredient i : foodStorage) {
      if (i.getName().equalsIgnoreCase(name)) {
        copies.add(i);
        total += i.getAmount();
      }
    }
    if (copies.isEmpty()) {
      throw new IllegalArgumentException("No ingredient by that name is registered.");
    }
    if (total < amount) {
      throw new IllegalArgumentException(
          "There is not enough of this ingredient, for it to be removed.");
    }
    copies.sort(Comparator.comparing(Ingredient::getExpirationDate));
    while (amount > 0) {
      for (Ingredient i : copies) {
        if (i.getAmount() > amount) {
          i.setAmount(i.getAmount() - amount);
          amount = 0;
        } else if (i.getAmount() == amount) {
          foodStorage.remove(i);
          amount = 0;
        } else {
          amount -= i.getAmount();
          foodStorage.remove(i);
        }
      }
    }
  }

  /**
   * Returns a sorted list of ingredients in foodStorage, sorted by name in alphabetical order.
   *
   * @return a sorted list of ingredients in foodStorage.
   */
  public List<Ingredient> sortedList() {
    foodStorage.sort(Comparator.comparing(Ingredient::getName));
    return foodStorage;
  }

  /**
   * Returns a list of ingredients that will expire before the given date.
   *
   * @param date is a LocalDate to compare the ingredient's expiration dates against.
   * @return a list of ingredients expiring before the given date.
   * @throws IllegalArgumentException if the LocalDate 'expirationDate' is null.
   */
  public List<Ingredient> expireBefore(LocalDate date) {
    if (date == null) {
      throw new IllegalArgumentException(
          "The LocalDate for the parameter 'Expiration Date' is blank, try again.");
    }
    List<Ingredient> willExpire = new ArrayList<>();
    for (Ingredient i : foodStorage) {
      if (i.getExpirationDate().isBefore(date)) {
        willExpire.add(i);
      }
    }
    willExpire.sort(Comparator.comparing(Ingredient::getExpirationDate));
    return willExpire;
  }

  /**
   * Calculates the total price of all ingredients that will expire before the given date.
   *
   * @param date is a LocalDate to compare the ingredient's expiration dates against.
   * @return a double representing the total price for all expired ingredients.
   */
  public double totalPriceExpiration(LocalDate date) {
    double totalPrice = 0;
    for (Ingredient i : foodStorage) {
      if (i.getExpirationDate().isBefore(date)) {
        totalPrice += i.getPrice();
      }
    }
    return Math.round(totalPrice * 100) / 100.0;
  }

  /**
   * Calculates the total price of all ingredients.
   *
   * @return a double representing the total price for all ingredients.
   */
  public double totalPrice() {
    double totalPrice = 0;
    for (Ingredient i : foodStorage) {
      totalPrice += i.getPrice();
    }
    return Math.round(totalPrice * 100.0) / 100.0;
  }


  /**
   * Checks if the ingredients for a given recipe are available in the food storage. Prints out the
   * missing ingredients if any.
   *
   * @param recipe is the recipe to check against.
   * @throws IllegalArgumentException if recipe is null or not found.
   * @see #calculateMissingIngredients(Recipe)
   * @see #printMissingIngredients(String, List)
   */
  public void checkRecipe(Recipe recipe) {
    if (recipe == null) {
      throw new IllegalArgumentException("Recipe does not exist, try again.");
    }
    List<RecipeIngredient> missingIngredients = calculateMissingIngredients(recipe);
    printMissingIngredients(recipe.getName(), missingIngredients);
    System.out.println(recipe);
  }

  /**
   * Calculate the missing ingredients for a recipe.
   *
   * @param recipe is the recipe to calculate missing ingredients for.
   * @return a List of missing ingredients.
   */
  public List<RecipeIngredient> calculateMissingIngredients(Recipe recipe) {
    List<RecipeIngredient> missingIngredients = new ArrayList<>();
    for (RecipeIngredient ing : recipe.getIngredients()) {
      double totalAmount = 0;
      for (Ingredient i : foodStorage) {
        if (i.getName().equalsIgnoreCase(ing.name()) && i.getExpirationDate()
            .isAfter(LocalDate.now())) {
          totalAmount += i.getAmount();
        }
      }
      if (totalAmount < ing.amount()) {
        double missingAmount = ing.amount() - totalAmount;
        missingIngredients.add(new RecipeIngredient(ing.name(), missingAmount, ing.unit()));
      }
    }
    return missingIngredients;
  }

  /**
   * Prints the missing ingredients for a given recipe. The {@code missingIngredients} parameter is
   * a list generated by the {@link #calculateMissingIngredients(Recipe)} method
   *
   * @param recipeName         is a String representing the name of the recipe.
   * @param missingIngredients Is a List of missing ingredients generated by the
   *                           {@link #calculateMissingIngredients(Recipe)} method.
   */
  private void printMissingIngredients(String recipeName,
      List<RecipeIngredient> missingIngredients) {
    if (missingIngredients.isEmpty()) {
      System.out.println("You have all the ingredients for " + recipeName);
    } else {
      System.out.println("You are missing the following ingredients for " + recipeName + ":");
      for (RecipeIngredient miss : missingIngredients) {
        System.out.println("-" + miss.amount() + " " + miss.unit() + " of " + miss.name());
      }
    }
  }

}
