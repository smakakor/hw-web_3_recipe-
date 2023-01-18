package com.example.hwweb_3_recipe.service.impl;

import com.example.hwweb_3_recipe.service.FileRecipeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
@Service
public class FileRecipeServiceImpl implements FileRecipeService {
    @Value("${application.file.recipe}")
    private String pathFileRecipe;
    @Value("${name.file.recipe}")
    private String nameFileRecipe;

    @Override
    public void saveToFile(String json) {
        try {
            cleanDataFileRecipe();
            Files.writeString(Path.of(pathFileRecipe, nameFileRecipe), json);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public String readFromRecipeFile() {
        try {
            return Files.readString(Path.of(pathFileRecipe, nameFileRecipe));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void cleanDataFileRecipe() {
        try {
            Files.deleteIfExists(Path.of(pathFileRecipe, nameFileRecipe));
            Files.createFile(Path.of(pathFileRecipe, nameFileRecipe));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public File getDataFile() {
        return new File(pathFileRecipe + "/" + nameFileRecipe);
    }
}
