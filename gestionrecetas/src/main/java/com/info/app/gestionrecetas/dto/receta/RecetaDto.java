package com.info.app.gestionrecetas.dto.receta;

import com.info.app.gestionrecetas.domain.enums.DificultadEnum;
import com.info.app.gestionrecetas.dto.categoria.CategoriaCreatedDto;
import com.info.app.gestionrecetas.dto.categoria.CategoriaDto;
import com.info.app.gestionrecetas.dto.paso.PasoCreatedDto;

import java.util.List;
import java.util.UUID;

public record RecetaDto(
        UUID id,
        String nombre,
        String descripcion,
        DificultadEnum dificultad,
        int tiempoTotal,
        CategoriaCreatedDto categoria,
        List<PasoCreatedDto> listaPasos
) {
}
