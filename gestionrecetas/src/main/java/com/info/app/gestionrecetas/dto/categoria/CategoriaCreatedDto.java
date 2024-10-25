package com.info.app.gestionrecetas.dto.categoria;

import java.util.UUID;

public record CategoriaCreatedDto(
        UUID id,
        String nombre
) {
}
