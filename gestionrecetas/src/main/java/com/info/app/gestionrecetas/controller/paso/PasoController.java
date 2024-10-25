package com.info.app.gestionrecetas.controller.paso;


import com.info.app.gestionrecetas.domain.Paso;
import com.info.app.gestionrecetas.dto.paso.PasoDto;
import com.info.app.gestionrecetas.mappers.paso.PasoMapper;
import com.info.app.gestionrecetas.service.paso.PasoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/paso")
@AllArgsConstructor
public class PasoController {

    private PasoService pasoService;

    private PasoMapper pasoMapper;

    @PostMapping()
    public ResponseEntity<?> createPaso(@RequestBody PasoDto paso){
        PasoDto pasoDto = pasoService.createPaso(paso);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pasoDto);
    }

    @PostMapping("/encontrar")
    public ResponseEntity<?> findOrCreatePaso(@RequestBody PasoDto pasoDto){

        Optional<Paso> pasoOpt = pasoService.findOrCreatePaso(pasoDto);

        return pasoOpt
                .map(paso -> ResponseEntity.ok(pasoMapper.pasoToPasoDto(paso)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(pasoDto));

    }
}
