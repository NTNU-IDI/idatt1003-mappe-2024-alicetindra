package edu.ntnu.idi.idatt;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

public class foodStorage {

  private final List<ingredient> foodStorage;

  /**
   * Method: Constructor
   * <p></p>The constructor
   * Creating an arraylist called foodStorage
   */
  public foodStorage() {
    foodStorage = new ArrayList<>();
  }

  /**
   * Method: Register ingredient in arraylist foodStorage.
   * <p>Using for loop to go through all existing ingredients. If no ingredient with same name or
   * expiration date. The new ingredient is added as a new element.
   *
   * @param ing The method takes in an ingredient that is created.
   */
  public void registerIngredient(ingredient ing) {

    for (ingredient i : foodStorage) {
      if (i.getName().equalsIgnoreCase(ing.getName()) && i.getExpirationDate()
          .equals(ing.getExpirationDate())) {
        i.setAmount(i.getAmount() + ing.getAmount());
        return;
      }
    }
    foodStorage.add(ing);
  }

  /**
   * Method: Get ingredient by name.
   * <p>Search for ingredient in foodStorage by name, and the ingredients is added to a new
   * arraylist called "search".
   *
   * @param name Method takes in string parameter. Searching for a name of an ingredient.
   * @return Returning the new composed list of all ingredients with the same name.
   */
  public List<ingredient> getIngredient(String name) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Name is null or empty");
    }
    List<ingredient> search = new ArrayList<>();
    for (ingredient i : foodStorage) {
      if (i.getName().equalsIgnoreCase(name)) {
        search.add(i);
      }
    }
    return search;
  }

  /**
   * Method: Remove certain amount of ingredient
   * <p>Creates new array list called copies. If there are ingredients with same name these are put
   * in this list If total amount of the named ingredient is less than the user wants to remove an
   * exception is thrown. The created list copies is sorted by expiration date. The one with
   * earliest date goes through while loop first. While amount you want to remove is greater than 0
   * thw while loop continues. If the amount of the first ingredient is more than input amount a new
   * amount is set for the ingredient and amount is set to 0. If the amount of the ingredient is
   * equal to input amount the ingredient is removed from the foodStorage list and input amount is
   * set to 0. Lastly it the amount of the ingredient is less than the input amount, the new input
   * amount is amount minus ingredient amount. And the ingredient will be removed from
   * foodStorage.</p>
   *
   * @param name   The name of the ingredient the user want to use
   * @param amount The amount that user want to remove
   */
  public void removeIngredient(String name, double amount) {
    List<ingredient> copies = new ArrayList<>();
    double total = 0;
    for (ingredient i : foodStorage) {
      if (i.getName().equalsIgnoreCase(name)) {
        copies.add(i);
        total += i.getAmount();
      }
    }
    if (total < amount) {
      throw new IllegalArgumentException("Not enough ingredients");
    }
    copies.sort(Comparator.comparing(ingredient::getExpirationDate));
    while (amount > 0) {
      for (ingredient i : copies) {
        if (i.getAmount() > amount) {
          i.setAmount(i.getAmount() - amount);
          amount = 0;
        } else if (i.getAmount() == amount) {
          foodStorage.remove(i);
          amount = 0;
        } else { //if(i.getAmount() < amount)
          amount -= i.getAmount();
          foodStorage.remove(i);
        }
      }
    }
  }

  /**
   * Method: Sort list of ingredients in foodStorage.
   * <p>Sort foodStorage list with comparator by name</p>
   *
   * @return Returning the arraylist foodStorage in alphabetical order.
   */
  public List<ingredient> sortedList() {
    foodStorage.sort(Comparator.comparing(ingredient::getName));
    return foodStorage;
  }

  /**
   * Method: Return list of ingredients that has expired Create a new arraylist "stillGood". For
   * loop goes through the ingredients and adds the ones that has an expiration date that is before
   * the given date to the expired list. I sort the list in date order and return the list.
   */

  /**
   * Method: Get expired products Create a new array list willExpire that in a for loop goes through
   * the list foodStorage. If the ingredients have an expiration date before input date then they
   * are added to the new list.
   *
   * @param date User input a date they want to compare the ingredient's expiration date.
   * @return Returning list of ingredients that will have expired before the input date.
   */
  public List<ingredient> expireBefore(LocalDate date) {
    List<ingredient> willExpire = new ArrayList<>();
    for (ingredient i : foodStorage) {
      if (i.getExpirationDate().isBefore(date)) {
        willExpire.add(i);
      }
    }
    willExpire.sort(Comparator.comparing(ingredient::getExpirationDate));
    return willExpire;
  }

  /**
   * Method: Get total price for non expired items. Go through foodStorage list and if expiration
   * date is before user input the price of the ingredient is added to double totalPrice
   *
   * @param date input for chosen date
   * @return Returning total price for all non expired ingredients.
   */
  public double totalPriceExpiration(LocalDate date) {
    double totalPrice = 0;
    for (ingredient i : foodStorage) {
      if (i.getExpirationDate().isBefore(date)) {
        totalPrice += i.getPrice();
      }
    }
    return totalPrice;
  }

  /**
   * Method: Calculate total price
   *
   * @return Return total price with max two decimals
   */
  public double totalPrice() {
    double totalPrice = 0;
    for (ingredient i : foodStorage) {
      totalPrice += i.getPrice();
    }
    return Math.round(totalPrice * 100.0) / 100.0;
  }

  /**
   * @param recipe
   */
  public void checkRecipe(recipe recipe) {
    List<recipeIngredient> missingIngredients = new ArrayList<>();
    for (recipeIngredient ing : recipe.getIngredients()) {
      double totalAmount = 0;
      for (ingredient i : foodStorage) {
        if (i.getName().equalsIgnoreCase(ing.name())) {
          totalAmount += i.getAmount();
        }
      }
      if (totalAmount < ing.amount()) {
        double missingAmount = ing.amount() - totalAmount;
        missingIngredients.add(new recipeIngredient(ing.name(), missingAmount, ing.unit()));
      }
    }
    if (missingIngredients.isEmpty()) {
      System.out.println("You have all the ingredients for " + recipe.getName());
    } else {
      System.out.println("You are missing the following ingredients for " + recipe.getName() + ":");
      for (recipeIngredient miss : missingIngredients) {
        System.out.println("-" + miss.amount() + " " + miss.unit() + " of " + miss.name());
      }
    }
  }


}
