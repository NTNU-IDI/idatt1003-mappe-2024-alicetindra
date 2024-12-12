package edu.ntnu.idi.idatt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents the User Interface for the program. Handles all user interactions such as
 * displaying menus, receiving input, setting up default data and delegating tasks to other
 * classes.
 *
 * <p>This class includes methods for registering ingredients as well as recipes, checking for
 * ingredients in food storage, remove ingredients from food storage, checking for expired
 * ingredients before a certain date, calculating total price of ingredients, checking if food
 * storage has enough ingredients for a recipe and suggesting a recipe according to the ingredients
 * in food storage.</p>
 *
 * @author Tindra Lindgren
 * @version 3.0
 * @since 0.2
 */
public class UserInterface {

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

  public FoodStorage foodStorage;
  public CookBook cookBook;

  private Scanner lines;
  private Scanner numbers;


  /**
   * Initializes the application by initializing instances of foodStorage and cookBook, sets up the
   * input scanners, and populates default ingredients and recipes.
   */
  public void init() {

    foodStorage = new FoodStorage();
    cookBook = new CookBook();

    lines = new Scanner(System.in);
    numbers = new Scanner(System.in);

    setDefaultIngredients();
    setDefaultRecipes();
  }

  /**
   * Retrieves and registers the default ingredients from {@link DefaultData} and adds each
   * ingredient to {@link FoodStorage}.
   */
  private void setDefaultIngredients() {
    List<Ingredient> defaultIngredients = DefaultData.getDefaultIngredients();
    for (Ingredient i : defaultIngredients) {
      foodStorage.registerIngredient(i);
    }
    System.out.println("Defaulting ingredient has been added. ");
  }

  /**
   * Retrieves and register the default recipes from {@link DefaultData} and adds ech recipes to
   * {@link CookBook}.
   */
  private void setDefaultRecipes() {
    List<Recipe> defaultRecipes = DefaultData.getDefaultRecipes();
    for (Recipe r : defaultRecipes) {
      cookBook.registerRecipe(r);
    }
    System.out.println("Default recipes have been added.");
  }

  /**
   * Starts the application. Displays menu, retrieving the selected menu choice and executes the
   * selected method for the choice.
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
   * Displays the menu options to the user and asks user to input a number that represents an action
   * in the menu.
   */
  private void displayMenu() {
    System.out.println("""
        Menu:
        1. Register new ingredient.
        2. Search for an ingredient in food storage.
        3. Remove an amount of an ingredient from food storage.
        4. Print out overview of ingredients in food storage.
        5. Print out ingredients that expire, + total price of expiring products.
        6. Calculate total price of groceries.
        7. Register a recipe.
        8. Search food storage to check if you have enough ingredients for a recipe.
        9. Print out all recipes.
        10. Give suggestions for recipe based on items in food storage.
        0. Exit program.
        Please enter a number between 0 and 10.
        """);
  }

  /**
   * Handles the input from the user and executes the selected functionality.
   *
   * @param choice is an integer representing the rmenu option selected by the user.
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
      default -> System.out.println("Invalid choice. Choose number between 0-10.");
    }
  }

  /**
   * Registers a new ingredient by inquiring the user for different parameters. If there is a
   * problem registering the ingredient, the user is prompted the reason why the problem occurred.
   */
  private void registerIngredient() {
    System.out.println("Enter grocery name: ");
    String name = lines.nextLine();

    System.out.println("Enter grocery amount: ");
    double amount = numbers.nextDouble();

    System.out.println("Enter unit of measurement (litres/ grams/ pieces): ");
    String measureUnit = lines.nextLine();

    System.out.println("Enter expiration date in format yyyy-mm-dd: ");
    String expirationDate = lines.nextLine();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate date;

    try {
      date = LocalDate.parse(expirationDate, formatter);
    } catch (DateTimeParseException e) {
      System.out.println("Invalid date format. Try again with: yyyy-mm-dd.");
      return;
    }
    System.out.println("Enter price of grocery: ");
    double price = numbers.nextDouble();
    try {
      Ingredient ingredient = new Ingredient(name, amount, measureUnit,
          date, price);
      System.out.println(ingredient);
      foodStorage.registerIngredient(ingredient);
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid input: " + e.getMessage());
    }
  }

  /**
   * Searches for an ingredient by name and displays the result. If input is invalid the user is
   * prompted the reason why.
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
   * Removes a specific amount of an ingredient by name.
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
   * Prints out a list of all ingredients in {@link FoodStorage} in alphabetical order.
   */
  private void printSortedIngredients() {
    System.out.println(foodStorage.sortedList());
  }

  /**
   * Prints out a list of ingredient that expire before a specified date, along with the total price
   * for these expired products.
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
                + "\nTotal price: "
                + foodStorage.totalPriceExpiration(date)
                + " kr.\nRemember that a lot of products are still good after it's expiration date."
                + "\n Smell and try the product before throwing it out.");
      }
    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    } catch (DateTimeParseException e) {
      System.out.println("Invalid date format. Try again with: yyyy-mm-dd.");
    }
  }

  /**
   * Prints out the total price for all ingredients.
   */
  private void totalPrice() {
    System.out.println("Total price of ingredients: " + foodStorage.totalPrice() + " kr.");
  }

  /**
   * Registers a new recipe by inquiring the user for different parameters and adds it to
   * {@link CookBook}. If there is a problem registering the recipe, the user is prompted the reason
   * why the problem occurred.
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
    List<RecipeIngredient> ingredients = new ArrayList<>();
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
        System.out.println("Enter ingredient unit of measurement (litres/ grams/ pieces):");
        String ingredientUnit = lines.nextLine();
        RecipeIngredient ingredient = new RecipeIngredient(ingredientName, ingredientAmount,
            ingredientUnit);
        ingredients.add(ingredient);
      }
      Recipe recipe = new Recipe(name, description, instructions, ingredients, portions);
      cookBook.registerRecipe(recipe);
      System.out.println(recipe);
    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  /**
   * Checks a recipe is there is enough ingredients in {@link FoodStorage}.
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
   * Prints all recipes in {@link CookBook}.
   */
  private void printRecipe() {
    try {
      System.out.println(cookBook.getRecipes());
    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }

  }


  /**
   * Suggests recipes based on the ingredients available in the food storage.
   */
  private void suggestRecipe() {
    List<Recipe> suggestedRecipes = new ArrayList<>();

    for (Recipe recipe : cookBook.getRecipes()) {
      List<RecipeIngredient> missingIngredients = foodStorage.calculateMissingIngredients(recipe);

      if (missingIngredients.isEmpty()) {
        suggestedRecipes.add(recipe);
      }
    }
    if (suggestedRecipes.isEmpty()) {
      System.out.println("No suggested recipes found.");
    } else {
      System.out.println("Suggested recipes:");
      for (Recipe r : suggestedRecipes) {
        System.out.println(r + "\n");
      }
    }
  }


}
