package com.Jobxpress.Jobxpress.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "pago")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPago;

    private Double monto;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "id_contratacion")
    private Contratacion contratacion;

    @ManyToOne
    @JoinColumn(name = "id_metodo")
    private MetodoPago metodo;
}
