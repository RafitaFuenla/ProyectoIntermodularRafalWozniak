package com.boxranger.boxranger.modelo;

/**
 * Clase que representa una clase o sesión de entrenamiento del box de CrossFit.
 * Cada clase tiene un entrenador asignado y un número máximo de atletas permitidos.
 */
public class Clase {

    /** Identificador único de la clase (autoincremental) */
    private int idClase;

    /** Identificador del entrenador que imparte la clase (clave foránea) */
    private int idEntrenador;

    /** Nombre descriptivo de la clase (ej: WOD Mañana, Halterofilia...) */
    private String nombre;

    /** Hora de inicio de la clase en formato HH:mm:ss */
    private String hora_inicio;

    /** Hora de fin de la clase en formato HH:mm:ss */
    private String hora_fin;

    /** Número máximo de atletas permitidos en la clase */
    private int max_atletas;

    /** Constructor vacío necesario para JavaFX */
    public Clase() {
    }

    /**
     * Constructor completo con todos los campos de la clase.
     * @param idClase identificador único
     * @param idEntrenador identificador del entrenador asignado
     * @param nombre nombre descriptivo de la clase
     * @param hora_inicio hora de inicio
     * @param hora_fin hora de fin
     * @param max_atletas número máximo de atletas
     */
    public Clase(int idClase, int idEntrenador, String nombre, String hora_inicio, String hora_fin, int max_atletas) {
        this.idClase = idClase;
        this.idEntrenador = idEntrenador;
        this.nombre = nombre;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.max_atletas = max_atletas;
    }

    public int getIdClase() { return idClase; }
    public void setIdClase(int idClase) { this.idClase = idClase; }
    public int getIdEntrenador() { return idEntrenador; }
    public void setIdEntrenador(int idEntrenador) { this.idEntrenador = idEntrenador; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getHora_inicio() { return hora_inicio; }
    public void setHora_inicio(String hora_inicio) { this.hora_inicio = hora_inicio; }
    public String getHora_fin() { return hora_fin; }
    public void setHora_fin(String hora_fin) { this.hora_fin = hora_fin; }
    public int getMax_atletas() { return max_atletas; }
    public void setMax_atletas(int max_atletas) { this.max_atletas = max_atletas; }
}