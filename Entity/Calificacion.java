package com.Jobxpress.Jobxpress.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "calificacion")
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCalificacion;

    private Integer puntuacion;
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "id_contratacion")
    private Contratacion contratacion;

    @ManyToOne
    @JoinColumn(name = "id_prestador")
    private Prestador prestador;
}
