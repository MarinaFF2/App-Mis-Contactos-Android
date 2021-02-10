package com.example.mycontacts;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.LogPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewContactActivity extends AppCompatActivity {
    private EditText editName;
    private EditText editPhone;
    private EditText editEmail;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        aniadimosMenu();
        asignamosElementos();
        buttonSaveEvent();
    }

    private void buttonSaveEvent() {
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if (!editName.getText().toString().equals("") && !editPhone.getText().toString().equals("") && !editEmail.getText().toString().equals("")) {
                    Log.e("myTag", "Pasado boton SAVE");
                    Contact contact = new Contact();
                    contact.setNombre(editName.getText().toString());
                    contact.setPhone(editPhone.getText().toString());
                    contact.setEmail(editEmail.getText().toString());

                    Intent returnIntent = new Intent(NewContactActivity.this, MainActivity.class);
                    returnIntent.putExtra("Contact", contact);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                //} else {
                    //mensaje de rellenar los campos
                //}
            }
        });
    }

    private void asignamosElementos() {
        editName = (EditText) findViewById(R.id.editName);
        editPhone = (EditText) findViewById(R.id.editPhone);
        editEmail = (EditText) findViewById(R.id.editEmail);
        buttonSave = (Button) findViewById(R.id.buttonSave);
    }

    private void aniadimosMenu() {
        //añadimos el action bar a la activity
        Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);

        //añadimos la accion a los items del menu
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}