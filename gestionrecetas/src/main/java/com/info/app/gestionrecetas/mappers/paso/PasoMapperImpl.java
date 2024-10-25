package com.info.app.gestionrecetas.mappers.paso;

import com.info.app.gestionrecetas.domain.Paso;
import com.info.app.gestionrecetas.dto.paso.PasoDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class PasoMapperImpl implements PasoMapper{
    @Override
    public Paso pasoDtoToPaso(PasoDto pasoDto) {

        Paso pasoCreate = new Paso();
        pasoCreate.setId(UUID.randomUUID());
        pasoCreate.setDescripcion(pasoDto.descripcion());
        pasoCreate.setTiempoEstimado(pasoDto.tiempoEstimado());
        pasoCreate.setIngredientes(pasoDto.ingredientes());
        pasoCreate.setEsOpcional(pasoDto.esOpcional());

        return pasoCreate;
    }

    @Override
    public PasoDto pasoToPasoDto(Paso paso) {

        if(paso == null){
            return null;
        }

        return new PasoDto(
                paso.getDescripcion(),
                paso.getTiempoEstimado(),
                paso.getIngredientes(),
                paso.isEsOpcional(),
                paso.getReceta().getId()
        );
    }
}
