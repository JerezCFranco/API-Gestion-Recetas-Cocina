package com.info.app.gestionrecetas.mappers.ingrediente;

import com.info.app.gestionrecetas.domain.Ingrediente;
import com.info.app.gestionrecetas.dto.ingrediente.IngredienteDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class IngredienteMapperImpl implements IngredienteMapper{
    @Override
    public Ingrediente ingredienteDtoToIngrediente(IngredienteDto ingredienteDto) {
        Ingrediente createIngrediente = new Ingrediente();

        createIngrediente.setNombre(ingredienteDto.nombre());
        createIngrediente.setDescripcion(ingredienteDto.descripcion());

        return createIngrediente;

    }

    @Override
    public IngredienteDto ingredienteToIngredienteDto(Ingrediente ingrediente) {

        if(ingrediente == null){
            return null;
        }

        return new IngredienteDto(
                ingrediente.getNombre(),
                ingrediente.getDescripcion()
        );

    }
}
