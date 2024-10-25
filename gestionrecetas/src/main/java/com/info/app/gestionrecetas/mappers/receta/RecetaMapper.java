package com.info.app.gestionrecetas.mappers.receta;

import com.info.app.gestionrecetas.domain.Receta;
import com.info.app.gestionrecetas.dto.receta.RecetaCreateDto;
import com.info.app.gestionrecetas.dto.receta.RecetaCreatedDto;
import com.info.app.gestionrecetas.dto.receta.RecetaDto;

public interface RecetaMapper {

    Receta recetaDtoToReceta(RecetaDto recetaDto);

    RecetaDto recetaToRecetaDto(Receta receta);

    Receta recetaCreateDtoToReceta(RecetaCreateDto recetaCreateDto);

    RecetaCreatedDto recetaToRecetaCreatedDto(Receta receta);
}
