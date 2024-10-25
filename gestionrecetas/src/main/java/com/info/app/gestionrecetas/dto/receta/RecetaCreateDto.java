package com.info.app.gestionrecetas.dto.receta;

import com.info.app.gestionrecetas.dto.categoria.CategoriaDto;
import com.info.app.gestionrecetas.dto.paso.PasoDto;

import java.util.List;

public record RecetaCreateDto(
        String nombre,
        CategoriaDto categoria,
        List<PasoDto> listaPasos
) {
}
