package com.example.subidanotacristian;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.subidanotacristian.adaptador.RecyclerCristian;
import com.example.subidanotacristian.adaptador.RecyclerUsuario;
import com.example.subidanotacristian.dialogos.DialogoConfirm;
import com.example.subidanotacristian.fragments.FragmentDos;
import com.example.subidanotacristian.fragments.FragmentTres;
import com.example.subidanotacristian.fragments.FragmentUno;
import com.example.subidanotacristian.utils.Persona;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements RecyclerUsuario.OnSelectedPerso,
        FragmentUno.onSelectedListener,DialogoConfirm.deleteListener {
    String URL2 = "https://api.rawg.io/api/games?page_size=20";
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    FrameLayout frameLayout;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instancias();
        Intent intent = getIntent();
        user = intent.getStringExtra("usuario");
        escribirNodo();
        acciones();
        menuToggle();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_uno,new FragmentTres(),"tres");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_uno,new FragmentDos(),"dos");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_uno,new FragmentUno(),"uno");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();



    }


    public void menuToggle(){
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void acciones() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.menu_lista:
                        fragmentManager = getSupportFragmentManager();
                        FragmentDos lista = (FragmentDos) fragmentManager.findFragmentByTag("dos");
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_uno,lista,"dos");
                        fragmentTransaction.addToBackStack(null);
                        break;
                    case R.id.menu_registro:
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.add(R.id.frame_uno,new FragmentUno(),"uno");
                        fragmentTransaction.addToBackStack(null);

                        break;
                    case R.id.menu_market:
                        fragmentManager = getSupportFragmentManager();
                        FragmentTres fragmentTres = (FragmentTres) fragmentManager.findFragmentByTag("tres");
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_uno,fragmentTres,"tres");
                        fragmentTransaction.addToBackStack(null);
                        break;
                }
                fragmentTransaction.commit();
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    public void FragmentTress(){
        FragmentTres fragmentTres = (FragmentTres) getSupportFragmentManager().findFragmentByTag("tres");
        fragmentTres.agregarFragment(URL2);
    }

    private void instancias() {
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation);
        frameLayout = findViewById(R.id.frame_uno);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance("https://ejemplo-46978-default-rtdb.europe-west1.firebasedatabase.app/");

    }


    @Override
    public void OnLogEliminarSelected(Persona persona) {

        if (user.equals("Admin")) {
            DialogoConfirm dialogoConfirm = DialogoConfirm.newInstance(persona);
            dialogoConfirm.show(getSupportFragmentManager(),"key");

        }else{
            Toast.makeText(getApplicationContext(),"No puedes Eliminar",Toast.LENGTH_SHORT).show();
        }
    }

    public void escribirNodo() {

         DatabaseReference nodoReferencia = firebaseDatabase.getReference("lista").child(user).child("añadido").child("favorito");
        nodoReferencia.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot dataSnapshot = task.getResult();
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                while(iterator.hasNext()){
                     Persona perso = iterator.next().getValue(Persona.class);
                FragmentDos lista = (FragmentDos) getSupportFragmentManager().findFragmentByTag("dos");
                lista.agregarCliente(perso);
                }
            }
        });


    }
    @Override
    public void clienteSelected(Persona persona) {

        //AGREGAR DATOS

        FragmentDos fragmentDos = (FragmentDos) getSupportFragmentManager().findFragmentByTag("dos");
        fragmentDos.agregarCliente(persona);
        String name = persona.getNombre();

        if(user.equals("Admin")) {
            DatabaseReference databaseReferenceEscritura = firebaseDatabase.getReference("lista").child(user).child("añadido").child("favorito");
            databaseReferenceEscritura.child(name).setValue(persona);
        }else {
            DatabaseReference databaseReferenceEscriturajugador = firebaseDatabase.getReference("lista").child(user).child("añadido").child("favorito");
            databaseReferenceEscriturajugador.child(name).setValue(persona);
        }
    }

    @Override
    public void onSelectedRemove(Persona persona) {

        //ELIMINAR DATOS

        if(user.equals("Admin")) {
            DatabaseReference databa = firebaseDatabase.getReference().child("lista").child(user).child("añadido").child("favorito").child(persona.getNombre());
            databa.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    Persona nombres = task.getResult().getValue(Persona.class);
                    if(nombres.getNombre().equals(persona.getNombre())){
                        FragmentDos fragmentDos = (FragmentDos) getSupportFragmentManager().findFragmentByTag("dos");
                        databa.setValue(null);
                        Toast.makeText(getApplicationContext(),"borro",Toast.LENGTH_SHORT).show();
                        fragmentDos.eliminarNombre(persona);

                    }
                }
            });
        }else{
            Toast.makeText(getApplicationContext(),"No puedes Eliminar",Toast.LENGTH_SHORT).show();

        }
    }


}