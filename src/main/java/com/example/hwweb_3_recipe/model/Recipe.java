package com.example.hwweb_3_recipe.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Recipe {
    private final String nameRecipe;
    private final int timeCooking;
    private final ArrayList<Ingredient> listIngredient = new ArrayList<>();
    private final ArrayList<String> stepsCooking = new ArrayList<>(); // шаги приготовления


}
