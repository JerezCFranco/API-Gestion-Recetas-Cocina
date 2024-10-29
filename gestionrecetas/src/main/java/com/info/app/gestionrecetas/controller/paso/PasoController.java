package com.info.app.gestionrecetas.controller.paso;

import com.info.app.gestionrecetas.domain.Paso;
import com.info.app.gestionrecetas.dto.paso.PasoDto;
import com.info.app.gestionrecetas.dto.paso.PasoUpdatedDto;
import com.info.app.gestionrecetas.mappers.paso.PasoMapper;
import com.info.app.gestionrecetas.service.paso.PasoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.UUID;

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

    @PatchMapping("/{idPaso}/receta/{idReceta}")
    public ResponseEntity<PasoDto> updatePaso(
            @PathVariable UUID idPaso,
            @PathVariable UUID idReceta,
            @RequestBody PasoUpdatedDto pasoUpdatedDto)
    {
        Optional<Paso> pasoOpt = pasoService.updatePaso(idPaso,idReceta,pasoUpdatedDto);

        if(pasoOpt.isPresent()){
            PasoDto pasoDto = pasoMapper.pasoToPasoDto(pasoOpt.get());
            return ResponseEntity.ok(pasoDto);
        }else{
            return ResponseEntity.notFound().build();
        }
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
