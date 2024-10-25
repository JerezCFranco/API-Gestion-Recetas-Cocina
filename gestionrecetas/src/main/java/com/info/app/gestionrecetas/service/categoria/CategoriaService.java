package com.info.app.gestionrecetas.service.categoria;

import com.info.app.gestionrecetas.domain.Categoria;
import com.info.app.gestionrecetas.dto.categoria.CategoriaDto;

import java.util.Optional;

public interface CategoriaService {

    CategoriaDto createCategoria(CategoriaDto categoriaDto);

    Categoria findByNombre(String nombre);

    Categoria findOrCreateCategoria(CategoriaDto categoriaDto);

    Optional<Categoria> findCategoriaByNombre(String categoriaNombre);
}
