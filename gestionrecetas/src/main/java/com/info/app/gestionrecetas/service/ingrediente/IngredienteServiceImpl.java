package com.info.app.gestionrecetas.service.ingrediente;

import com.info.app.gestionrecetas.domain.Ingrediente;
import com.info.app.gestionrecetas.dto.ingrediente.IngredienteDto;
import com.info.app.gestionrecetas.mappers.ingrediente.IngredienteMapper;
import com.info.app.gestionrecetas.repository.ingrediente.IngredienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class IngredienteServiceImpl implements IngredienteService{

    private final IngredienteRepository ingredienteRepository;

    private final IngredienteMapper ingredienteMapper;

    @Override
    public Ingrediente findOrCreateIngrediente(IngredienteDto ingredienteDto) {

        Optional<Ingrediente> existIngrediente = ingredienteRepository.findByNombre(ingredienteDto.nombre());

        if (existIngrediente.isPresent()) {
            return existIngrediente.get();
        } else {
            Ingrediente createIngrediente = ingredienteMapper.ingredienteDtoToIngrediente(ingredienteDto);
            //Ingrediente createIngrediente = ingredienteMapper.ingredienteDtoToIngrediente(ingredienteDto);
            return ingredienteRepository.save(createIngrediente);
        }
    }
}
