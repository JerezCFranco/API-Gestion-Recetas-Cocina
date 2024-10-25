package com.info.app.gestionrecetas.dto.ingrediente;

import java.util.UUID;

public record IngredienteFindDto(
        UUID id,
        String nombre
) {
}
