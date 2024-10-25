package com.info.app.gestionrecetas.dto.receta;

import com.info.app.gestionrecetas.domain.enums.DificultadEnum;
import com.info.app.gestionrecetas.dto.categoria.CategoriaDto;
import com.info.app.gestionrecetas.dto.paso.PasoDto;

import java.util.List;
import java.util.UUID;

public record RecetaCreatedDto(
        UUID id,
        String nombre,
        String descripcion,
        DificultadEnum dificulad,
        CategoriaDto categoria,
        List<PasoDto> listaPasos
) {
}
