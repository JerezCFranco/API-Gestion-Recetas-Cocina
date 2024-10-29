package com.info.app.gestionrecetas.service.paso;

import com.info.app.gestionrecetas.domain.Ingrediente;
import com.info.app.gestionrecetas.domain.Paso;
import com.info.app.gestionrecetas.domain.Receta;
import com.info.app.gestionrecetas.dto.paso.PasoDto;
import com.info.app.gestionrecetas.dto.paso.PasoUpdatedDto;
import com.info.app.gestionrecetas.mappers.paso.PasoMapper;
import com.info.app.gestionrecetas.repository.paso.PasoRepository;
import com.info.app.gestionrecetas.repository.receta.RecetaRepository;
import com.info.app.gestionrecetas.service.ingrediente.IngredienteService;
import com.info.app.gestionrecetas.service.receta.RecetaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class PasoServiceImpl implements PasoService{

    private PasoMapper pasoMapper;

    private PasoRepository pasoRepository;

    private RecetaService recetaService;

    private RecetaRepository recetaRepository;

    private IngredienteService ingredienteService;

    @Override
    public PasoDto createPaso(PasoDto pasoDto) {

        Receta receta = recetaService.getRecetaById( pasoDto.idReceta());

        Paso pasoCreated = pasoMapper.pasoDtoToPaso(pasoDto);

        pasoCreated.setReceta(receta);

        List<Ingrediente> listIngrediente = new ArrayList<>();

        pasoDto.ingredientes().forEach(ingredienteDto ->{
            Ingrediente ingrediente = ingredienteService.findOrCreateIngrediente(ingredienteDto);
            listIngrediente.add(ingrediente);
        });

        listIngrediente.forEach(pasoCreated::addIngrediente);

        return pasoMapper.pasoToPasoDto(pasoRepository.save(pasoCreated));

    }

    @Override
    public Optional<Paso> findOrCreatePaso(PasoDto pasoDto) {
        Optional<Paso> existPaso = pasoRepository.findByNombre(pasoDto.nombre());

        if(existPaso.isPresent()){
            return existPaso;
        }else{
            Paso createPaso = pasoMapper.pasoDtoToPaso(pasoDto);

            return Optional.of(pasoRepository.save(createPaso));
        }

    }

    @Override
    public Optional<Paso> updatePaso(UUID idPaso, UUID idReceta, PasoUpdatedDto pasoUpdatedDto) {

        Optional<Receta> recetaOpt = recetaRepository.findById(idReceta);

        if(recetaOpt.isPresent()){
            Receta receta = recetaOpt.get();
            Optional<Paso> pasoOpt = receta.getListaPasos()
                    .stream()
                    .filter(paso -> paso.getId().equals(idPaso))
                    .findFirst();

            if(pasoOpt.isPresent()){
                Paso paso = pasoOpt.get();
                paso.setDescripcion(pasoUpdatedDto.descripcion());
                paso.setTiempoEstimado(pasoUpdatedDto.tiempoEstimado());
                paso.setEsOpcional(pasoUpdatedDto.esOpcional());

                List<Ingrediente> nuevosIngredientes = pasoUpdatedDto.ingredientes()
                        .stream()
                        .map(ingredienteDto -> ingredienteService.findOrCreateIngrediente(ingredienteDto))
                        .toList();
                paso.getIngredientes().clear();
                paso.getIngredientes().addAll(nuevosIngredientes);

                return Optional.of(pasoRepository.save(paso));
            }

        }
        return Optional.empty();

    }

}
