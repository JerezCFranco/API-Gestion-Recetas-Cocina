package com.info.app.gestionrecetas.controller.paso;


import com.info.app.gestionrecetas.dto.paso.PasoDto;
import com.info.app.gestionrecetas.service.paso.PasoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/paso")
@AllArgsConstructor
public class PasoController {

    private PasoService pasoService;

    @PostMapping()
    public ResponseEntity<?> createPaso(@RequestBody PasoDto paso){
        PasoDto pasoDto = pasoService.createPaso(paso);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pasoDto);
    }
}
