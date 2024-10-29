package com.info.app.gestionrecetas.service.paso;

import com.info.app.gestionrecetas.domain.Paso;
import com.info.app.gestionrecetas.dto.ingrediente.IngredienteFindDto;
import com.info.app.gestionrecetas.dto.paso.PasoDto;
import com.info.app.gestionrecetas.dto.paso.PasoUpdatedDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PasoService {

    PasoDto createPaso(PasoDto paso);

    Optional<Paso> findOrCreatePaso(PasoDto pasoDto);

    Optional<Paso> updatePaso(UUID idPaso, UUID idReceta, PasoUpdatedDto pasoUpdatedDto);

}
