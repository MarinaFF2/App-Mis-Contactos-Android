package com.example.mycontacts;

import java.io.Serializable;
import java.util.ArrayList;

public class Agenda  implements Serializable {
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

        listContact.add(new  Contact(R.drawable.pedro_sanchez,"Pedro Sanchez","064567890","pedroSanchez@gmail.com"));
        listContact.add(new Contact(R.drawable.pablo_iglesias,"Pablo Iglesias","064567890","pabloIglesias@gmail.com"));
        listContact.add(new Contact(R.drawable.pablo_casado,"Pablo Casado","064567890","pabloCasado@gmail.com"));
        listContact.add(new Contact(R.drawable.santiago_abascal,"Santiago Abascal","064567890","santiagoAbascal@gmail.com"));
        listContact.add(new Contact(R.drawable.salvador_illa,"salvador illa","234234","salvador_illa@gmail.com"));
        listContact.add(new Contact(R.drawable.fernando_simon,"fernando simon","234234","fernando_simon@gmail.com"));
    }
}
