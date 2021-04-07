package com.example.subidanotacristian.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.subidanotacristian.R;
import com.example.subidanotacristian.adaptador.RecyclerCristian;
import com.example.subidanotacristian.utils.ObjetoApi;
import com.example.subidanotacristian.utils.Persona;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentTres extends Fragment {
    String URL2 = "https://api.rawg.io/api/games?page_size=20";
    RecyclerCristian recyclerPartidos;
    RecyclerView recyclerView;
    ArrayList<Persona> listaPartido;
    String ids,ruta,name,baner,team,descripcion,idteam,web,insta,face,twit;





    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);


            listaPartido = new ArrayList();
            recyclerPartidos = new RecyclerCristian(getContext());

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_uno,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = getView().findViewById(R.id.recycler);
        recyclerView.setAdapter(recyclerPartidos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
        getApi();

    }

    public void agregarFragment(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray object=response.getJSONArray("results");
                    for (int i = 0; i < object.length() ; i++) {
                        JSONObject H = object.getJSONObject(i);
                        name = H.getString("name");
                        baner = H.getString("background_image");
                        ObjetoApi persona =  new ObjetoApi(name,baner);

                        Log.v("objeeeee",persona.toString());
                        System.out.println(persona);

                        recyclerPartidos.aniadirLiga(persona);
                        //datos.add(new Ligas(name,id));
                        //liga = new Ligas(name,id);
                        //adaptadorLigas.notifyDataSetChanged();
                        //adaptadorLigas.aniadirLiga(liga);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag","onErrorResponse HAY ERROR"+error.getMessage());

            }

        });
        requestQueue.add(request);
    }

    private void getApi() {
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL2, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray object=response.getJSONArray("results");
                    for (int i = 0; i < object.length() ; i++) {
                        JSONObject H = object.getJSONObject(i);
                        name = H.getString("name");
                        baner = H.getString("background_image");
                        ObjetoApi persona =  new ObjetoApi(name,baner);

                        Log.v("objeeeee",persona.toString());
                        System.out.println(persona);

                        recyclerPartidos.aniadirLiga(persona);
                        //datos.add(new Ligas(name,id));
                        //liga = new Ligas(name,id);
                        //adaptadorLigas.notifyDataSetChanged();
                        //adaptadorLigas.aniadirLiga(liga);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag","onErrorResponse HAY ERROR"+error.getMessage());

            }

        });
        requestQueue.add(request);
    }


}
