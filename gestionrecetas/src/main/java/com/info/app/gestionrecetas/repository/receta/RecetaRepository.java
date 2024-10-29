package com.info.app.gestionrecetas.repository.receta;

import com.info.app.gestionrecetas.domain.Categoria;
import com.info.app.gestionrecetas.domain.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RecetaRepository extends JpaRepository <Receta, UUID> {

    @Query("SELECT r FROM Receta r JOIN r.categoria c WHERE c.nombre = :categoria")
    List<Receta> findBycategoriaLike(@Param("categoria")String ctgNombre);
}
