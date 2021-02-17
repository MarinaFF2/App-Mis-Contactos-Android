package com.example.mycontacts;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactoAdapter extends RecyclerView.Adapter<ContactoAdapter.ContactoViewHolder>{
    ArrayList<Contact> listContactos;
    Activity activity;
    public ContactoAdapter(ArrayList<Contact> listContactos, Activity activity) {
        this.listContactos = listContactos;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //enlazamos la clase con el layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_contacto,parent,false);
        return new ContactoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder contactoViewHolder, int position) {
        //mostramos textos
        Contact c1 = listContactos.get(position);
        contactoViewHolder.imgCvFoto.setImageResource(c1.getFoto());
        contactoViewHolder.tvCvNombre.setText(c1.getNombre());
        contactoViewHolder.tvCvTelefono.setText(c1.getPhone());
        contactoViewHolder.tvCvEmail.setText(c1.getEmail());

        //añado listener detailContact
        contactoViewHolder.imgCvFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ContactDetailActivity.class);
                for (int i = 0; listContactos.size() > i; i++) {
                    if (position == i) {// Contact
                        intent.putExtra("Contact", listContactos.get(position));
                        activity.startActivity(intent);
                    }
                }
            }
        });
        //añado listener dar like
        contactoViewHolder.imgCVHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity,"Has pulsado like a "+c1.getNombre(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listContactos.size();
    }

    public static class ContactoViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgCvFoto;
        private ImageView imgCVHeart;
        private TextView tvCvNombre;
        private TextView tvCvTelefono;
        private TextView tvCvEmail;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCvFoto = (ImageView) itemView.findViewById(R.id.imgCvFoto);
            imgCVHeart = (ImageView) itemView.findViewById(R.id.imgCVHeart);
            tvCvNombre = (TextView) itemView.findViewById(R.id.tvCvNombre);
            tvCvTelefono = (TextView) itemView.findViewById(R.id.tvCvTelefono);
            tvCvEmail = (TextView) itemView.findViewById(R.id.tvCvEmail);
        }
    }
}
