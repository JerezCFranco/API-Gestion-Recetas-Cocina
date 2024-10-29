package com.info.app.gestionrecetas.service.receta;

import com.info.app.gestionrecetas.domain.Categoria;
import com.info.app.gestionrecetas.domain.Paso;
import com.info.app.gestionrecetas.domain.Receta;
import com.info.app.gestionrecetas.dto.ingrediente.IngredienteDto;
import com.info.app.gestionrecetas.dto.receta.RecetaCreateDto;
import com.info.app.gestionrecetas.dto.receta.RecetaCreatedDto;
import com.info.app.gestionrecetas.dto.receta.RecetaDto;
import com.info.app.gestionrecetas.mappers.ingrediente.IngredienteMapper;
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

    private IngredienteMapper ingredienteMapper;

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
    public List<RecetaDto> getAllRecetas(String ctgNombre) {

        if(StringUtils.hasText(ctgNombre)){
            Optional<Categoria> categoriaOpt = categoriaService.findCategoriaByNombre(ctgNombre);
            if (categoriaOpt.isPresent()){
                return recetaRepository.findBycategoriaLike(categoriaOpt.get().getNombre())
                        .stream()
                        .map(recetaMapper::recetaToRecetaDto)
                        .toList();
            }
        }

        return recetaRepository.findAll()
                .stream()
                .map(recetaMapper::recetaToRecetaDto)
                .toList();
    }

    @Override
    public List<IngredienteDto> getIngredienteByRecetaYOPaso(UUID idReceta, UUID idPaso) {
        Receta receta = recetaRepository.findById(idReceta)
                .orElseThrow(() -> new NoSuchElementException("Receta no encontrada"));

        if (idPaso != null){
            Paso paso = receta.getListaPasos()
                    .stream()
                    .filter(p -> p.getId().equals(idPaso))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Paso no encontrado"));

            return paso.getIngredientes()
                    .stream()
                    .map(ingredienteMapper::ingredienteToIngredienteDto)
                    .toList();
        } else {
            return receta.getListaPasos()
                    .stream()
                    .flatMap(paso -> paso.getIngredientes().stream())
                    .distinct()
                    .map(ingredienteMapper::ingredienteToIngredienteDto)
                    .toList();
        }
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
