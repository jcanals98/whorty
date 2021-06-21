package com.web.tablas;

public class Post {

    public Integer id;
    public String titulo;
    public String descripcion;
    public Integer usuario_id;
    public Integer empresa_id;
    public String multimedia;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getUsuario_id() {
        return this.usuario_id;
    }

    public void setUsuario_id(Integer usuario_id) {
        this.usuario_id = usuario_id;
    }

    public Integer getEmpresa_id() {
        return this.empresa_id;
    }

    public void setEmpresa_id(Integer empresa_id) {
        this.empresa_id = empresa_id;
    }

    public String getMultimedia() {
        return this.multimedia;
    }

    public void setMultimedia(String multimedia) {
        this.multimedia = multimedia;
    }

}
