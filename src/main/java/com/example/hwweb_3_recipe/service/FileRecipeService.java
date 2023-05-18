package com.example.hwweb_3_recipe.service;

import java.io.File;
import java.nio.file.Path;

public interface FileRecipeService {
    void saveToFile(String json);

    String readFromRecipeFile();

    File getDataFile();

    Path createTempFile(String suffix);
}
