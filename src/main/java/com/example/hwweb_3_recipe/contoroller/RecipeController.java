package com.example.hwweb_3_recipe.contoroller;

import com.example.hwweb_3_recipe.model.Ingredient;
import com.example.hwweb_3_recipe.model.Recipe;
import com.example.hwweb_3_recipe.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description ="Эндпоинты для работы с рецепами")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    @Operation(summary = "Добавление рецепта")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Рецепт добален"),
            @ApiResponse(responseCode = "404",description = "Рецепт не добален")
    })
    public ResponseEntity<?> addRecipe(@RequestBody Recipe recipe) {
        if (StringUtils.isBlank(recipe.getNameRecipe())) {
            return ResponseEntity.badRequest().body("Название рецепта не может быть пустым");
        }
        recipeService.addRecipe(recipe);
        return ResponseEntity.ok(recipe);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получения рецепта", description = "Рецепт можно получить вписав его id")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Рецепт найден"),
            @ApiResponse(responseCode = "404",description = "Рецепт не найден")
    })
    public ResponseEntity<Recipe> getRecipe(@PathVariable int id) {
        Recipe recipe = recipeService.getRecipe(id);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование рецепта", description = "Рецепт можно отредоктировать указав его id и новый измененый рецепт")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Рецепт отредоктирован "),
            @ApiResponse(responseCode = "404",description = "Рецепт не удалось отредоктировать")
    })
    public ResponseEntity<?> putIngredient(@PathVariable int id, @RequestBody Recipe recipe) {
        if (StringUtils.isBlank(recipe.getNameRecipe())) {
            return ResponseEntity.badRequest().body("Название рецепта не может быть пустым");
        }
        return ResponseEntity.ok(recipe);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление рецепта", description = "Рецепт можно удалить вписав его id")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Рецепт удален"),
            @ApiResponse(responseCode = "404",description = "Рецепт не найден")
    })
    public ResponseEntity<Void> deleteRecipe(@PathVariable int id) {
        if (recipeService.deleteRecipe(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    @Operation(summary = "Получение всех рецептов", description = "Получаем весь список рецептов, которые были добавлены")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Полученны все рецепты"),

    })
    public Map<Integer, Recipe> getAllIngredient() {
        return recipeService.getAllRecipe();
    }
}
