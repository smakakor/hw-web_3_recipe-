package com.example.hwweb_3_recipe.service;

import com.example.hwweb_3_recipe.model.Ingredient;

import java.util.Map;

public interface IngredientService {
    void addIngredient(Ingredient ingredient);

    Ingredient getIngredient(int id);

    boolean deleteIngredient(int id);

    Ingredient putIngredient(int id, Ingredient ingredient);

    Map<Integer, Ingredient> getAllIngredient();
}
