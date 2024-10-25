package com.info.app.gestionrecetas.service.receta;

import com.info.app.gestionrecetas.domain.Categoria;
import com.info.app.gestionrecetas.domain.Receta;
import com.info.app.gestionrecetas.dto.receta.RecetaCreateDto;
import com.info.app.gestionrecetas.dto.receta.RecetaCreatedDto;
import com.info.app.gestionrecetas.dto.receta.RecetaDto;
import com.info.app.gestionrecetas.mappers.receta.RecetaMapper;
import com.info.app.gestionrecetas.repository.receta.RecetaRepository;
import com.info.app.gestionrecetas.service.categoria.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RecetaServiceImpl implements RecetaService{

    private RecetaRepository recetaRepository;

    private RecetaMapper recetaMapper;

    private CategoriaService categoriaService;

    @Override
    public Receta getRecetaById(UUID uuid) {
        Optional<Receta> optionalReceta = recetaRepository.findById(uuid);

        if(optionalReceta.isPresent()){
            return optionalReceta.get();
        } else {
            throw new NoSuchElementException("Receta no encontrada");
        }
    }

    @Override
    public Optional<RecetaDto> getRecetaDtoById(UUID uuid) {

        Optional<Receta> optionalReceta = recetaRepository.findById(uuid);

        if(optionalReceta.isPresent()){
            return Optional.of(
                    recetaMapper.recetaToRecetaDto(optionalReceta.get())
            );
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<RecetaDto> getAllRecetas(String categoriaNombre) {

        if(StringUtils.hasText(categoriaNombre)){
            Optional<Categoria> categoriaOpt = categoriaService.findCategoriaByNombre(categoriaNombre);
            if(categoriaOpt.isPresent()){
                Categoria categoria = categoriaOpt.get();
                return recetaRepository.findBycategoriaLike(categoria)
                        .stream()
                        .map(receta -> recetaMapper.recetaToRecetaDto(receta))
                        .toList();
            }
        }else {
            return recetaRepository.findAll().stream()
                    .map( receta -> recetaMapper.recetaToRecetaDto(receta))
                    .toList();
        }

        return recetaRepository.findAll().stream()
                .map( receta -> recetaMapper.recetaToRecetaDto(receta))
                .toList();

    }

    @Override
    public Optional<RecetaCreatedDto> createReceta(RecetaCreateDto recetaCreateDto) {

        Categoria categoria = categoriaService.findOrCreateCategoria(recetaCreateDto.categoria());

        Receta nuevaReceta = recetaMapper.recetaCreateDtoToReceta(recetaCreateDto);

        nuevaReceta.setCategoria(categoria);

        return Optional.of(
                recetaMapper.recetaToRecetaCreatedDto(recetaRepository.save(nuevaReceta))
        );

    }

    @Override
    public boolean deleteReceta(UUID idReceta) {

        if(recetaRepository.existsById(idReceta)){

            recetaRepository.deleteById(idReceta);
            return true;
        }
        return false;
    }
}
