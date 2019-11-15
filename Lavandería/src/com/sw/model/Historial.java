package com.sw.model;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 *
 * @author Mohammed
 */
public class Historial implements Serializable
{

    private static final long serialVersionUID = -8147791639672414830L;

    private Persona cliente;
    private Prenda[] prendas;
    private GregorianCalendar fecha;
    private double totalKg;
    private double precioTotal;

    public Historial(Persona cliente, Prenda[] prendas, GregorianCalendar fecha, double totalKg, double precioTotal)
    {
        this.cliente = cliente;
        this.prendas = prendas;
        this.fecha = fecha;
        this.totalKg = totalKg;
        this.precioTotal = precioTotal;

    }

    public Historial()
    {
        this(null, null, null, 0, 0);
    }

    public Persona getCliente()
    {
        return cliente;
    }

    public void setCliente(Persona cliente)
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
