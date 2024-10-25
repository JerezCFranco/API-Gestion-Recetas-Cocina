package com.info.app.gestionrecetas.service.paso;

import com.info.app.gestionrecetas.domain.Paso;
import com.info.app.gestionrecetas.domain.Receta;
import com.info.app.gestionrecetas.dto.paso.PasoDto;
import com.info.app.gestionrecetas.mappers.paso.PasoMapper;
import com.info.app.gestionrecetas.repository.paso.PasoRepository;
import com.info.app.gestionrecetas.service.receta.RecetaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PasoServiceImpl implements PasoService{

    private PasoMapper pasoMapper;

    private PasoRepository pasoRepository;

    private RecetaService recetaService;

    @Override
    public PasoDto createPaso(PasoDto pasoDto) {

        Receta receta = recetaService.getRecetaById( pasoDto.idReceta());

        Paso pasoCreated = pasoMapper.pasoDtoToPaso(pasoDto);

        pasoCreated.setReceta(receta);

        return pasoMapper.pasoToPasoDto(pasoRepository.save(pasoCreated));

    }
}
