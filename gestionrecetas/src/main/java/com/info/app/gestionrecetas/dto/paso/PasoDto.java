package com.info.app.gestionrecetas.dto.paso;

import com.info.app.gestionrecetas.domain.Ingrediente;
import com.info.app.gestionrecetas.domain.Receta;

import java.util.List;
import java.util.UUID;

public record PasoDto(
        String descripcion,
        int tiempoEstimado,
        List<Ingrediente> ingredientes,
        boolean esOpcional,
        UUID idReceta) {}
