package com.info.app.gestionrecetas.dto.paso;

import com.info.app.gestionrecetas.dto.ingrediente.IngredienteDto;
import java.util.List;
import java.util.UUID;

public record PasoDto(
        String nombre,
        String descripcion,
        int tiempoEstimado,
        List<IngredienteDto> ingredientes,
        boolean esOpcional,
        UUID idReceta) {}
