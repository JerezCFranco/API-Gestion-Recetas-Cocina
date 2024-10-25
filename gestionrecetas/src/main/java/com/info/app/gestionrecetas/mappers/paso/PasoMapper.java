package com.info.app.gestionrecetas.mappers.paso;

import com.info.app.gestionrecetas.domain.Paso;
import com.info.app.gestionrecetas.dto.paso.PasoDto;

public interface PasoMapper {

    Paso pasoDtoToPaso(PasoDto pasoDto);

    PasoDto pasoToPasoDto(Paso paso);
}
