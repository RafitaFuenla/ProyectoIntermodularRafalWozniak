package com.boxranger.boxranger.modelo;

/**
 * Clase que representa un pago realizado por un socio del box de CrossFit.
 * El estado del pago puede ser: pagado, pendiente o fallido.
 */
public class Pago {

    /** Identificador único del pago (autoincremental) */
    private int idPago;

    /** Identificador del socio que realiza el pago (clave foránea) */
    private int idSocio;

    /** Importe de la cuota pagada */
    private double cuota;

    /** Fecha en la que se registró el pago en formato yyyy-MM-dd */
    private String fecha_pago;

    /** Estado del pago: pagado, pendiente o fallido */
    private String estado;

    /** Constructor vacío necesario para JavaFX */
    public Pago() {
    }

    /**
     * Constructor completo con todos los campos del pago.
     * @param idPago identificador único
     * @param idSocio identificador del socio asociado
     * @param cuota importe de la cuota
     * @param fecha_pago fecha del pago
     * @param estado estado del pago (pagado, pendiente, fallido)
     */
    public Pago(int idPago, int idSocio, double cuota, String fecha_pago, String estado) {
        this.idPago = idPago;
        this.idSocio = idSocio;
        this.cuota = cuota;
        this.fecha_pago = fecha_pago;
        this.estado = estado;
    }

    public int getIdPago() { return idPago; }
    public void setIdPago(int idPago) { this.idPago = idPago; }
    public int getIdSocio() { return idSocio; }
    public void setIdSocio(int idSocio) { this.idSocio = idSocio; }
    public double getCuota() { return cuota; }
    public void setCuota(double cuota) { this.cuota = cuota; }
    public String getFecha_pago() { return fecha_pago; }
    public void setFecha_pago(String fecha_pago) { this.fecha_pago = fecha_pago; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}