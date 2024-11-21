package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.List;

public class recipe {

  private final String name;
  private final String description;
  private final String howTo;
  private final List<ingredient> ingredients;
  private final int portions;

  public recipe(String name, String description, String howTo, List<ingredient> ingredients,
      int portions) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty");
    }
    if (description == null || description.trim().isEmpty()) {
      throw new IllegalArgumentException("Description cannot be null or empty");
    }
    if (howTo == null || howTo.trim().isEmpty()) {
      throw new IllegalArgumentException("HowTo cannot be null or empty");
    }
    if (ingredients == null || ingredients.isEmpty()) {
      throw new IllegalArgumentException("Ingredients cannot be null or empty");
    }
    if (portions < 1 || portions > 50) {
      throw new IllegalArgumentException("Portions must be between 1 and 50");
    }
    this.name = name;
    this.description = description;
    this.howTo = howTo;
    this.ingredients = new ArrayList<>(ingredients);
    this.portions = portions;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getHowTo() {
    return howTo;
  }

  public List<ingredient> getIngredients() {
    return ingredients;
  }

  public int getPortions() {
    return portions;
  }

  @Override
  public String toString() {
    return "Recipe [name=" + name + "\nDescription=" + description + "\nInstructions=" + howTo
        + "\nIngredients=" +
        ingredients + "\nPortions=" + portions + "]";
  }

}
