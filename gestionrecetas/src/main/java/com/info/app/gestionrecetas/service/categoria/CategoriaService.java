package com.info.app.gestionrecetas.service.categoria;

import com.info.app.gestionrecetas.domain.Categoria;
import com.info.app.gestionrecetas.dto.categoria.CategoriaDto;

public interface CategoriaService {

    CategoriaDto createCategoria(CategoriaDto categoriaDto);

    Categoria findOrCreateCategoria(CategoriaDto categoriaDto);
}
