package com.example.hwweb_3_recipe.service;

public interface FileRecipeService {
    void saveToFile(String json);

    String readFromRecipeFile();
}
