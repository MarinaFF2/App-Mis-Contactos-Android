package com.example.mycontacts;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Contact> listContact;
    private Agenda agenda;
    private ListView list_contacts;
    private static final int LAUNCH_SECOND_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instancaimos el arrayList
        agenda = new Agenda();
        listContact = agenda.getListContact();

        aniadimosMenu();
        rellenarListView();
        listViewEvent();
    }

    private void aniadimosMenu() {
        //añadimos el action bar a la activity
        Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);

        //añadimos la accion a los items del menu
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


    /**
     * metodo para crear el menu y sus items
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu){
        //añadimos el menu el add
        getMenuInflater().inflate(R.menu.menu_add, menu);

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * metodo para saber la opcion seleccionada de los items del menu
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_create_order: //si pulsamos en el +
                Intent i = new Intent(this, NewContactActivity.class);
                startActivityForResult(i, LAUNCH_SECOND_ACTIVITY);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * metodo que escuha las acciones del listView
     */
    private void listViewEvent() {
        //creaccion del listener
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View itemView, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ContactDetailActivity.class);
                for (int i = 0; listContact.size() > i; i++) {
                    if (position == i) {// Contact
                    intent.putExtra("Contact", listContact.get(position));
                    startActivity(intent);
                    finish();
                    }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Comprobamos que viene de la activity NewContactActivity
        if (requestCode == LAUNCH_SECOND_ACTIVITY) {
            if(resultCode == Activity.RESULT_OK){
                Log.e("myTag", "Me encuentro en MAinActivity, metodo onActivityResult");
                //recogemos el contacto que nos han pasado desde nuevo contacto
                Contact contact = (Contact) data.getSerializableExtra("Contact");
                listContact.add(contact);
                agenda.setListContact(listContact);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Log.e("myTag", "Cancelado.      Me encuentro en MAinActivity, metodo onActivityResult");
                //Write your code if there's no result
            }
        }
    }//onActivityResult

    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == android.view.KeyEvent.KEYCODE_BACK){
            Intent i = new Intent(MainActivity.this, ContactDetailActivity.class);
            startActivity(i);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}