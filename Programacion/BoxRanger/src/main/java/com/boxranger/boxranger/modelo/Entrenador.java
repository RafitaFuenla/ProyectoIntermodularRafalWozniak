package com.boxranger.boxranger.modelo;

/**
 * Clase que representa un entrenador del box de CrossFit.
 * Contiene la información personal del entrenador y su especialidad.
 */
public class Entrenador {

    /** Identificador único del entrenador (autoincremental) */
    private int idEntrenador;

    /** Nombre del entrenador */
    private String nombre;

    /** Apellidos del entrenador */
    private String apellidos;

    /** DNI del entrenador (único) */
    private String DNI;

    /** Email del entrenador (único) */
    private String email;

    /** Teléfono de contacto del entrenador */
    private String telefono;

    /** Especialidad deportiva del entrenador (CrossFit, Halterofilia, etc.) */
    private String especialidad;

    /** Constructor vacío necesario para JavaFX */
    public Entrenador() {
    }

    /**
     * Constructor completo con todos los campos del entrenador.
     * @param idEntrenador identificador único
     * @param nombre nombre del entrenador
     * @param apellidos apellidos del entrenador
     * @param DNI documento de identidad
     * @param email correo electrónico
     * @param telefono teléfono de contacto
     * @param especialidad especialidad deportiva
     */
    public Entrenador(int idEntrenador, String nombre, String apellidos, String DNI, String email, String telefono, String especialidad) {
        this.idEntrenador = idEntrenador;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.DNI = DNI;
        this.email = email;
        this.telefono = telefono;
        this.especialidad = especialidad;
    }

    public int getIdEntrenador() { return idEntrenador; }
    public void setIdEntrenador(int idEntrenador) { this.idEntrenador = idEntrenador; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getDNI() { return DNI; }
    public void setDNI(String DNI) { this.DNI = DNI; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
}