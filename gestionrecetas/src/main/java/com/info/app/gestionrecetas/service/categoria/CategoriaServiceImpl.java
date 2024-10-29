package com.info.app.gestionrecetas.service.categoria;

import com.info.app.gestionrecetas.domain.Categoria;
import com.info.app.gestionrecetas.dto.categoria.CategoriaDto;
import com.info.app.gestionrecetas.mappers.categoria.CategoriaMapper;
import com.info.app.gestionrecetas.repository.categoria.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoriaServiceImpl implements CategoriaService{

    private CategoriaMapper categoriaMapper;

    private CategoriaRepository categoriaRepository;
    

    @Override
    public Categoria findOrCreateCategoria(CategoriaDto categoriaDto) {
        Optional<Categoria> existCategoria = categoriaRepository.findByNombre(categoriaDto.nombre());

        if(existCategoria.isPresent()){
            return existCategoria.get();
        }else{
            Categoria createCategoria = categoriaMapper.categoriaDtoToCategoria(categoriaDto);
            return categoriaRepository.save(createCategoria);
        }
    }

    @Override
    public Optional<Categoria> findCategoriaByNombre(String categoriaNombre) {
        return categoriaRepository.findByNombre(categoriaNombre);
    }
}
