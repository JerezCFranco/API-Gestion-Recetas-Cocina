package com.info.app.gestionrecetas.service.receta;

import com.info.app.gestionrecetas.domain.Receta;
import com.info.app.gestionrecetas.dto.receta.RecetaCreateDto;
import com.info.app.gestionrecetas.dto.receta.RecetaCreatedDto;
import com.info.app.gestionrecetas.dto.receta.RecetaDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecetaService {

    Receta getRecetaById(UUID uuid);

    Optional<RecetaDto> getRecetaDtoById(UUID uuid);

    List<RecetaDto> getAllRecetas();

    Optional<RecetaCreatedDto> createReceta(RecetaCreateDto recetaCreateDto);

    //Optional<> createReceta()
}
