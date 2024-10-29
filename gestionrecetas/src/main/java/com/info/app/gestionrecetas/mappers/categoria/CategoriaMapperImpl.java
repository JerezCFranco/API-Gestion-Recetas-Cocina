package com.info.app.gestionrecetas.mappers.categoria;

import com.info.app.gestionrecetas.domain.Categoria;
import com.info.app.gestionrecetas.dto.categoria.CategoriaCreatedDto;
import com.info.app.gestionrecetas.dto.categoria.CategoriaDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
@AllArgsConstructor
public class CategoriaMapperImpl implements CategoriaMapper{


    @Override
    public Categoria categoriaDtoToCategoria(CategoriaDto categoriaDto) {
        Categoria categoriaCreate = new Categoria();
        categoriaCreate.setId(UUID.randomUUID());
        categoriaCreate.setNombre(categoriaDto.nombre());

        return categoriaCreate;
    }


    @Override
    public CategoriaCreatedDto categoriaToCategoriaCreatedDto(Categoria categoria) {

        return new CategoriaCreatedDto(
                categoria.getId(),
                categoria.getNombre()
        );

    }
}
