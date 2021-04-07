package com.example.subidanotacristian.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.subidanotacristian.R;
import com.example.subidanotacristian.utils.Persona;

public class FragmentUno  extends Fragment {


    onSelectedListener listener;
    EditText n_nombre,n_marca,n_descripcion,n_precio,n_opcion;
    Button add_button;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.listener = (onSelectedListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_registro,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        n_nombre = getView().findViewById(R.id.texto_nombre);
        n_marca = getView().findViewById(R.id.texto_plataforma);
        n_descripcion = getView().findViewById(R.id.texto_edad);
        n_precio = getView().findViewById(R.id.texto_precio);
        add_button = getView().findViewById(R.id.agregar);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = n_nombre.getText().toString();
                String marca = n_marca.getText().toString();
                String descripcion = n_descripcion.getText().toString();
                String precio = n_precio.getText().toString();
                Persona persona = new Persona(nombre,marca,descripcion,precio);
                listener.clienteSelected(persona);
            }
        });
    }

    public interface onSelectedListener{
        void clienteSelected(Persona persona);
    }


}
