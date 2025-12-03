package com.Jobxpress.Jobxpress.Entity;

import com.Jobxpress.Jobxpress.Entity.pk.ClienteCalificacionPK;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cliente_calificacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteCalificacion {

    @EmbeddedId
    private ClienteCalificacionPK id;

    @ManyToOne
    @MapsId("idCliente")
    @JoinColumn(name = "id_cliente")
    private Usuario cliente;

    @ManyToOne
    @MapsId("idCalificacion")
    @JoinColumn(name = "id_calificacion")
    private Calificacion calificacion;

}
