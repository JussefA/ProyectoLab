package com.example.myapplication.entidades;

import static com.example.myapplication.Activities.entrar.carrera;

public class Usuario {
    private String id_usuario;
    private String nombre_usuario;
    private String apellido_usuario;
    private String carrera_usuario;

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    /*public String getApeliido_Materno() {
        return apeliido_Materno;
    }

    public void setApeliido_Materno(String apeliido_Materno) {
        this.apeliido_Materno = apeliido_Materno;
    }*/

    public String getApellido_usuario() {
        return apellido_usuario;
    }

    public void setApellido_usuario(String apellido_usuario) {
        this.apellido_usuario = apellido_usuario;
    }

    public String getCarrera_usuario() {
        return carrera_usuario;
    }

    public void setCarrera_usuario(String carrera_usuario) {
        this.carrera_usuario = carrera_usuario;
    }
}
