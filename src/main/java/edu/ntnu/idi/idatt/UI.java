package edu.ntnu.idi.idatt;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {

  public foodStorage foodStorage;
  public recipe recipe;
  public cookBook cookBook;

  private Scanner lines;
  private Scanner numbers;


  public void init() {

    foodStorage = new foodStorage();
    cookBook = new cookBook();

    lines = new Scanner(System.in);
    numbers = new Scanner(System.in);
    setDefaultIngredients();
    setDefaultRecipe();
  }

  public void start() {
    while (true) {
      try {
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
            9. Print out all recipes
            10. Give suggestions for recipe based on items in food storage
            11. Exit program
            """);
        int choice = numbers.nextInt();

        if (choice == 11) {
          System.out.println("Exiting program");
          break;
        }
        switch (choice) {
          case 1:
            registerIngredient();
            break;
          case 2:
            searchIngredient();
            break;
          case 3:
            removeIngredient();
            break;
          case 4:
            printSortedIngredients();
            break;
          case 5:
            expireBefore();
            break;
          case 6:
            totalPrice();
            break;
          case 7:
            registerRecipe();
            break;
          case 8:
            checkRecipe();
            break;
          case 9:
            printRecipe();
            break;
          case 10:
            System.out.println("Give suggestions for recipe based on items in food storage");
            break;
          default:
            System.out.println("Invalid choice");
        }
      } catch (Exception e) {
        System.out.println("Invalid input. Please try again:)");
        numbers.nextLine();
      }
    }
  }

  /**
   * Method: Set default ingredients
   */
  private void setDefaultIngredients() {
    List<ingredient> defaultIngredients = List.of(
        new ingredient("milk", 2, "litres",
            LocalDate.of(2024, 12, 30), 35.50),
        new ingredient("yoghurt", 1.5, "litres",
            LocalDate.of(2024, 12, 30), 28.90),
        new ingredient("bread", 1, "pc",
            LocalDate.of(2025, 1, 15), 18.90),
        new ingredient("frozen berries", 500, "grams",
            LocalDate.of(2025, 5, 10), 42.0),
        new ingredient("honey", 250, "grams",
            LocalDate.of(2030, 1, 20), 89.90),
        new ingredient("chia seeds", 500, "grams",
            LocalDate.of(2025, 5, 10), 80),
        new ingredient("oats", 1000, "grams",
            LocalDate.of(2025, 2, 11), 25),
        new ingredient("peanut butter", 400, "grams",
            LocalDate.of(2025, 8, 12), 65.6),
        new ingredient("egg", 12, "pc",
            LocalDate.of(2025, 2, 24), 46.0),
        new ingredient("flour", 1000, "grams",
            LocalDate.of(2025, 3, 18), 38.9),
        new ingredient("ground beef", 800, "grams",
            LocalDate.of(2025, 1, 10), 70.0));

    for (ingredient i : defaultIngredients) {
      foodStorage.registerIngredient(i);
    }
    System.out.println("Defaulting ingredient has been added. ");
  }

  /**
   * Method: Set default recipes
   */
  private void setDefaultRecipe() {
    cookBook.addRecipe(createOats());
    cookBook.addRecipe(createBananaPancake());
    cookBook.addRecipe(createEggs());
    cookBook.addRecipe(createRedCurry());
    cookBook.addRecipe(createBolognese());
    cookBook.addRecipe(createSpinachStew());
  }

  /**
   * Method: create default ingredient
   *
   * @return returns created overnight oats recipe
   */
  private recipe createOats() {
    List<recipeIngredient> oats = List.of(new recipeIngredient("oats", 35, "grams"),
        new recipeIngredient("milk", 0.1, "litres"),
        new recipeIngredient("yoghurt", 0.1, "litres"),
        new recipeIngredient("honey", 20, "grams"),
        new recipeIngredient("chia seeds", 15, "grams"),
        new recipeIngredient("frozen berries", 40, "grams"),
        new recipeIngredient("peanut butter", 30, "grams"));
    return new recipe("Overnight oats", "A filing and refreshing breakfast.",
        """
            1. Mix honey, oats and chia seeds together.
            2. Add milk and yoghurt and mix well.
            3. Add frozen berries and leave mixture in fridge overnight.
            4. In the morning add peanut butter and enjoy:)""",
        oats, 1);
  }

  /**
   * Method: create default ingredient
   *
   * @return returns created Banana pancake recipe
   */
  private recipe createBananaPancake() {
    List<recipeIngredient> bananaPancakes = List.of(new recipeIngredient("banana", 1, "pc"),
        new recipeIngredient("egg", 2, "pc"),
        new recipeIngredient("peanut butter", 30, "grams"),
        new recipeIngredient("butter", 10, "grams"));
    return new recipe("Banana pancake", "Easy and quick breakfast",
        """
            1. Mash banana and mix with the eggs.
            2. Heat butter on middle heat in a frying pan and add mixture.
            3. Flip pancake after 5-10 minutes.
            4. Enjoy with peanut butter:)""", bananaPancakes, 1);
  }

  /**
   * Method: create default ingredient
   *
   * @return returns created scrambles eggs recipe
   */
  private recipe createEggs() {
    List<recipeIngredient> scrambledEggs = List.of(new recipeIngredient("egg", 3, "pc"),
        new recipeIngredient("cream", 0.1, "litres"),
        new recipeIngredient("salt", 5, "grams"),
        new recipeIngredient("pepper", 5, "grams"),
        new recipeIngredient("butter", 10, "grams"));
    return new recipe("Scrambled eggs",
        "High protein breakfast on its own or on a piece of toast.",
        """
            1. Mix all the ingredients well with a fork or whisk.
            2. Turn frying pan on low to medium heat.
            3. Pour in egg mixture and stir with a wooden spoon until the consistency is close to what you want.
            4. Take pan off the heat and continue stirring.""", scrambledEggs, 2);
  }

  /**
   * Method: create default ingredient
   *
   * @return returns created red curry chicken recipe
   */
  private recipe createRedCurry() {
    List<recipeIngredient> redCurryChicken = List.of(new recipeIngredient("chicken", 600, "grams"),
        new recipeIngredient("yellow onion", 1, "pc"),
        new recipeIngredient("garlic", 3, "pc"),
        new recipeIngredient("bell pepper", 0.5, "pc"),
        new recipeIngredient("cooking oil", 10, "grams"),
        new recipeIngredient("coconut milk", 0.4, "litres"),
        new recipeIngredient("red curry paste", 50, "grams"),
        new recipeIngredient("water", 0.2, "litres"),
        new recipeIngredient("rice", 340, "grams"));
    return new recipe("Red curry chicken", "A classic thai curry served with rice",
        """
            1. Cut the chicken in small pieces.
            2. Peal and finely chop onion and garlic as well as shred the bell pepper
            3. Fry the chicken in cooking oil in a frying pan
            4. Fry the garlic with the red curry paste. Add onion, bell pepper, coconut milk and water
            5. Add the chicken and let it simmer, 5-10 minutes.
            6. Cook the rice according to the package and serve""", redCurryChicken, 4);
  }

  /**
   * Method: create default ingredient
   *
   * @return returns created bolognese recipe
   */
  private recipe createBolognese() {
    List<recipeIngredient> bolognese = List.of(new recipeIngredient("minced meat", 500, "grams"),
        new recipeIngredient("tomato sauce", 0.5, "litres"),
        new recipeIngredient("yellow onion", 1, "pc"),
        new recipeIngredient("bell pepper", 0.5, "pc"),
        new recipeIngredient("carrot", 1, "pc"),
        new recipeIngredient("garlic", 2, "pc"),
        new recipeIngredient("pasta", 340, "grams"),
        new recipeIngredient("butter", 10, "grams"),
        new recipeIngredient("salt", 10, "grams"),
        new recipeIngredient("pepper", 10, "grams"));
    return new recipe("Pasta bolognese",
        "Hearty and comforting, meaty and easy bolognese sauce recipe",
        """
            1. Chop the onion, mince garlic and grate the carrot.
            2. Brown the minced meat in butter and minced garlic.
            3. Add the vegetables and tomato sauce.
            4. Let simmer, the longer it simmers the more taste will develop.
            5. Cook pasta according to package""", bolognese, 4);
  }

  /**
   * Method: create default ingredient
   *
   * @return returns created spinach stew recipe
   */
  private recipe createSpinachStew() {
    List<recipeIngredient> spinachStew = List.of(new recipeIngredient("spinach", 1000, "grams"),
        new recipeIngredient("minced meat", 500, "grams"),
        new recipeIngredient("yellow onion", 1, "pc"),
        new recipeIngredient("garlic", 4, "pc"),
        new recipeIngredient("bullion cube", 2, "pc"),
        new recipeIngredient("lemon", 1, "pc"),
        new recipeIngredient("salt", 10, "grams"),
        new recipeIngredient("pepper", 10, "grams"),
        new recipeIngredient("rice", 340, "grams"));
    return new recipe("Lebanese Spinach stew",
        "Sabenegh wo roz, which means spinach and rice, is an easy-to-make, fresh Lebanese spinach stew.",
        """
            1. Chop the spinach, onion and mince the garlic.
            2. Heat oil in a big pot and fry the onion until soft.
            3. Add the meat until colored, add garlic and the spinach and stir while frying.
            4. Crumble the bullion cubes and add salt and pepper to taste.
            5. If you have used frozen spinach bring to a boil, if you have used fresh spinach add a little water adn bring to a boil.
            6. Lower heat and let simmer for 20-30 minutes.
            7. Cook rice according to package instructions.
            8. As a final touch press lemon juice to taste and enjoy.""", spinachStew, 4);
  }

  /**
   * Method:
   */
  public void registerIngredient() {
    System.out.println("Enter grocery name: ");
    String name = lines.nextLine();
    System.out.println("Enter grocery amount: ");
    double amount = numbers.nextDouble();
    System.out.println("Enter unit of measurement: ");
    String measureUnit = lines.nextLine();
    System.out.println("Enter year of best before date: ");
    int year = numbers.nextInt();
    System.out.println("Enter month of best before date: ");
    int month = numbers.nextInt();
    System.out.println("Enter day of best before date: ");
    int day = numbers.nextInt();
    System.out.println("Enter price of grocery: ");
    double price = numbers.nextDouble();
    try {
      ingredient ingredient = new ingredient(name, amount, measureUnit,
          LocalDate.of(year, month, day), price);
      System.out.println(ingredient);
      foodStorage.registerIngredient(ingredient);
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid input: " + e.getMessage());
    }
  }


  /**
   *
   */
  public void searchIngredient() {
    System.out.println("Enter ingredient name: ");
    String name = lines.nextLine();
    try {
      System.out.println(foodStorage.getIngredient(name));
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid input: " + e.getMessage());
    }
  }

  /**
   *
   */
  public void removeIngredient() {
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
  public void printSortedIngredients() {
    System.out.println(foodStorage.sortedList());
  }

  /**
   * Have to check exception and test negative!!
   */
  public void expireBefore() {
    System.out.println("Enter expiration year: ");
    int year = numbers.nextInt();
    System.out.println("Enter expiration month: ");
    int month = numbers.nextInt();
    System.out.println("Enter expiration date: ");
    int day = numbers.nextInt();
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

  /**
   *
   */
  public void registerRecipe() {
    System.out.println("Enter recipe name:");
    String name = lines.nextLine();
    System.out.println("Enter recipe description:");
    String description = lines.nextLine();
    System.out.println("Enter recipe instructions:");
    String instructions = lines.nextLine();
    System.out.println("Enter amount of portions:");
    int portions = numbers.nextInt();
    List<recipeIngredient> ingredients = new ArrayList<>();
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
  }

  /**
   *
   */
  public void checkRecipe() {
    System.out.println("Enter recipe name:");
    String name = lines.nextLine();
    foodStorage.checkRecipe(cookBook.findRecipeByName(name));
  }

  /**
   *
   */
  public void printRecipe() {
    System.out.println(cookBook.printRecipes());
  }


}
