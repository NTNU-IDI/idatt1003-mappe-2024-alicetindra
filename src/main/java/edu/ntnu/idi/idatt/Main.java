package edu.ntnu.idi.idatt;

/**
 * This class contains the main method which serves as the entry point to the application. It
 * initializes and starts the User Interface (UI).
 *
 * @author tindra
 * @version 3.0
 * @since 0.1
 */
public class Main {

  /**
   * Main method that runs the application.
   *
   * @param args are the arguments for the main method.
   */
  public static void main(String[] args) {
    UI ui = new UI();
    ui.init();
    ui.start();
  }
}