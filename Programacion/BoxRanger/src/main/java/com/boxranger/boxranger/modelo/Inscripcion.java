package com.boxranger.boxranger.modelo;

/**
 * Clase que representa la inscripción de un socio a una clase del box.
 * Actúa como tabla intermedia entre Socios y Clases para gestionar
 * la relación muchos a muchos entre ambas entidades.
 * La clave primaria es compuesta: idSocio + idClase.
 */
public class Inscripcion {

    /** Identificador del socio inscrito (clave foránea y parte de la PK compuesta) */
    private int idSocio;

    /** Identificador de la clase a la que se inscribe (clave foránea y parte de la PK compuesta) */
    private int idClase;

    /** Fecha en la que el socio se inscribió a la clase en formato yyyy-MM-dd */
    private String fecha_inscripcion;

    /** Constructor vacío necesario para JavaFX */
    public Inscripcion() {
    }

    /**
     * Constructor completo con todos los campos de la inscripción.
     * @param idSocio identificador del socio
     * @param idClase identificador de la clase
     * @param fecha_inscripcion fecha de inscripción
     */
    public Inscripcion(int idSocio, int idClase, String fecha_inscripcion) {
        this.idSocio = idSocio;
        this.idClase = idClase;
        this.fecha_inscripcion = fecha_inscripcion;
    }

    public int getIdSocio() { return idSocio; }
    public void setIdSocio(int idSocio) { this.idSocio = idSocio; }
    public int getIdClase() { return idClase; }
    public void setIdClase(int idClase) { this.idClase = idClase; }
    public String getFecha_inscripcion() { return fecha_inscripcion; }
    public void setFecha_inscripcion(String fecha_inscripcion) { this.fecha_inscripcion = fecha_inscripcion; }
}