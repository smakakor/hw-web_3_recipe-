package com.example.hwweb_3_recipe.service.impl;

import com.example.hwweb_3_recipe.service.FileIngredientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileIngredientServiceImpl implements FileIngredientService {
    @Value("${application.file.ingredient}")
    private String pathFileIngredient;
    @Value("${name.file.ingredient}")
    private String nameFileIngredient;

    @Override
    public void saveToFile(String json) {
        try {
            cleanDataFileIngredient();
            Files.writeString(Path.of(pathFileIngredient, nameFileIngredient), json);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public String readFromIngredientFile() {
        try {
            return Files.readString(Path.of(pathFileIngredient, nameFileIngredient));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void cleanDataFileIngredient() {
        try {
            Files.deleteIfExists(Path.of(pathFileIngredient, nameFileIngredient));
            Files.createFile(Path.of(pathFileIngredient, nameFileIngredient));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public File getDataFile() {
        return new File(pathFileIngredient + "/" + nameFileIngredient);
    }

}
