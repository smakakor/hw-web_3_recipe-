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
    public StringBuilder stepsToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stepsCooking.size(); i++)
            sb.append(stepsCooking.get(i) + '\n');
        return sb;
    }
    public StringBuilder ingredientsToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listIngredient.size(); i++)
            sb.append(listIngredient.get(i).toString() + '\n');
        return sb;
    }


}
