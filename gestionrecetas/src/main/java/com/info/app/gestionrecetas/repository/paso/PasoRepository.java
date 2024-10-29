package com.info.app.gestionrecetas.repository.paso;

import com.info.app.gestionrecetas.domain.Paso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PasoRepository extends JpaRepository<Paso, UUID> {

    Optional<Paso> findByNombre(String nombre);
    
}
