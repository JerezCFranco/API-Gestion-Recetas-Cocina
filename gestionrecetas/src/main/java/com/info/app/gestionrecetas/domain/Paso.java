package com.info.app.gestionrecetas.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Paso {

    @SuppressWarnings("deprecation")
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "receta_id", nullable = false)
    private Receta receta;

    private String nombre;

    private String descripcion;

    private int tiempoEstimado;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "paso_ingrediente",
            joinColumns = @JoinColumn(name = "paso_id"),
            inverseJoinColumns = @JoinColumn(name = "ingrediente_id")
    )
    private List<Ingrediente> ingredientes = new ArrayList<>();

    private boolean esOpcional;

    public void addIngrediente(Ingrediente ingrediente) {
        if (!this.ingredientes.contains(ingrediente)) {
            this.ingredientes.add(ingrediente);
            ingrediente.getPasos().add(this);
        }
    }
}
