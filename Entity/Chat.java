package com.Jobxpress.Jobxpress.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idChat;

    private String mensaje;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "id_emisor")
    private Usuario emisor;

    @ManyToOne
    @JoinColumn(name = "id_receptor")
    private Usuario receptor;
}
