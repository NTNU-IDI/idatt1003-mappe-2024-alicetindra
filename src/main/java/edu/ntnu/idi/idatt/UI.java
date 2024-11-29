package edu.ntnu.idi.idatt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents the User Interface for the program. Handles all user interactions such as
 * displaying menus, receiving input, and delegating tasks to other classes.
 *
 * <p>Uses {@link foodStorage} for managing ingredients and {@link cookBook}
 * for handling recipes.</p>
 *
 * @author Tindra Lindgren
 * @version 1.0
 * @since 2024-11-27
 */
public class UI {

  private static final int REGISTER_INGREDIENT = 1;
  private static final int SEARCH_INGREDIENT = 2;
  private static final int REMOVE_INGREDIENT = 3;
  private static final int PRINT_OVERVIEW = 4;
  private static final int EXPIRE_BEFORE = 5;
  private static final int CALCULATE_TOTAL_PRICE = 6;
  private static final int REGISTER_RECIPE = 7;
  private static final int CHECK_RECIPE = 8;
  private static final int PRINT_RECIPES = 9;
  private static final int SUGGEST_RECIPE = 10;
  private static final int EXIT_PROGRAM = 0;

  public foodStorage foodStorage;
  public cookBook cookBook;

  private Scanner lines;
  private Scanner numbers;


  /**
   *
   */
  public void init() {

    foodStorage = new foodStorage();
    cookBook = new cookBook();

    lines = new Scanner(System.in);
    numbers = new Scanner(System.in);

    setDefaultIngredients();
    setDefaultRecipes();
  }

  /**
   *
   */
  public void start() {
    while (true) {
      try {
        displayMenu();
        int choice = numbers.nextInt();

        if (choice == EXIT_PROGRAM) {
          System.out.println("Exiting program");
          break;
        }
        handleMenu(choice);
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please try again:)");
        numbers.nextLine();
      }
    }
  }

  /**
   *
   */
  private void displayMenu() {
    System.out.println("""
        Menu:
        1. Register new ingredient.
        2. Search for an ingredient in food storage.
        3. Remove a certain amount of ingredient from food storage.
        4. Print out overview of ingredients in food storage.
        5. Print out ingredients that expire before a certain date + total price of expiring products.
        6. Calculate total price of groceries.
        7. Register a recipe.
        8. Search food storage to check if you have enough ingredients for a recipe.
        9. Print out all recipes.
        10. Give suggestions for recipe based on items in food storage.
        0. Exit program.
        """);
  }

  /**
   * @param choice
   */
  private void handleMenu(int choice) {
    switch (choice) {
      case REGISTER_INGREDIENT -> registerIngredient();
      case SEARCH_INGREDIENT -> searchIngredient();
      case REMOVE_INGREDIENT -> removeIngredient();
      case PRINT_OVERVIEW -> printSortedIngredients();
      case EXPIRE_BEFORE -> expireBefore();
      case CALCULATE_TOTAL_PRICE -> totalPrice();
      case REGISTER_RECIPE -> registerRecipe();
      case CHECK_RECIPE -> checkRecipe();
      case PRINT_RECIPES -> printRecipe();
      case SUGGEST_RECIPE -> suggestRecipe();
      default -> System.out.println("Invalid choice. Choose between 0-10.");
    }
  }

  /**
   *
   */
  private void registerIngredient() {
    System.out.println("Enter grocery name: ");
    String name = lines.nextLine();

    System.out.println("Enter grocery amount: ");
    double amount = numbers.nextDouble();

    System.out.println("Enter unit of measurement: ");
    String measureUnit = lines.nextLine();

    System.out.println("Enter expiration date in format yyyy-mm-dd: ");
    String expirationDate = lines.nextLine();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate date = LocalDate.parse(expirationDate, formatter);

    System.out.println("Enter price of grocery: ");
    double price = numbers.nextDouble();
    try {
      ingredient ingredient = new ingredient(name, amount, measureUnit,
          date, price);
      System.out.println(ingredient);
      foodStorage.registerIngredient(ingredient);
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid input: " + e.getMessage());
    }
  }

  /**
   *
   */
  private void searchIngredient() {
    System.out.println("Enter ingredient name: ");
    String name = lines.nextLine();
    try {
      if (foodStorage.getIngredient(name).isEmpty()) {
        System.out.println("Ingredient not found");
      } else {
        System.out.println(foodStorage.getIngredient(name));
      }
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid input: " + e.getMessage());
    }
  }

  /**
   *
   */
  private void removeIngredient() {
    System.out.println("Enter ingredient name: ");
    String name = lines.nextLine();
    System.out.println("What amount should be removed?");
    double amount = numbers.nextDouble();
    try {
      foodStorage.removeIngredient(name, amount);
      System.out.println("Ingredient removed successfully");
    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  /**
   *
   */
  private void printSortedIngredients() {
    System.out.println(foodStorage.sortedList());
  }

  /**
   * Have to check exception and test negative!!
   */
  private void expireBefore() {
    System.out.println("Enter expiration date in format yyyy-mm-dd: ");
    String expirationDate = lines.nextLine();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    try {
      LocalDate date = LocalDate.parse(expirationDate, formatter);
      if (foodStorage.expireBefore(date).isEmpty()) {
        System.out.println("No expired ingredients found.");
      } else {
        System.out.println(
            "These products will expire before " + date + ":\n" + foodStorage.expireBefore(date)
                + "\nTotal price: " +
                foodStorage.totalPriceExpiration(date) + " kr.");
      }
    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  /**
   *
   */
  private void totalPrice() {
    System.out.println("Total price of ingredients: " + foodStorage.totalPrice() + " kr.");
  }

  /**
   *
   */
  private void registerRecipe() {
    System.out.println("Enter recipe name:");
    String name = lines.nextLine();
    System.out.println("Enter recipe description:");
    String description = lines.nextLine();
    System.out.println("Enter recipe instructions:");
    String instructions = lines.nextLine();
    System.out.println("Enter amount of portions:");
    int portions = numbers.nextInt();
    List<recipeIngredient> ingredients = new ArrayList<>();
    try {
      while (true) {
        System.out.println("Enter 1. to add ingredient or 2. to stop adding");
        int choice = numbers.nextInt();
        if (choice == 2) {
          break;
        }
        System.out.println("Enter ingredient name:");
        String ingredientName = lines.nextLine();
        System.out.println("Enter ingredient amount:");
        double ingredientAmount = numbers.nextDouble();
        System.out.println("Enter ingredient unit of measurement:");
        String ingredientUnit = lines.nextLine();
        recipeIngredient ingredient = new recipeIngredient(ingredientName, ingredientAmount,
            ingredientUnit);
        ingredients.add(ingredient);
      }
      recipe recipe = new recipe(name, description, instructions, ingredients, portions);
      cookBook.addRecipe(recipe);
      System.out.println(recipe);
    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  /**
   *
   */
  private void checkRecipe() {
    System.out.println("Enter recipe name:");
    String name = lines.nextLine();
    try {
      foodStorage.checkRecipe(cookBook.findRecipeByName(name));
    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  /**
   * Prints all recipes in {@link cookBook}.
   *
   * <p>This method checks to see if there are any recipes found in {@link cookBook}. If there are
   * recipes they will be printed out.</p>
   */
  private void printRecipe() {
    if (cookBook.getRecipes().isEmpty()) {
      System.out.println("No recipes found");
    }
    System.out.println(cookBook.getRecipes());
  }


  /**
   * Suggests recipes based on the ingredients available in the food storage.
   *
   * <p>This method iterates through all recipes in the {@link cookBook}, checks if
   * the necessary ingredients are available in sufficient quantities in the {@link foodStorage},
   * and suggests recipes that can be made or nearly made.</p>
   *
   * @throws IllegalArgumentException if an error occurs during recipe lookup.
   */
  private void suggestRecipe() {
    List<recipe> suggestedRecipes = new ArrayList<>();

    for (recipe recipe : cookBook.getRecipes()) {
      List<recipeIngredient> missingIngredients = foodStorage.calculateMissingIngredients(recipe);

      if (missingIngredients.isEmpty()) {
        suggestedRecipes.add(recipe);
      }
    }
    if (suggestedRecipes.isEmpty()) {
      System.out.println("No suggested recipes found.");
    } else {
      System.out.println("Suggested recipes:");
      for (recipe r : suggestedRecipes) {
        System.out.println(r + "\n");
      }
    }
  }


  /**
   *
   */
  private void setDefaultIngredients() {
    List<ingredient> defaultIngredients = defaultData.getDefaultIngredients();
    for (ingredient i : defaultIngredients) {
      foodStorage.registerIngredient(i);
    }
    System.out.println("Defaulting ingredient has been added. ");
  }

  private void setDefaultRecipes() {
    List<recipe> defaultRecipes = defaultData.getDefaultRecipes();
    for (recipe r : defaultRecipes) {
      cookBook.addRecipe(r);
    }
    System.out.println("Default recipes have been added.");
  }


}
