package com.web.tablas;

public class Comentario {

    public Integer id;
    public String comentario;
    public Integer posts_id;
    public Integer usuario_id;
    public Integer empresa_id;
    public String multimedia;

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

    public String getMultimedia() {
        return this.multimedia;
    }

    public void setMultimedia(String multimedia) {
        this.multimedia = multimedia;
    }

}