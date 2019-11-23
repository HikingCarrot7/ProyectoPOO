package com.sw.model;

import com.sw.utilities.Temporizador;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Mohammed
 */
public class Servicio implements Serializable
{

    private static final long serialVersionUID = 5793287335339414498L;

    private static int numeroTickets;
    private Cliente cliente;
    private Calendar fecha;
    private ArrayList<Prenda> prendas;
    private Temporizador tiempoEstimado;
    private boolean ticketGenerado;
    private double totalKg;
    private double costoKg;
    private int numeroTicket;

    public Servicio(Cliente cliente, Calendar fecha, Temporizador tiempoEstimado, ArrayList<Prenda> prendas, double totalKg, double costoKg)
    {

        this.cliente = cliente;
        this.fecha = fecha;
        this.tiempoEstimado = tiempoEstimado;
        this.prendas = prendas;
        this.costoKg = costoKg;
        this.totalKg = totalKg;

        numeroTicket = ++numeroTickets;

    }

    public Ticket getTicket()
    {

        return new Ticket(
                getNumeroTicket(),
                getFecha(),
                getCliente().getNombre(),
                getPrendas(),
                getTotalPrendas(),
                getPrecioTotal(),
                getTotalKg());

    }

    public int getTotalPrendas()
    {

        int total = 0;

        for (int i = 0; i < prendas.size(); i++)
            total += prendas.get(i).getCantidad();

        return total;

    }

    public static int getNumeroTickets()
    {
        return numeroTickets;
    }

    public static void setNumeroTickets(int numeroTickets)
    {
        Servicio.numeroTickets = numeroTickets;
    }

    public Cliente getCliente()
    {
        return cliente;
    }

    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }

    public Calendar getFecha()
    {
        return fecha;
    }

    public void setFecha(Calendar fecha)
    {
        this.fecha = fecha;
    }

    public ArrayList<Prenda> getPrendas()
    {
        return prendas;
    }

    public void setPrendas(ArrayList<Prenda> prendas)
    {
        this.prendas = prendas;
    }

    public Temporizador getTiempoEstimado()
    {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(Temporizador tiempoEstimado)
    {
        this.tiempoEstimado = tiempoEstimado;
    }

    public boolean isTicketGenerado()
    {
        return ticketGenerado;
    }

    public void setTicketGenerado(boolean ticketGenerado)
    {
        this.ticketGenerado = ticketGenerado;
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
        return costoKg * totalKg;
    }

    public double getCostoKg()
    {
        return costoKg;
    }

    public void setCostoKg(double costoKg)
    {
        this.costoKg = costoKg;
    }

    public int getNumeroTicket()
    {
        return numeroTicket;
    }

    public void setNumeroTicket(int numeroTicket)
    {
        this.numeroTicket = numeroTicket;
    }

}
