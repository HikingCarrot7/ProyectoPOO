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

    private String numeroTicket;
    private String nombreCliente;
    private String nombreNegocio;
    private String nombreEncargado;
    private String direccionNegocio;
    private ArrayList<Prenda> prendas;
    private double totalPrecio;
    private double totalKg;

    public Ticket(String numeroTicket, String nombreCliente, String nombreEncargado, ArrayList<Prenda> prendas, double totalPrecio, double totalKg)
    {

        this.numeroTicket = numeroTicket;
        this.nombreCliente = nombreCliente;
        this.nombreEncargado = nombreEncargado;
        this.prendas = prendas;
        this.totalPrecio = totalPrecio;
        this.totalKg = totalKg;

        nombreNegocio = Negocio.NOMBRENEGOCIO;
        direccionNegocio = Negocio.DIRECCION;

    }

    public Ticket(String numeroTicket, String nombreCliente, ArrayList<Prenda> prendas, double totalPrecio, double totalKg)
    {

        this.numeroTicket = numeroTicket;
        this.nombreCliente = nombreCliente;
        this.prendas = prendas;
        this.totalPrecio = totalPrecio;
        this.totalKg = totalKg;

        nombreNegocio = Negocio.NOMBRENEGOCIO;
        direccionNegocio = Negocio.DIRECCION;

    }

    public void generarTicket()
    {

    }

    public String getNumeroTicket()
    {
        return numeroTicket;
    }

    public void setNumeroTicket(String numeroTicket)
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

    public String getNombreEncargado()
    {
        return nombreEncargado;
    }

    public void setNombreEncargado(String nombreEncargado)
    {
        this.nombreEncargado = nombreEncargado;
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

    public double getTotalPrecio()
    {
        return totalPrecio;
    }

    public void setTotalPrecio(double totalPrecio)
    {
        this.totalPrecio = totalPrecio;
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
