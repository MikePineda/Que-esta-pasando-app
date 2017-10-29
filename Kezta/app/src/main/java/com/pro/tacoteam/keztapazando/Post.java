package com.pro.tacoteam.keztapazando;

/**
 * Created by GERALVARADO on 29/10/2017.
 */

public class Post {
    private String nombre;
    private String mensaje;

    public Post(String nombre, String mensaje){
        this.nombre = nombre;
        this.mensaje = mensaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
