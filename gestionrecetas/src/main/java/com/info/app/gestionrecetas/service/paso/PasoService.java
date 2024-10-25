package com.info.app.gestionrecetas.service.paso;

import com.info.app.gestionrecetas.domain.Paso;
import com.info.app.gestionrecetas.dto.paso.PasoDto;

import java.util.Optional;

public interface PasoService {

    PasoDto createPaso(PasoDto paso);

    Optional<Paso> findOrCreatePaso(PasoDto pasoDto);
}
