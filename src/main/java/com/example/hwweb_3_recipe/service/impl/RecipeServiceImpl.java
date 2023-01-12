package com.example.hwweb_3_recipe.service.impl;

import com.example.hwweb_3_recipe.model.Recipe;
import com.example.hwweb_3_recipe.service.RecipeService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final Map<Integer, Recipe> allRecipe = new TreeMap<>();
    private static int counter = 0;

    @Override
    public void addRecipe(Recipe recipe) {
        allRecipe.put(++counter, recipe);
    } // добаляем

    @Override
    public Recipe getRecipe(int id) { // запрашиваем
        for (Integer integer : allRecipe.keySet()) {
            Recipe recipe = allRecipe.get(id);
            if (integer != null) {
                return recipe;
            }
        }
        return null;
    }

    @Override
    public boolean deleteRecipe(int id) { // удаляю
        for (Integer integer : allRecipe.keySet()) {
            if (integer.equals(id)) {
                allRecipe.remove(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public Recipe putRecipe(int id, Recipe recipe) { // редактирую
        for (Integer integer : allRecipe.keySet()) {
            if (integer.equals(id)) {
                allRecipe.put(id, recipe);
                return recipe;
            }
        }
        return null;
    }

    @Override
    public Map<Integer, Recipe> getAllRecipe() { // запршиваю всё
        return allRecipe;
    }
}
