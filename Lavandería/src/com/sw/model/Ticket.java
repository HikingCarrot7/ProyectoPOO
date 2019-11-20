package com.sw.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Mohammed
 */
public class Ticket implements Serializable
{

    private static final long serialVersionUID = 6162162262623189765L;

    private String nombreCliente;
    private String nombreNegocio;
    private String direccionNegocio;
    private ArrayList<Prenda> prendas;
    private int numeroTicket;
    private int totalPiezas;
    private double precioTotal;
    private double totalKg;

    public Ticket(int numeroTicket, String nombreCliente, ArrayList<Prenda> prendas, int totalPiezas, double precioTotal, double totalKg)
    {

        this.numeroTicket = numeroTicket;
        this.nombreCliente = nombreCliente;
        this.prendas = prendas;
        this.totalPiezas = totalPiezas;
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

    public String getNombreCliente()
    {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente)
    {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreNegocio()
    {
        return nombreNegocio;
    }

    public void setNombreNegocio(String nombreNegocio)
    {
        this.nombreNegocio = nombreNegocio;
    }

    public String getDireccionNegocio()
    {
        return direccionNegocio;
    }

    public void setDireccionNegocio(String direccionNegocio)
    {
        this.direccionNegocio = direccionNegocio;
    }

    public ArrayList<Prenda> getPrendas()
    {
        return prendas;
    }

    public void setPrendas(ArrayList<Prenda> prendas)
    {
        this.prendas = prendas;
    }

    public int getTotalPiezas()
    {
        return totalPiezas;
    }

    public void setTotalPiezas(int totalPiezas)
    {
        this.totalPiezas = totalPiezas;
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
