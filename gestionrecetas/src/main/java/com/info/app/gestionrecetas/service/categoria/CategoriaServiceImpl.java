package com.info.app.gestionrecetas.service.categoria;

import com.info.app.gestionrecetas.dto.categoria.CategoriaDto;
import com.info.app.gestionrecetas.mappers.categoria.CategoriaMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoriaServiceImpl implements CategoriaService{

    private CategoriaMapper categoriaMapper;

    @Override
    public CategoriaDto createCategoria(CategoriaDto categoriaDto) {

        return null;

    }
}
