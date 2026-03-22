package com.boxranger.boxranger.modelo;

public class Clase {

    private int idClase, idEntrenador;
    private String nombre, hora_inicio, hora_fin;
    private int max_atletas;

    public Clase(int idClase, int idEntrenador, String nombre, String hora_inicio, String hora_fin, int max_atletas) {
        this.idClase = idClase;
        this.idEntrenador = idEntrenador;
        this.nombre = nombre;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.max_atletas = max_atletas;
    }

    public int getIdClase() {
        return idClase;
    }
    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }
    public int getIdEntrenador() {
        return idEntrenador;
    }
    public void setIdEntrenador(int idEntrenador) {
        this.idEntrenador = idEntrenador;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getHora_inicio() {
        return hora_inicio;
    }
    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }
    public String getHora_fin() {
        return hora_fin;
    }
    public void setHora_fin(String hora_fin) {
        this.hora_fin = hora_fin;
    }
    public int getMax_atletas() {
        return max_atletas;
    }
    public void setMax_atletas(int max_atletas) {
        this.max_atletas = max_atletas;
    }
}
