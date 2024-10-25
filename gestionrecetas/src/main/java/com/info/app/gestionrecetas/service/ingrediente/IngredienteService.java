package com.info.app.gestionrecetas.service.ingrediente;

import com.info.app.gestionrecetas.domain.Ingrediente;
import com.info.app.gestionrecetas.dto.ingrediente.IngredienteDto;

public interface IngredienteService {

    Ingrediente findOrCreateIngrediente(IngredienteDto ingredienteDto);
}
