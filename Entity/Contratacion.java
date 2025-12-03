package com.Jobxpress.Jobxpress.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "contratacion")
public class Contratacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idContratacion;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    private String estado;

    @Column(nullable = true)
    private String hora;   // <--- NUEVO

    @Column(nullable = true)
    private Double monto;  // <--- NUEVO

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Usuario cliente;

    @ManyToOne
    @JoinColumn(name = "id_prestador")
    private Prestador prestador;

    @ManyToOne
    @JoinColumn(name = "id_servicio")
    private Servicio servicio;

    @OneToMany(mappedBy = "contratacion")
    private List<Pago> pagos;

    @OneToMany(mappedBy = "contratacion")
    private List<Calificacion> calificaciones;
}
