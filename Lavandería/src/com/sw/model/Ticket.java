package com.sw.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Me
 */
public class Ticket implements Serializable
{

    private static final long serialVersionUID = 6162162262623189765L;

    private String nombreCliente;
    private ArrayList<Prenda> prendas;
    private Calendar fecha;
    private int numeroTicket;
    private int totalPrendas;
    private double precioTotal;
    private double totalKg;

    public Ticket(int numeroTicket, Calendar fecha, String nombreCliente, ArrayList<Prenda> prendas, int totalPrendas, double precioTotal, double totalKg)
    {

        this.numeroTicket = numeroTicket;
        this.fecha = fecha;
        this.nombreCliente = nombreCliente;
        this.prendas = prendas;
        this.totalPrendas = totalPrendas;
        this.precioTotal = precioTotal;
        this.totalKg = totalKg;

    }

    public int getNumeroTicket()
    {
        return numeroTicket;
    }

    public void setNumeroTicket(int numeroTicket)
    {
        this.numeroTicket = numeroTicket;
    }

    public Calendar getFecha()
    {
        return fecha;
    }

    public void setFecha(Calendar fecha)
    {
        this.fecha = fecha;
    }

    public String getNombreCliente()
    {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente)
    {
        this.nombreCliente = nombreCliente;
    }

    public ArrayList<Prenda> getPrendas()
    {
        return prendas;
    }

    public void setPrendas(ArrayList<Prenda> prendas)
    {
        this.prendas = prendas;
    }

    public int getTotalPrendas()
    {
        return totalPrendas;
    }

    public void setTotalPrendas(int totalPiezas)
    {
        this.totalPrendas = totalPiezas;
    }

    public double getPrecioTotal()
    {
        return precioTotal;
    }

    public void setTotalPrecio(double totalPrecio)
    {
        this.precioTotal = totalPrecio;
    }

    public double getTotalKg()
    {
        return totalKg;
    }

    public void setTotalKg(double totalKg)
    {
        this.totalKg = totalKg;
    }

}
