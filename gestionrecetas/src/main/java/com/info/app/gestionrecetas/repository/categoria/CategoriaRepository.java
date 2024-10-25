package com.info.app.gestionrecetas.repository.categoria;

import com.info.app.gestionrecetas.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {

    Optional<Categoria> findByNombre(String nombre);
}
