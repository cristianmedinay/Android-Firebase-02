package com.example.subidanotacristian.utils;

import java.io.Serializable;

public class Persona implements Serializable {

    String nombre,plataforma,edad,precio;


    public Persona(String nombre, String plataforma, String edad, String precio) {
        this.nombre = nombre;
        this.plataforma = plataforma;
        this.edad = edad;
        this.precio = precio;
    }

    public Persona(){

    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", plataforma='" + plataforma + '\'' +
                ", edad='" + edad + '\'' +
                ", precio='" + precio + '\'' +
                '}';
    }
}
