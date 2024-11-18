package edu.ntnu.idi.idatt;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

public class foodStorage {

  private final List<ingredient> foodStorage;

  public foodStorage() {
    foodStorage = new ArrayList<>();
  }

  /*
  Method: Register ingredient in arraylist foodStorage.
  This method will go through the arraylist foodStorage and see if the new ingredient already exists.
  If it exists and has the same expiration date the amount will increase. If the ingredient exist bus has a different
  expiration date a new element in the array will be created with the same name.
  If the name does not exist a new ingredient will be added to the arraylist.
   */
  public void registerIngredient(ingredient ing) {
    for (ingredient i : foodStorage) {

      if (i.getName().equals(ing.getName()) && i.getExpirationDate()
          .equals(ing.getExpirationDate())) {
        i.setAmount(i.getAmount() + ing.getAmount());
      } else {
        foodStorage.add(ing);
      }
    }
  }

  /*
  Method: Search for ingredient in foodStorage.
  The method returns an arraylist. An arraylist called search is created and the for loop goes through
  the arraylist foodStorage and if the name corresponds with the input name the ingredient is added
  to the new arraylist search. This list is then returned.
   */
  public List<ingredient> getIngredient(String name) {
    List<ingredient> search = new ArrayList<>();
    for (ingredient i : foodStorage) {
      if (i.getName().equals(name)) {
        search.add(i);
      }
    }
    return search;
  }

  /*
  Method: Remove amount of a certain ingredient.
  Create list of ingredients with same name called copies. Get the total amount of that ingredient.
  If totalAmount is less than amount it cannot be used. Throw exception.
  Arraylist copies is sorted by date with comparator.
  While loop where the loop continues as long as the amount is greater than 0.
  For loop goes through the ingredients in copies list. If the amount of the first ingredient is more
  than input amount a new amount is set for the ingredient and amount is set to 0. If the amount of
  the ingredient is equal to input amount the ingredient is removed from the foodStorage list and
  input amount is set to 0. Lastly it the amount of the ingredient is less than the input amount,
  the new input amount is amount minus ingredient amount. And the ingredient will be removed from foodStorage.
  The while loop continues until input amount is 0.
   */
  public void removeIngredient(String name, double amount) {
    List<ingredient> copies = new ArrayList<>();
    double totalAmount = 0;
    for (ingredient i : foodStorage) {
      if (i.getName().equals(name)) {
        copies.add(i);
        totalAmount += i.getAmount();
      }
    }
    if (totalAmount < amount) {
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

  /*
  Method: Return list of ingredients that has expired
  Create a new arraylist "expired". For loop goes through the ingredients and adds the ones that
  has an expiration date that is before the given date to the expired list. I sort the list in
  date order and return the list.
   */
  public List<ingredient> expiredIngredients(int year, int month, int day) {
    List<ingredient> expired = new ArrayList<>();
    for (ingredient i : foodStorage) {
      if (i.getExpirationDate().isBefore(LocalDate.of(year, month, day))) {
        expired.add(i);
      }
    }
    expired.sort(Comparator.comparing(ingredient::getExpirationDate));
    return expired;
  }

  /*
  Method: Sort list of ingredients in foodStorage.
  Sort foodStorage list with comparator by name and return sorted list.
   */
  public List<ingredient> sortedList() {
    foodStorage.sort(Comparator.comparing(ingredient::getName));
    return foodStorage;
  }


}
