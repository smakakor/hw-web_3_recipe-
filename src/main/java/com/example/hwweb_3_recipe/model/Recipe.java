package com.example.hwweb_3_recipe.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    private String nameRecipe;
    private int timeCooking;
    private ArrayList<Ingredient> listIngredient = new ArrayList<>();
    private ArrayList<String> stepsCooking = new ArrayList<>(); // шаги приготовления


}
