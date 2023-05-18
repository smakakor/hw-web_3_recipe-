package com.example.hwweb_3_recipe.service;

import java.io.File;

public interface FileIngredientService {
    void saveToFile(String json);

    String readFromIngredientFile();

    File getDataFile();
}
