package com.example.subidanotacristian.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.subidanotacristian.R;
import com.example.subidanotacristian.utils.ObjetoApi;
import com.example.subidanotacristian.utils.Persona;

import java.util.ArrayList;
public class RecyclerCristian extends RecyclerView.Adapter<RecyclerCristian.MiHolder> {

        ArrayList<ObjetoApi> listaPersona;
        Context context;

        public RecyclerCristian( Context context) {
        this.listaPersona = new ArrayList<>();
        this.context = context;
        }

@NonNull
@Override
public MiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(context).inflate(R.layout.item_recycler,parent,false);
        MiHolder miHolder = new MiHolder(view);
        return miHolder;
        }

    @Override
    public void onBindViewHolder(@NonNull MiHolder holder, int position) {
        final ObjetoApi persona = listaPersona.get(position);
        holder.getTexto_uno().setText(persona.getNombre());
        Glide.with(context).load(persona.getImagen()).into(holder.texto_dos);




        }




    public void aniadirLiga(ObjetoApi persona){
        listaPersona.add(persona);
        notifyDataSetChanged();
    }



    /*public void agregarInventario(Persona inventario){
        listaPersona.add(inventario);
        notifyDataSetChanged();
    }*/

    @Override
    public int getItemCount() {
        return listaPersona.size();
    }

public class MiHolder extends RecyclerView.ViewHolder {
    ImageView texto_dos;
    TextView texto_uno;

    public MiHolder(@NonNull View itemView) {
        super(itemView);
        texto_uno = itemView.findViewById(R.id.texto_1);
        texto_dos = itemView.findViewById(R.id.texto_2);
    }

    public TextView getTexto_uno() {
        return texto_uno;
    }

    public void setTexto_uno(TextView texto_uno) {
        this.texto_uno = texto_uno;
    }

    public ImageView getTexto_dos() {
        return texto_dos;
    }

    public void setTexto_dos(ImageView texto_dos) {
        this.texto_dos = texto_dos;
    }
    /*public TextView getTexto_uno() {
        return texto_uno;
    }

    public void setTexto_uno(TextView texto_uno) {
        this.texto_uno = texto_uno;
    }

    public TextView getTexto_dos() {
        return texto_dos;
    }

    public void setTexto_dos(TextView texto_dos) {
        this.texto_dos = texto_dos;
    }*/
}
}
