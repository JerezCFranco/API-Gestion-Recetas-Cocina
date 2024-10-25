package com.info.app.gestionrecetas.mappers.paso;

import com.info.app.gestionrecetas.domain.Ingrediente;
import com.info.app.gestionrecetas.domain.Paso;
import com.info.app.gestionrecetas.dto.paso.PasoDto;
import com.info.app.gestionrecetas.mappers.ingrediente.IngredienteMapper;
import com.info.app.gestionrecetas.service.ingrediente.IngredienteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PasoMapperImpl implements PasoMapper{

    private IngredienteMapper ingredienteMapper;

    private IngredienteService ingredienteService;

    @Override
    public Paso pasoDtoToPaso(PasoDto pasoDto) {

        Paso pasoCreate = new Paso();
        pasoCreate.setId(UUID.randomUUID());
        pasoCreate.setNombre(pasoDto.nombre());
        pasoCreate.setDescripcion(pasoDto.descripcion());
        pasoCreate.setTiempoEstimado(pasoDto.tiempoEstimado());

        List<Ingrediente> listaIngrediente = pasoDto.ingredientes()
                .stream()
                .map(ingredienteService::findOrCreateIngrediente)
                .collect(Collectors.toList());

        pasoCreate.setIngredientes(listaIngrediente);
        pasoCreate.setEsOpcional(pasoDto.esOpcional());

        return pasoCreate;
    }

    @Override
    public PasoDto pasoToPasoDto(Paso paso) {

        if(paso == null){
            return null;
        }

        return new PasoDto(
                paso.getNombre(),
                paso.getDescripcion(),
                paso.getTiempoEstimado(),
                paso.getIngredientes()
                        .stream()
                        .map(ingredienteMapper::ingredienteToIngredienteDto)
                        .collect(Collectors.toList()),
                paso.isEsOpcional(),
                paso.getReceta().getId()
        );
    }
}
