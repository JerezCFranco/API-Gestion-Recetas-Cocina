package com.info.app.gestionrecetas.dto.categoria;

import com.info.app.gestionrecetas.domain.Receta;
import com.info.app.gestionrecetas.dto.receta.RecetaDto;

import java.util.List;
import java.util.UUID;

public record CategoriaDto(
        String nombre
        //List<RecetaDto> recetas
) {
}
