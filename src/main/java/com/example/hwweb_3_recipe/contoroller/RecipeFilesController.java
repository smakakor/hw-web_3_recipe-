package com.example.hwweb_3_recipe.contoroller;

import com.example.hwweb_3_recipe.service.FileRecipeService;
import com.example.hwweb_3_recipe.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/recipe/file")
@Tag(name = "Файлы рецептов", description =  "Получить и загрузить рецепты в виде файла")
public class RecipeFilesController {
    private final FileRecipeService fileRecipeService;

    private final RecipeService recipeService;

    public RecipeFilesController(FileRecipeService fileRecipeService, RecipeService recipeService) {
        this.fileRecipeService = fileRecipeService;
        this.recipeService = recipeService;
    }

    @Operation(
            summary = "Скачать фаил рецептов",
            description = "в формате json"
    )
    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> downloadDataFileRecipe() throws FileNotFoundException {
        File file = fileRecipeService.getDataFile();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = \" RecipeLog.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Operation(
            summary = "Импортировать файл с рецептами",
            description = "в формате json"
    )
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadDataFile(@RequestParam MultipartFile file) {
        File dataFile = fileRecipeService.getDataFile();
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @Operation(
            summary = "Скачать все рецепты",
            description = "в файле формата .txt"
    )
    @GetMapping("/recipesInFile")
    public ResponseEntity<InputStreamResource> getRecipesInTextFile() {
        try {
            Path path = recipeService.createRecipesFile();
            if (Files.size(path) == 0) {
                return ResponseEntity.noContent().build();
            }
            InputStreamResource resource = new InputStreamResource(new FileInputStream(path.toFile()));
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .contentLength(Files.size(path))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipesFile.txt\"")
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
