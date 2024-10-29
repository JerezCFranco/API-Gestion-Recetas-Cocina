package com.info.app.gestionrecetas.mappers.ingrediente;

import com.info.app.gestionrecetas.domain.Ingrediente;
import com.info.app.gestionrecetas.dto.ingrediente.IngredienteDto;
import com.info.app.gestionrecetas.dto.ingrediente.IngredienteFindDto;

public interface IngredienteMapper {

    Ingrediente ingredienteDtoToIngrediente(IngredienteDto ingredienteDto);

    IngredienteDto ingredienteToIngredienteDto(Ingrediente ingrediente);

    IngredienteFindDto ingredienteToIngredienteFindDto(Ingrediente ingrediente);

}
