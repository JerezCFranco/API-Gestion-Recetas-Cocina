package com.info.app.gestionrecetas.dto.paso;

import com.info.app.gestionrecetas.dto.ingrediente.IngredienteDto;

import java.util.List;

public record PasoUpdatedDto(
        String descripcion,
        int tiempoEstimado,
        List<IngredienteDto> ingredientes,
        boolean esOpcional
) {
}