package edu.ntnu.idi.idatt;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {

  public foodStorage foodStorage;
  public ingredient defaultIngredient;

  //declare scanner as instance variables
  private Scanner lines;
  private Scanner integer;
  private Scanner doubles;

  public void init() {

    foodStorage = new foodStorage();
    //scanner for user to write string as well as scanner for numbers.
    lines = new Scanner(System.in);
    integer = new Scanner(System.in);
    doubles = new Scanner(System.in);
    /**
     * Adding default ingredients while starting program.
     */
    defaultIngredients();
  }

  public void start() {
    while (true) {
      //menu for user.
      try { //try and catch if input is invalid
        System.out.println("""
            Menu:
            1. Register new ingredient
            2. Search for an ingredient in food storage
            3. Remove a certain amount of ingredient from food storage
            4. Print out overview of ingredients in food storage
            5. Print out ingredients that expire before a certain date + total price of expiring products
            6. Calculate total price of ingredients
            7. Register a recipe
            8. Search food storage to check if you have enough ingredients for a recipe
            9. Store recipe in cook book
            10. Give suggestions for recipe based on items in food storage
            11. Exit program
            """);
        int choice = integer.nextInt();

        //if user chooses 11. While loop is finished and program ends.
        if (choice == 11) {
          System.out.println("Exiting program");
          break;
        }
        switch (choice) {
          case 1:
            menu();
            break;
          case 2:
            searchIngredient();
            break;
          case 3:
            removeIngredient();
            break;
          case 4:
            printSortedList();
            break;
          case 5:
            expireBefore();
            break;
          case 6:
            totalPrice();
            break;
          case 7:
            System.out.println("Register a recipe ");
            break;
          case 8:
            System.out.println(
                "Search food storage to check if you have enough ingredients for a recipe");
            break;
          case 9:
            System.out.println("Store recipe in cook book");
            break;
          case 10:
            System.out.println("Give suggestions for recipe based on items in food storage");
            break;
          default:
            System.out.println("Invalid choice");
        }
      } catch (Exception e) {
        System.out.println("Invalid input. Please try again:)");
        integer.nextLine(); //skip user input
      }
    }
  }

  /**
   * Method: Adding default ingredients to foodStorage when program starts.
   */
  private void defaultIngredients() {
    List<ingredient> defaultIngredients = List.of(
        new ingredient("milk", 2, "litres",
            LocalDate.of(2024, 12, 30), 35.50),
        new ingredient("bread", 1, "pc",
            LocalDate.of(2025, 1, 15), 18.90),
        new ingredient("frozen berries", 500, "grams",
            LocalDate.of(2025, 5, 10), 42.0),
        new ingredient("honey", 250, "grams",
            LocalDate.of(2030, 1, 20), 89.90),
        new ingredient("peanut butter", 400, "grams",
            LocalDate.of(2025, 8, 12), 65.6));

    for (ingredient i : defaultIngredients) {
      foodStorage.registerIngredient(i);
    }
    System.out.println("Defaulting ingredient has been added. ");

  }

  //Method for each switch case to clean up code
  public void menu() {
    System.out.println("Enter grocery name: ");
    String name = lines.nextLine();
    System.out.println("Enter grocery amount: ");
    double amount = doubles.nextDouble();
    System.out.println("Enter measurement unit: ");
    String measureUnit = lines.nextLine();
    System.out.println("Enter year of best before date: ");
    int year = integer.nextInt();
    System.out.println("Enter month of best before date: ");
    int month = integer.nextInt();
    System.out.println("Enter day of best before date: ");
    int day = integer.nextInt();
    System.out.println("Enter price of grocery: ");
    double price = doubles.nextDouble();
    try {
      //attempt to create an ingredient with user input
      ingredient ingredient = new ingredient(name, amount, measureUnit,
          LocalDate.of(year, month, day), price);
      System.out.println(ingredient);
      foodStorage.registerIngredient(ingredient);
    } catch (IllegalArgumentException e) {
      //handle invalid input (e.g, negative number, invalid date or letters instead of numbers)
      System.out.println("Invalid input: " + e.getMessage());
    }
  }

  public void searchIngredient() {
    System.out.println("Enter ingredient name: ");
    String name = lines.nextLine();
    try {
      System.out.println(foodStorage.getIngredient(name));
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid input: " + e.getMessage());
    }
  }

  public void removeIngredient() {
    System.out.println("Enter ingredient name: ");
    String name = lines.nextLine();
    System.out.println("What amount should be removed?");
    double amount = doubles.nextDouble();
    try {
      foodStorage.removeIngredient(name, amount);
      System.out.println("Ingredient removed successfully");
    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public void printSortedList() {
    System.out.println(foodStorage.sortedList());
  }

  /**
   * Have to check exception anf test negative!!
   */
  public void expireBefore() {
    System.out.println("Enter expiration year: ");
    int year = integer.nextInt();
    System.out.println("Enter expiration month: ");
    int month = integer.nextInt();
    System.out.println("Enter expiration date: ");
    int day = integer.nextInt();
    try {
      LocalDate date = LocalDate.of(year, month, day);
      System.out.println(
          "These products will expire before " + date + ":\n" + foodStorage.expireBefore(date)
              + "\nTotal price: " +
              foodStorage.totalPriceExpiration(date) + " kr.");
    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  /**
   *
   */
  public void totalPrice() {
    System.out.println("Total price of ingredients: " + foodStorage.totalPrice() + " kr.");
  }

}
