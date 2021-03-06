package com.example.mycontacts;

import java.io.Serializable;

public class Contact implements Serializable {

    private int Foto;
    private String nombre;
    private String phone;
    private String email;

    public Contact(int foto,String nombre, String phone, String email) {
        Foto = foto;
        this.nombre = nombre;
        this.phone = phone;
        this.email = email;
    }
    public Contact() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFoto() {
        return Foto;
    }
    @Override
    public String toString() {
        return nombre;
    }
}
