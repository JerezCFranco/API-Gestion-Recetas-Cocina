package com.info.app.gestionrecetas.repository.ingrediente;

import com.info.app.gestionrecetas.domain.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {

    Optional<Ingrediente> findByNombre(String nombre);
}
