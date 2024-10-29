package com.info.app.gestionrecetas.mappers.receta;

import com.info.app.gestionrecetas.domain.Paso;
import com.info.app.gestionrecetas.domain.Receta;
import com.info.app.gestionrecetas.dto.receta.RecetaCreateDto;
import com.info.app.gestionrecetas.dto.receta.RecetaCreatedDto;
import com.info.app.gestionrecetas.dto.receta.RecetaDto;
import com.info.app.gestionrecetas.mappers.categoria.CategoriaMapper;
import com.info.app.gestionrecetas.mappers.paso.PasoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.UUID;

@Component
@AllArgsConstructor
public class RecetaMapperImpl implements RecetaMapper{

    private final CategoriaMapper categoriaMapper;

    private PasoMapper pasoMapper;

    @Override
    public RecetaDto recetaToRecetaDto(Receta receta) {
        return new RecetaDto(
                receta.getId(),
                receta.getNombre(),
                receta.getDescripcion(),
                receta.getDificultad(),
                calcularTiempoTotal(receta),
                categoriaMapper.categoriaToCategoriaCreatedDto(receta.getCategoria()),
                receta.getListaPasos() != null
                        ? receta.getListaPasos()
                            .stream()
                            .map( paso -> pasoMapper.pasoToPasoCreatedDto(paso))
                            .toList()
                        : Collections.emptyList()
        );
    }

    @Override
    public Receta recetaCreateDtoToReceta(RecetaCreateDto recetaCreateDto) {

        Receta receta = new Receta();
        receta.setId(UUID.randomUUID());
        receta.setNombre(recetaCreateDto.nombre());
        receta.setDescripcion(recetaCreateDto.descripcion());
        receta.setDificultad(recetaCreateDto.dificultad());

        return receta;

    }

    @Override
    public RecetaCreatedDto recetaToRecetaCreatedDto(Receta receta) {

        return new RecetaCreatedDto(
                receta.getId(),
                receta.getNombre(),
                receta.getDescripcion(),
                receta.getDificultad(),
                receta.getCategoria() != null
                        ?categoriaMapper.categoriaToCategoriaCreatedDto(receta.getCategoria())
                        :null,
                receta.getListaPasos() != null
                        ? receta.getListaPasos()
                            .stream()
                            .map(paso -> pasoMapper.pasoToPasoCreatedDto(paso))
                            .toList()
                        : Collections.emptyList()
        );

    }

    private int calcularTiempoTotal(Receta receta){
        return receta.getListaPasos()
                .stream()
                .filter(paso -> !paso.isEsOpcional())
                .mapToInt(Paso::getTiempoEstimado)
                .sum();
    }
}
