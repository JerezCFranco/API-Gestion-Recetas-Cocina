package com.info.app.gestionrecetas.repository.receta;

import com.info.app.gestionrecetas.domain.Receta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecetaRepository extends JpaRepository <Receta, UUID> {
}
