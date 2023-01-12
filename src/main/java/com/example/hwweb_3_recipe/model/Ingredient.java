package com.example.hwweb_3_recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredient {
    private final String nameIngredient;
    private final int amountIngredient; // количество ингред.
    private final String measure; // единица измерения
}
