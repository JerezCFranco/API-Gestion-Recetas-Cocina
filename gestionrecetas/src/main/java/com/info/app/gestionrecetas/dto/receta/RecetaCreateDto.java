package com.info.app.gestionrecetas.dto.receta;

import com.info.app.gestionrecetas.domain.enums.DificultadEnum;
import com.info.app.gestionrecetas.dto.categoria.CategoriaDto;
import com.info.app.gestionrecetas.dto.paso.PasoDto;

import java.util.List;

public record RecetaCreateDto(
        String nombre,
        String descripcion,
        DificultadEnum dificultad,
        CategoriaDto categoria,
        List<PasoDto> listaPasos
) {
}
