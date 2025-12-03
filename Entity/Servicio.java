package com.Jobxpress.Jobxpress.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "servicio")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idServicio;

    private String nombre;
    private String descripcion;
    private Double precio;

   @ManyToOne
    @JoinColumn(name = "id_prestador", nullable = true)
    private Prestador prestador;


    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
}
