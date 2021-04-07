package com.example.subidanotacristian.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.subidanotacristian.R;
import com.example.subidanotacristian.utils.Persona;

import java.util.ArrayList;

public class RecyclerUsuario extends RecyclerView.Adapter<RecyclerUsuario.MiHolder> {

    ArrayList<Persona> listaPersona;
    Context context;
    OnSelectedPerso listener;
    public RecyclerUsuario(Context context) {
        this.listaPersona = new ArrayList<>();
        this.context = context;
        this.listener = (OnSelectedPerso) context;
    }
    @NonNull
    @Override
    public RecyclerUsuario.MiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(context).inflate(R.layout.item2_recycler_cliente,parent,false);
        MiHolder miHolder = new MiHolder(view);
        return miHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerUsuario.MiHolder holder, int position) {
        final Persona persona = listaPersona.get(position);
        holder.getTextoNombre().setText(persona.getNombre());
        holder.getTextoNombre().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnLogEliminarSelected(persona);
            }
        });

    }

    public interface OnSelectedPerso{
        void OnLogEliminarSelected(Persona persona);
    }


    public void agregarUsuario(Persona persona){
        listaPersona.add(persona);
        notifyDataSetChanged();
    }
    public void eliminar(Persona persona){
        listaPersona.remove(persona);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaPersona.size();
    }

    public class MiHolder extends RecyclerView.ViewHolder {
        TextView textoNombre;
        public MiHolder(@NonNull View itemView) {
            super(itemView);
            textoNombre = itemView.findViewById(R.id.texto_nombre);
        }

        public TextView getTextoNombre() {
            return textoNombre;
        }

        public void setTextoNombre(TextView textoNombre) {
            this.textoNombre = textoNombre;
        }
    }
}
