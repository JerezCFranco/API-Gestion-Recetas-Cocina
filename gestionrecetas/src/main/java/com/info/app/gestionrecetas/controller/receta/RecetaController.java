package com.info.app.gestionrecetas.controller.receta;

import com.info.app.gestionrecetas.domain.Categoria;
import com.info.app.gestionrecetas.dto.receta.RecetaCreateDto;
import com.info.app.gestionrecetas.dto.receta.RecetaCreatedDto;
import com.info.app.gestionrecetas.dto.receta.RecetaDto;
import com.info.app.gestionrecetas.service.receta.RecetaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/receta")
@AllArgsConstructor
public class RecetaController {

    private RecetaService recetaService;

    @GetMapping()
    public List<RecetaDto> getAllRecetas(
            @RequestParam(required = false, name = "ctgNombre")String ctgNombre
    ){

        return recetaService.getAllRecetas(ctgNombre);
    }

    @GetMapping("/{idReceta}")
    public ResponseEntity<?> getRecetaById(@PathVariable("idReceta")UUID idReceta){
        Optional<RecetaDto> recetaDto = recetaService.getRecetaDtoById(idReceta);

        if(recetaDto.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new NoSuchElementException("La receta de ID" + idReceta + "no fue encontrada."));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(recetaDto.get());
    }

    @PostMapping()
    public ResponseEntity<?> createReceta(@RequestBody RecetaCreateDto recetaCreateDto){
        Optional<RecetaCreatedDto> recetaCreatedDto = recetaService.createReceta(recetaCreateDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(recetaCreatedDto.get());
    }

    @DeleteMapping("{idReceta}")
    public ResponseEntity<?> deleteReceta(@PathVariable UUID idReceta){

        boolean isRecetaDeleted = recetaService.deleteReceta(idReceta);

        if(isRecetaDeleted){
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }

}
