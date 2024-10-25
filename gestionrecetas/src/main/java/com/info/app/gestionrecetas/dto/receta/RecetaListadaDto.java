package com.info.app.gestionrecetas.dto.receta;

import com.info.app.gestionrecetas.domain.enums.DificultadEnum;

import java.util.UUID;

public record RecetaListadaDto(
        UUID id,
        String nombre,
        String descripcion,
        DificultadEnum dificultad,
        int tiempoTotalPreparacion
) {
}
