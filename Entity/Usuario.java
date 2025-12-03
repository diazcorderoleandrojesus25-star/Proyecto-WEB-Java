package com.Jobxpress.Jobxpress.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String contrasena;
    private String direccion;

    @ManyToOne(fetch = FetchType.EAGER)   
    @JoinColumn(name = "id_rol")
    private Rol rol;
}
