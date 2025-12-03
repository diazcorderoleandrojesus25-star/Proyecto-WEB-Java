package com.Jobxpress.Jobxpress.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "participantes_chat")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipantesChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Usuario dentro del chat
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Sala o conversación del chat
    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    private String fecha_ingreso;
}
