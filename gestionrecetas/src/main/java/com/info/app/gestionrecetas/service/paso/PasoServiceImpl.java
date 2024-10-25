package com.info.app.gestionrecetas.service.paso;

import com.info.app.gestionrecetas.domain.Ingrediente;
import com.info.app.gestionrecetas.domain.Paso;
import com.info.app.gestionrecetas.domain.Receta;
import com.info.app.gestionrecetas.dto.paso.PasoDto;
import com.info.app.gestionrecetas.mappers.ingrediente.IngredienteMapper;
import com.info.app.gestionrecetas.mappers.paso.PasoMapper;
import com.info.app.gestionrecetas.repository.paso.PasoRepository;
import com.info.app.gestionrecetas.service.ingrediente.IngredienteService;
import com.info.app.gestionrecetas.service.receta.RecetaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PasoServiceImpl implements PasoService{

    private PasoMapper pasoMapper;

    private PasoRepository pasoRepository;

    private RecetaService recetaService;

    private IngredienteService ingredienteService;

    private IngredienteMapper ingredienteMapper;

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
}
