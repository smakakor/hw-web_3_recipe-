package com.example.hwweb_3_recipe.service;

import com.example.hwweb_3_recipe.model.Recipe;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public interface RecipeService {
    void addRecipe(Recipe recipe);

    Recipe getRecipe(int id);

    boolean deleteRecipe(int id);

    Recipe putRecipe(int id, Recipe recipe);

    Map<Integer, Recipe> getAllRecipe();

    Path createRecipesFile() throws IOException;
}
