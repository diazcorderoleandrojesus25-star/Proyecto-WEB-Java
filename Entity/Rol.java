package com.Jobxpress.Jobxpress.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")   // ✔ columna correcta
    private Integer idRol;

    @Column(name = "rol", nullable = false, unique = true)
    private String rol;  // EJ: ROLE_CLIENTE, ROLE_ADMIN

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
