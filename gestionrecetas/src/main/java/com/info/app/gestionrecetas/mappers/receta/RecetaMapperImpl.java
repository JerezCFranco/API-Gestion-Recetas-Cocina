package com.info.app.gestionrecetas.mappers.receta;

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

    //private final CategoriaMapper categoriaMapper;

    private PasoMapper pasoMapper;

    @Override
    public Receta recetaDtoToReceta(RecetaDto recetaDto) {
        if(recetaDto == null){
            return null;
        }

        Receta receta = new Receta();
        receta.setId(recetaDto.id());
        receta.setNombre(recetaDto.nombre());
        receta.setDescripcion(recetaDto.descripcion());
        receta.setDificultad(recetaDto.dificultad());
        //receta.setCategoria(categoriaMapper.categoriaDtoToCategoria(recetaDto.categoria()));
        receta.setListaPasos(
                recetaDto.listaPasos() != null
                        ? recetaDto.listaPasos()
                        .stream()
                        .map(pasoDto -> pasoMapper.pasoDtoToPaso(pasoDto))
                        .toList()
                        : Collections.emptyList()
        );
        return receta;
    }

    @Override
    public RecetaDto recetaToRecetaDto(Receta receta) {
        return new RecetaDto(
                receta.getId(),
                receta.getNombre(),
                receta.getDescripcion(),
                receta.getDificultad(),
                null,//categoriaMapper.categoriaToCategoriaDto(receta.getCategoria()),
                receta.getListaPasos() != null
                        ? receta.getListaPasos()
                        .stream()
                        .map( paso -> pasoMapper.pasoToPasoDto(paso))
                        .toList()
                        : Collections.emptyList()
        );
    }

    @Override
    public Receta recetaCreateDtoToReceta(RecetaCreateDto recetaCreateDto) {

        Receta receta = new Receta();
        receta.setId(UUID.randomUUID());
        receta.setNombre(recetaCreateDto.nombre());
        //receta.setCategoria(categoriaMapper.categoriaDtoToCategoria(recetaCreateDto.categoria()));

        return receta;

    }

    @Override
    public RecetaCreatedDto recetaToRecetaCreatedDto(Receta receta) {

        return new RecetaCreatedDto(
                receta.getId(),
                receta.getNombre(),
                receta.getDescripcion(),
                receta.getDificultad(),
                null,//categoriaMapper.categoriaToCategoriaDto(receta.getCategoria()),
                receta.getListaPasos() != null
                        ? receta.getListaPasos()
                        .stream()
                        .map(paso -> pasoMapper.pasoToPasoDto(paso))
                        .toList()
                        : Collections.emptyList()
        );

    }
}
