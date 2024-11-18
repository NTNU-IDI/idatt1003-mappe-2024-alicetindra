package edu.ntnu.idi.idatt;

import java.time.LocalDate;
import java.util.Scanner;

public class UI {

  public foodStorage foodStorage;
  ;
  //declare scanner as instance variables
  private Scanner lines;
  private Scanner numbers;

  public void init() {

    foodStorage = new foodStorage();
    //scanner for user to write string as well as scanner for numbers.
    lines = new Scanner(System.in);
    numbers = new Scanner(System.in);
  }

  public void start() {
    while (true) {
      //menu for user.
      try { //try and catch if input is invalid
        System.out.println("""
            Menu:
            1. Register new ingredient
            2. Store ingredient in food storage
            3. Search for an ingredient in food storage
            4. Remove a certain amount of ingredient from food storage
            5. Print out overview of ingredients in food storage
            6. Print out expired ingredients and total price of expired products
            7. Calculate total price of ingredients
            8. Register a recipe
            9. Search food storage to check if you have enough ingredients for a recipe
            10. Store recipe in cook book
            11. Give suggestions for recipe based on items in food storage
            12. Exit program
            """);
        int choice = numbers.nextInt();

        //if user chooses 12. While loop is finished and program ends.
        if (choice == 12) {
          System.out.println("Exiting program");
          break;
        }
        switch (choice) {
          case 1:
            System.out.println("Enter grocery name: ");
            String name = lines.nextLine();
            System.out.println("Enter grocery amount: ");
            double amount = numbers.nextDouble();
            System.out.println("Enter measurement unit: ");
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
              //attempt to create an ingredient with user input
              ingredient ingredient = new ingredient(name, amount, measureUnit,
                  LocalDate.of(year, month, day), price);
              System.out.println(ingredient);
              foodStorage.registerIngredient(ingredient);
            } catch (IllegalArgumentException e) {
              //handle invalid input (e.g, negative number, invalid date or letters instead of numbers)
              System.out.println("Invalid input: " + e.getMessage());
            }
            break;
          case 2:
            System.out.println("Store ingredient in food storage: ");
            //if milk is already in system, search milk, add amount
            break;
          case 3:
            System.out.println("Search for an ingredient in food storage ");
            break;
          case 4:
            System.out.println("Remove a certain amount of ingredient from food storage");
            break;
          case 5:
            System.out.println("Print out overview of ingredients in food storage ");
            break;
          case 6:
            System.out.println("Print out expired ingredients and total price of expired products");
            break;
          case 7:
            System.out.println("Calculate total price of ingredients");
            break;
          case 8:
            System.out.println("Register a recipe ");
            break;
          case 9:
            System.out.println(
                "Search food storage to check if you have enough ingredients for a recipe");
            break;
          case 10:
            System.out.println("Store recipe in cook book");
            break;
          case 11:
            System.out.println("Give suggestions for recipe based on items in food storage");
            break;
          default:
            System.out.println("Invalid choice");
        }
      } catch (Exception e) {
        System.out.println("You have not entered a valid integer. Please try again:)");
        numbers.nextLine(); //skip user input
      }
    }


  }
}
