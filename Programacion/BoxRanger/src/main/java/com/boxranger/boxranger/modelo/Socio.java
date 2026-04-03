package com.boxranger.boxranger.modelo;

/**
 * Clase que representa un socio del box de CrossFit.
 * Contiene toda la información personal del socio y las fechas
 * de alta y baja en el centro.
 */
public class Socio {

    /** Identificador único del socio (autoincremental) */
    private int idSocio;

    /** Nombre del socio */
    private String nombre;

    /** Apellidos del socio */
    private String apellidos;

    /** DNI del socio (único) */
    private String DNI;

    /** Email del socio (único) */
    private String email;

    /** Teléfono de contacto del socio */
    private String telefono;

    /** Fecha en la que el socio se dio de alta en el box */
    private String fecha_alta;

    /** Fecha en la que el socio causó baja. Puede ser nula si sigue activo */
    private String fecha_baja;

    /** Constructor vacío necesario para JavaFX */
    public Socio() {
    }

    /**
     * Constructor completo con todos los campos del socio.
     * @param idSocio identificador único
     * @param nombre nombre del socio
     * @param apellidos apellidos del socio
     * @param DNI documento de identidad
     * @param email correo electrónico
     * @param telefono teléfono de contacto
     * @param fecha_alta fecha de incorporación al box
     * @param fecha_baja fecha de baja, null si sigue activo
     */
    public Socio(int idSocio, String nombre, String apellidos, String DNI, String email, String telefono, String fecha_alta, String fecha_baja) {
        this.idSocio = idSocio;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.DNI = DNI;
        this.email = email;
        this.telefono = telefono;
        this.fecha_alta = fecha_alta;
        this.fecha_baja = fecha_baja;
    }

    public int getIdSocio() { return idSocio; }
    public void setIdSocio(int idSocio) { this.idSocio = idSocio; }
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
    public String getFecha_alta() { return fecha_alta; }
    public void setFecha_alta(String fecha_alta) { this.fecha_alta = fecha_alta; }
    public String getFecha_baja() { return fecha_baja; }
    public void setFecha_baja(String fecha_baja) { this.fecha_baja = fecha_baja; }
}