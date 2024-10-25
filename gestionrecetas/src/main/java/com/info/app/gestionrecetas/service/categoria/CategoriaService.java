package com.info.app.gestionrecetas.service.categoria;

import com.info.app.gestionrecetas.dto.categoria.CategoriaDto;

public interface CategoriaService {

    CategoriaDto createCategoria(CategoriaDto categoriaDto);

    //CategoriaDto findOrCreateCategoria(String nombreCategoria);
}
