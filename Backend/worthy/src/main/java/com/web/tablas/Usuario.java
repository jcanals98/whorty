package com.web.tablas;
import  com.google.gson.Gson;    


public class Usuario {

    public Integer id;
    public String username;
    public String password;
    public String nombre;
    public String apellidos;
    public String dni;
    public String email;
    public String telefono;
    public String ubicacion;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }  

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String emial) {
        this.email = emial;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getUbicacion() {
        return this.ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    @Override    
    public String toString() {    
        return "\" Usuario [id = "+ id + ", username = " + username + ", password = " + password + ", nombre = " + nombre + ", apellidos = " + apellidos + 
        ", dni = " + dni +  ", email = " + email +  ", telefono = " + telefono +", ubicacion = " + ubicacion +"]";    
    }  

}
