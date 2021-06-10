package com.web.tablas;

public class Comentario {

    public Integer id;
    public String comentario;
    public Integer posts_id;
    public Integer usuario_id;
    public Integer empresa_id;
    public Integer multimedia_id;
    public String fecha_creacion;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComentario() {
        return this.comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getPosts_id() {
        return this.posts_id;
    }

    public void setPosts_id(Integer posts_id) {
        this.posts_id = posts_id;
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

    public Integer getMultimedia_id() {
        return this.multimedia_id;
    }

    public void setMultimedia_id(Integer multimedia_id) {
        this.multimedia_id = multimedia_id;
    }

    public String getFecha_creacion() {
        return this.fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

}