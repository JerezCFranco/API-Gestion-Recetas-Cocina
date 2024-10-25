package com.info.app.gestionrecetas.repository.paso;

import com.info.app.gestionrecetas.domain.Paso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PasoRepository extends JpaRepository<Paso, UUID> {
}
