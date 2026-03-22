package com.boxranger.boxranger.modelo;

public class Pago {

    private int idPago, idSocio;
    private double cuota;
    private String fecha_pago, estado;

    public Pago(int idPago, int idSocio, double cuota, String fecha_pago, String estado) {
        this.idPago = idPago;
        idSocio = idSocio;
        this.cuota = cuota;
        this.fecha_pago = fecha_pago;
        this.estado = estado;
    }

    public int getIdPago() {
        return idPago;
    }
    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }
    public int getIdSocio() {
        return idSocio;
    }
    public void setIdSocio(int idSocio) {
        idSocio = idSocio;
    }
    public double getCuota() {
        return cuota;
    }
    public void setCuota(double cuota) {
        this.cuota = cuota;
    }
    public String getFecha_pago() {
        return fecha_pago;
    }
    public void setFecha_pago(String fecha_pago) {
        this.fecha_pago = fecha_pago;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
