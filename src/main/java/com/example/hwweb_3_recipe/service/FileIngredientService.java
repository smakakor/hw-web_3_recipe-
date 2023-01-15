package com.example.hwweb_3_recipe.service;

public interface FileIngredientService {
    void saveToFile(String json);

    String readFromIngredientFile();
}
