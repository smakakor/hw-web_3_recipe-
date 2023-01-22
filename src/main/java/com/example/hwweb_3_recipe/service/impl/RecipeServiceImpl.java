package com.example.hwweb_3_recipe.service.impl;

import com.example.hwweb_3_recipe.model.Recipe;
import com.example.hwweb_3_recipe.service.FileRecipeService;
import com.example.hwweb_3_recipe.service.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.TreeMap;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final FileRecipeService fileRecipeService;
    private  Map<Integer, Recipe> allRecipe = new TreeMap<>();
    private static int counter = 0;

    public RecipeServiceImpl(FileRecipeService fileRecipeService) {
        this.fileRecipeService = fileRecipeService;
    }

    @PostConstruct
    private void init() {
        readFromFileRecipe();
    }
    @Override
    public void addRecipe(Recipe recipe) {
        allRecipe.put(++counter, recipe);
        saveToFileRecipe();
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
                saveToFileRecipe();
                return recipe;
            }
        }
        return null;
    }

    @Override
    public Map<Integer, Recipe> getAllRecipe() { // запршиваю всё
        return allRecipe;
    }

    private void saveToFileRecipe() {
        try {
            String json = new ObjectMapper().writeValueAsString(allRecipe);
            fileRecipeService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFileRecipe() {
        String json = fileRecipeService.readFromRecipeFile();
        try {
            allRecipe = new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    @Override
    public Path createRecipesFile() throws IOException {
        Path path = fileRecipeService.createTempFile("recipes");
        for (Recipe recipe : allRecipe.values()) {
            try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                writer.append("Название рецепта: " + recipe.getNameRecipe() + '\n' + '\n' +
                        "Время приготовления: " + recipe.getTimeCooking() + '\n' + '\n' +
                        "Ингредиенты: " + '\n' + '\n' + recipe.ingredientsToString() + '\n' +
                        "Инструкция приготовления: " + '\n' + '\n' + recipe.stepsToString() + '\n');
            }
        }
        return path;
    }
}
