package com.example.mycontacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactDetailActivity extends AppCompatActivity {
    private Contact  contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        contact = (Contact) getIntent().getSerializableExtra("Contact");

        TextView textName = (TextView) findViewById(R.id.textName);
        TextView textPhone = (TextView) findViewById(R.id.textPhone);
        TextView textEmail = (TextView) findViewById(R.id.textEmail);

        textName.setText(contact.getNombreID());
        textPhone.setText(contact.getPhoneID());
        textEmail.setText(contact.getEmailID());

        ImageView imagePhone = (ImageView) findViewById(R.id.imagePhone);
        ImageView imageEmail = (ImageView) findViewById(R.id.imageEmail);

        //creaccion del listener
        imagePhone.setOnClickListener(new View.OnClickListener() {
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
            }
        });

        imageEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("plain/text");
                startActivity(Intent.createChooser(i, "Email"));
            }
        });
    }
}