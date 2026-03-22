package com.boxranger.boxranger.modelo;

public class Inscripcion {

    private int idSocio, idClase;
    private String fecha_inscripcion;

    public Inscripcion(int idSocio, int idClase, String fecha_inscripcion) {
        this.idSocio = idSocio;
        this.idClase = idClase;
        this.fecha_inscripcion = fecha_inscripcion;
    }

    public int getIdSocio() {
        return idSocio;
    }
    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }
    public int getIdClase() {
        return idClase;
    }
    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }
    public String getFecha_inscripcion() {
        return fecha_inscripcion;
    }
    public void setFecha_inscripcion(String fecha_inscripcion) {
        this.fecha_inscripcion = fecha_inscripcion;
    }


}
