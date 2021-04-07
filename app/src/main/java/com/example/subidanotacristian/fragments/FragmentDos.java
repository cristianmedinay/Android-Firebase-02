package com.example.subidanotacristian.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.subidanotacristian.R;
import com.example.subidanotacristian.adaptador.RecyclerCristian;
import com.example.subidanotacristian.adaptador.RecyclerUsuario;
import com.example.subidanotacristian.utils.Persona;

public class FragmentDos extends Fragment {

    RecyclerUsuario recyclerClientes;
    RecyclerView recyclerView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        recyclerClientes = new RecyclerUsuario(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_lista,container,false);
        return  view;
    }

    public void agregarCliente(Persona persona){
        recyclerClientes.agregarUsuario(persona);
    }
    public void eliminarNombre(Persona persona){
        recyclerClientes.eliminar(persona);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = getView().findViewById(R.id.recycler2);
        recyclerView.setAdapter(recyclerClientes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
    }

}
