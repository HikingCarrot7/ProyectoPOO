package com.sw.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Mohammed
 */
public class Historial implements Serializable
{

    private static final long serialVersionUID = -8147791639672414830L;

    private Cliente cliente;
    private ArrayList<Prenda> prendas;
    private Calendar fecha;
    private double totalKg;
    private double precioTotal;

    public Historial(Cliente cliente, ArrayList<Prenda> prendas, Calendar fecha, double totalKg, double precioTotal)
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

    public ArrayList<Prenda> getPrendas()
    {
        return prendas;
    }

    public void setPrendas(ArrayList<Prenda> prendas)
    {
        this.prendas = prendas;
    }

    public Calendar getFecha()
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
