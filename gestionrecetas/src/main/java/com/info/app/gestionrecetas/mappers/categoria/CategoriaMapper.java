package com.info.app.gestionrecetas.mappers.categoria;

import com.info.app.gestionrecetas.domain.Categoria;
import com.info.app.gestionrecetas.dto.categoria.CategoriaCreatedDto;
import com.info.app.gestionrecetas.dto.categoria.CategoriaDto;

public interface CategoriaMapper {

    Categoria categoriaDtoToCategoria(CategoriaDto categoriaDto);

    CategoriaDto categoriaToCategoriaDto(Categoria categoria);

    CategoriaCreatedDto categoriaToCategoriaCreatedDto(Categoria categoria);
}
