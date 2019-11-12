package com.sw.model;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 *
 * @author Mohammed
 */
public class Historial implements Serializable
{

    // ---CLIENTE---Clave, nombre, fcha, activo;
    private Cliente cliente;
    private Prenda[] prendas;
    private GregorianCalendar fecha;
    private double totalKg;
    private double precioTotal;

    public Historial(Cliente cliente, Prenda[] prendas, GregorianCalendar fecha, double totalKg, double precioTotal)
    {
        this.cliente = cliente;
        this.prendas = prendas;
        this.fecha = fecha;
        this.totalKg = totalKg;
        this.precioTotal = precioTotal;

    }

    public Cliente getCliente()
    {
        return cliente;
    }

    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }

    public Prenda[] getPrendas()
    {
        return prendas;
    }

    public void setPrendas(Prenda[] prendas)
    {
        this.prendas = prendas;
    }

    public GregorianCalendar getFecha()
    {
        return fecha;
    }

    public void setFecha(GregorianCalendar fecha)
    {
        this.fecha = fecha;
    }

    public double getTotalKg()
    {
        return totalKg;
    }

    public void setTotalKg(double totalKg)
    {
        this.totalKg = totalKg;
    }

    public double getPrecioTotal()
    {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal)
    {
        this.precioTotal = precioTotal;
    }

}
