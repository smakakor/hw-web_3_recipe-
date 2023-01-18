package com.example.hwweb_3_recipe.service;

import java.io.File;

public interface FileRecipeService {
    void saveToFile(String json);

    String readFromRecipeFile();

    File getDataFile();
}
