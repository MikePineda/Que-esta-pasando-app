package com.pro.tacoteam.keztapazando;

import java.util.Date;

/**
 * Created by GERALVARADO on 29/10/2017.
 */

public class Post {
    private String nombre;
    private String mensaje;
    private Date date;
    public Post(String nombre, String mensaje, Date date){
        this.nombre = nombre;
        this.mensaje = mensaje;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
