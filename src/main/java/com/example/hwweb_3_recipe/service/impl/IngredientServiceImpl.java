package com.example.hwweb_3_recipe.service.impl;


import com.example.hwweb_3_recipe.model.Ingredient;
import com.example.hwweb_3_recipe.service.IngredientService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final Map<Integer, Ingredient> amountIngredient = new TreeMap<>();
    private static int counter = 0;

    @Override
    public void addIngredient(Ingredient ingredient) {
        amountIngredient.put(++counter, ingredient);
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
                return ingredient;
            }
        }
        return null;
    }

    @Override
    public Map<Integer, Ingredient> getAllIngredient() { // запршиваю всё
        return amountIngredient;
    }

}
