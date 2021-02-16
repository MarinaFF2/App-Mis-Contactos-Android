package com.example.mycontacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Contact> listContact;
    private Agenda agenda;
    private RecyclerView rvListContact;
    private static final int LAUNCH_SECOND_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null) {
            //recogemos las sesiones
            listContact = (ArrayList<Contact>) savedInstanceState.getSerializable("listContact");
            agenda = new Agenda();
            agenda.setListContact(listContact);
        }else if(savedInstanceState==null) {
            //instancaimos el arrayList
            agenda = new Agenda();
            listContact = agenda.getListContact();
        }

        aniadimosMenu();
        recyclerView();
    }

    private void recyclerView() {
        rvListContact = (RecyclerView) findViewById(R.id.rvListContact);
        //añado layout de como se va a ver
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvListContact.setLayoutManager(linearLayoutManager);
        //añado adaptador
        ContactoAdapter adapter = new ContactoAdapter(listContact, this);
        rvListContact.setAdapter(adapter);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //guardadmos las sesiones
        outState.putSerializable("listContact", listContact);
        Toast.makeText(this, "metodo onSaveInstanceState", Toast.LENGTH_SHORT).show();
    }

    private void aniadimosMenu() {
        //añadimos el action bar a la activity
        Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);

        //añadimos la accion a los items del menu
       // ActionBar actionBar = getSupportActionBar();
       // actionBar.setDisplayHomeAsUpEnabled(true);
    }

  /*
  public boolean onCreateOptionsMenu(Menu menu){
        //metodo para crear el menu y sus items
        //añadimos el menu el add
        getMenuInflater().inflate(R.menu.menu_add, menu);

      return super.onCreateOptionsMenu(menu);
   }

  public boolean onOptionsItemSelected(MenuItem item){
        //metodo para saber la opcion seleccionada de los items del menu
        switch (item.getItemId()){
            case R.id.action_create_order: //si pulsamos en el +
                Intent i = new Intent(this, NewContactActivity.class);
                startActivityForResult(i, LAUNCH_SECOND_ACTIVITY);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
*/


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