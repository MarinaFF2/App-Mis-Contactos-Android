package com.example.mycontacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.os.PersistableBundle;
import android.view.KeyEvent;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ContactDetailActivity extends AppCompatActivity {
    private Contact  contact;
    private TextView textName;
    private TextView textPhone;
    private TextView textEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        aniadimosMenu();
        rellenarActivity();
        llamarEvent();
        emailEvent();
    }

    private void aniadimosMenu() {
        //añadimos el action bar a la activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void rellenarActivity() {
        contact = (Contact) getIntent().getSerializableExtra("Contact");

        textName = (TextView) findViewById(R.id.textName);
        textPhone = (TextView) findViewById(R.id.textPhone);
        textEmail = (TextView) findViewById(R.id.textEmail);

        textName.setText(contact.getNombre());
        textPhone.setText(contact.getPhone());
        textEmail.setText(contact.getEmail());
    }

    private void llamarEvent() {
        LinearLayout linkPhone = (LinearLayout) findViewById(R.id.linkPhone);
        //creaccion del listener
        linkPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Esta opcion 1º te llama directamente al numero
                //los permisos solo los necesita el ACTION_CALL
                //Comprobar si existen permisos
                /*int permissionCheck = ContextCompat.checkSelfPermission(ContactDetailActivity.this, Manifest.permission.CALL_PHONE);

                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    Log.i("Mensaje", "No se tiene permiso para realizar llamadas telefónicas.");
                    ActivityCompat.requestPermissions(ContactDetailActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 225);
                } else {
                    Log.i("Mensaje", "Se tiene permiso para realizar llamadas!");
                    Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+textPhone.getText()));
                       startActivity(intentCall);
                }*/

                //Esta opcion 2º te deja el numero para que le des al boton de llamada
                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+textPhone.getText()));
                startActivity(intentCall);
                finish();
            }
        });
    }

    private void emailEvent() {
        LinearLayout linkEmail = (LinearLayout) findViewById(R.id.linkEmail);
        linkEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("plain/text");
                i.setType("message/rfc822");
                //   i.setData(Uri.parse("malito:"));
                i.putExtra(Intent.EXTRA_EMAIL,textEmail.getText());
                startActivity(Intent.createChooser(i, "Email"));
                finish();
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == android.view.KeyEvent.KEYCODE_BACK){
            Intent i = new Intent(ContactDetailActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}