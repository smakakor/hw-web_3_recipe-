package com.example.hwweb_3_recipe.contoroller;

import com.example.hwweb_3_recipe.service.FileIngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/ingredient/file")
@Tag(name = "Файлы ингредиентов", description =  "Загрузить ингредиенты в виде файла")
public class IngredientFilesController {
    private final FileIngredientService fileIngredientService;

    public IngredientFilesController(FileIngredientService fileIngredientService) {
        this.fileIngredientService = fileIngredientService;
    }
    @Operation(
            summary = "Импортировать файла с ингредиентами",
            description = "в формате json"
    )
    @PostMapping(value = "/import",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadDataFile(@RequestParam MultipartFile file) {
        File dataFile = fileIngredientService.getDataFile();
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
