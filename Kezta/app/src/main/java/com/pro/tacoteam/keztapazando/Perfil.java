package com.pro.tacoteam.keztapazando;

/**
 * Created by GERALVARADO on 29/10/2017.
 */

public class Perfil {

    private String id;
    private String nombre;
    private String username;
    private String password;
    private String correo;


    public Perfil(){
    }

    public Perfil(String username, String password, String correo) {
        this.username = username;
        this.password = password;
        this.correo = correo;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
