package com.example.mycontacts;

import java.io.Serializable;

public class Contact implements Serializable {
    private int nombreID;
    private String nombre;
    private int phoneID;
    private int emailID;

    public Contact(int nombreID,String nombre, int phoneID, int emailID) {
        this.nombreID = nombreID;
        this.nombre = nombre;
        this.phoneID = phoneID;
        this.emailID = emailID;
    }

    public int getNombreID() {
        return nombreID;
    }

    public void setNombreID(int nombreID) {
        this.nombreID = nombreID;
    }

    public int getPhoneID() {
        return phoneID;
    }

    public void setPhoneID(int phoneID) {
        this.phoneID = phoneID;
    }

    public int getEmailID() {
        return emailID;
    }

    public void setEmailID(int emailID) {
        this.emailID = emailID;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
