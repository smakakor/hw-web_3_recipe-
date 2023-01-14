package com.example.hwweb_3_recipe.contoroller;

import com.example.hwweb_3_recipe.model.Ingredient;
import com.example.hwweb_3_recipe.service.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ingredient")
@Tag(name = "Ингредиенты", description ="Эндпоинты для работы с ингредиентами")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    @Operation(summary = "Добавление ингредиента")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Ингредиент добален"),
            @ApiResponse(responseCode = "404",description = "Ингредиент не добален")
    })
    public ResponseEntity<Ingredient> addIngredient(@RequestBody Ingredient ingredient) {
        ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok(ingredient);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получения ингредиента", description = "Ингредиент можно получить вписав его id")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Ингредиент найден"),
            @ApiResponse(responseCode = "404",description = "Ингредиент не найден")
    })
    public ResponseEntity<Ingredient> getIngredient(@PathVariable int id) {
        Ingredient ingredient = ingredientService.getIngredient(id);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование ингредиента", description = "Ингредиент можно отредоктировать указав его id и новый измененый ингредиент")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Ингредиент отредоктирован "),
            @ApiResponse(responseCode = "404",description = "Ингредиент не удалось отредоктировать")
    })
    public ResponseEntity<Ingredient> putIngredient(@PathVariable int id, @RequestBody Ingredient ingredient) {
        Ingredient ingredientVariable = ingredientService.putIngredient(id, ingredient);
        if (ingredientVariable == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление ингредиента", description = "Ингредиент можно удалить вписав его id")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Ингредиент удален"),
            @ApiResponse(responseCode = "404",description = "Ингредиент не найден")
    })
    public ResponseEntity<Void> deleteIngredient(@PathVariable int id) {
        if (ingredientService.deleteIngredient(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    @Operation(summary = "Получение всех ингредиентов", description = "Получаем весь список ингредиентов, которые были добавлены")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Полученны все нгредиенты"),

    })
    public Map<Integer, Ingredient> getAllIngredient() {
        return ingredientService.getAllIngredient();
    }

}
