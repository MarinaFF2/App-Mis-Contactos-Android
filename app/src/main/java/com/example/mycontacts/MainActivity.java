package com.example.mycontacts;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Contact> listContact;
    private ListView list_contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rellenarArrarListContacts();
        rellenarListView();
        listViewEvent();
    }

    private void listViewEvent() {
        //creaccion del listener
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View itemView, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ContactDetailActivity.class);
                if(position == 0) {//1º contacto
                    intent.putExtra("Contact", listContact.get(position));
                    startActivity(intent);
                    finish();
                }
                if(position == 1) {//2º contacto
                    intent.putExtra("Contact", listContact.get(position));
                    startActivity(intent);
                    finish();
                }
                if(position == 2) {//3º contacto
                    intent.putExtra("Contact", listContact.get(position));
                    startActivity(intent);
                    finish();
                }

                if(position == 3) {//4º contacto
                    intent.putExtra("Contact", listContact.get(position));
                    startActivity(intent);
                    finish();
                }
            }
        };
        //asignamos el listener creado a la ListView
        list_contacts.setOnItemClickListener(itemClickListener);
    }

    private void rellenarListView() {
        list_contacts = (ListView) findViewById(R.id.list_contacts);
        ArrayAdapter<Contact> adapter = new ArrayAdapter<Contact>(MainActivity.this, android.R.layout.simple_list_item_1, listContact);
        list_contacts.setAdapter(adapter);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == android.view.KeyEvent.KEYCODE_BACK){
            Intent i = new Intent(MainActivity.this, ContactDetailActivity.class);
            startActivity(i);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
    private void rellenarArrarListContacts() {
        listContact = new ArrayList<>();

        listContact.add(new  Contact(R.string.contac_name1, getString (R.string.contac_name1),R.string.contac_phone,R.string.contac_email1));
        listContact.add(new Contact(R.string.contac_name2, getString (R.string.contac_name2),R.string.contac_phone,R.string.contac_email2));
        listContact.add(new Contact(R.string.contac_name3, getString (R.string.contac_name3),R.string.contac_phone,R.string.contac_email3));
        listContact.add(new Contact(R.string.contac_name4, getString (R.string.contac_name4),R.string.contac_phone,R.string.contac_email4));
    }
}