package com.boxranger.boxranger.modelo;

public class Socio {

    private int idSocio;
    private String nombre, apellidos, DNI, email, telefono, fecha_alta, fecha_baja;

    public Socio() {
    }

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

    public int getIdSocio() {
        return idSocio;
    }
    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getDNI() {
        return DNI;
    }
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getFecha_alta() {
        return fecha_alta;
    }
    public void setFecha_alta(String fecha_alta) {
        this.fecha_alta = fecha_alta;
    }
    public String getFecha_baja() {
        return fecha_baja;
    }
    public void setFecha_baja(String fecha_baja) {
        this.fecha_baja = fecha_baja;
    }
}
