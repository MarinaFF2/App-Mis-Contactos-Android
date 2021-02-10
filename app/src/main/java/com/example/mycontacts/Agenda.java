package com.example.mycontacts;

import java.util.ArrayList;

public class Agenda {
    private ArrayList<Contact> listContact;

    public Agenda() {
        rellenarArrarListContacts();
    }

    public void setListContact(ArrayList<Contact> listContact) {
        this.listContact = listContact;
    }

    public ArrayList<Contact> getListContact() {
        return listContact;
    }

    private void rellenarArrarListContacts() {
        listContact = new ArrayList<>();

        listContact.add(new  Contact("Pedro Sanchez","064567890","pedroSanchez@gmail.com"));
        listContact.add(new Contact("Pablo Iglesias","064567890","pabloIglesias@gmail.com"));
        listContact.add(new Contact("Pablo Casado","064567890","pabloCasado@gmail.com"));
        listContact.add(new Contact("Santiago Abascal","064567890","santiagoAbascal@gmail.com"));
    }
}
