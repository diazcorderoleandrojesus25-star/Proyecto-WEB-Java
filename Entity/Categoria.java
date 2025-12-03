package com.Jobxpress.Jobxpress.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoria;

    private String nombre;

    @OneToMany(mappedBy = "categoria")
    private List<Servicio> servicios;
}
