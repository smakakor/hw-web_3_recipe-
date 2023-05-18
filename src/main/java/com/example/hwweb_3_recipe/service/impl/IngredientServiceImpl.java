package com.example.hwweb_3_recipe.service.impl;


import com.example.hwweb_3_recipe.model.Ingredient;
import com.example.hwweb_3_recipe.model.Recipe;
import com.example.hwweb_3_recipe.service.FileIngredientService;
import com.example.hwweb_3_recipe.service.FileRecipeService;
import com.example.hwweb_3_recipe.service.IngredientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final FileIngredientService fileIngredientService;

    private Map<Integer, Ingredient> amountIngredient = new TreeMap<>();
    private static int counter = 0;

    public IngredientServiceImpl(FileIngredientService fileIngredientService) {
        this.fileIngredientService = fileIngredientService;
    }

    @PostConstruct
    private void init() {
        readFromFileIngredient();
    }


    @Override
    public void addIngredient(Ingredient ingredient) {
        amountIngredient.put(++counter, ingredient);
        saveToFileIngredient();
    } // добаляем

    @Override
    public Ingredient getIngredient(int id) { // запрашиваем
        for (Integer integer : amountIngredient.keySet()) {
            Ingredient ingredient = amountIngredient.get(id);
                if (integer != null) {
                    return ingredient;
                }
        }
        return null;
    }

    @Override
    public boolean deleteIngredient(int id) { // удаляю
        for (Integer integer : amountIngredient.keySet()) {
            if (integer.equals(id)) {
                amountIngredient.remove(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public Ingredient putIngredient(int id, Ingredient ingredient) { // редактирую
        for (Integer integer : amountIngredient.keySet()) {
            if (integer.equals(id)) {
                amountIngredient.put(id, ingredient);
                saveToFileIngredient();
                return ingredient;
            }
        }
        return null;
    }

    @Override
    public Map<Integer, Ingredient> getAllIngredient() { // запршиваю всё
        return amountIngredient;
    }
    private void saveToFileIngredient() {
        try {
            String json = new ObjectMapper().writeValueAsString(amountIngredient);
            fileIngredientService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFileIngredient() {
        String json = fileIngredientService.readFromIngredientFile();
        try {
            amountIngredient = new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
