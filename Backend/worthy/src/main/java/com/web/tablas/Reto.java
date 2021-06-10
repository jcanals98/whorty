package com.web.tablas;

public class Reto {

    public Integer id;
    public Integer id_Creador;
    public String nombre;
    public String descripcion;
    public String tecnologias;
    public Integer participantesMax;
    public Integer participantes;
    public Integer multimedia_id;
    public Integer nivel;
    public String fecha_limite;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_Creador() {
        return this.id_Creador;
    }

    public void setId_Creador(Integer id_Creador) {
        this.id_Creador = id_Creador;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTecnologias() {
        return this.tecnologias;
    }

    public void setTecnologias(String tecnologias) {
        this.tecnologias = tecnologias;
    }

    public Integer getParticipantesMax() {
        return this.participantesMax;
    }

    public void setParticipantesMax(Integer participantesMax) {
        this.participantesMax = participantesMax;
    }

    public Integer getParticipantes() {
        return this.participantes;
    }

    public void setParticipantes(Integer participantes) {
        this.participantes = participantes;
    }

    public Integer getMultimedia_id() {
        return this.multimedia_id;
    }

    public void setMultimedia_id(Integer multimedia_id) {
        this.multimedia_id = multimedia_id;
    }

    public Integer getNivel() {
        return this.nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public String getFecha_limite() {
        return this.fecha_limite;
    }

    public void setFecha_limite(String fecha_limite) {
        this.fecha_limite = fecha_limite;
    }


}