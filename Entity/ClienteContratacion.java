package com.Jobxpress.Jobxpress.Entity;

import com.Jobxpress.Jobxpress.Entity.pk.ClienteContratacionPK;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cliente_contratacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteContratacion {

    @EmbeddedId
    private ClienteContratacionPK id;

    @ManyToOne
    @MapsId("idCliente")
    @JoinColumn(name = "id_cliente")
    private Usuario cliente;

    @ManyToOne
    @MapsId("idContratacion")
    @JoinColumn(name = "id_contratacion")
    private Contratacion contratacion;

}
