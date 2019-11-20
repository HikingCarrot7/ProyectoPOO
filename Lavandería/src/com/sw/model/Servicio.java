package com.sw.model;

import com.sw.persistence.DAO;
import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author Mohammed
 */
public abstract class Servicio implements Serializable
{

    private static final long serialVersionUID = 5793287335339414498L;

    private Cliente cliente;
    private Calendar fecha;
    private static int numeroTickets;
    private int numeroTicket;

    static
    {
        numeroTickets = new DAO(DAO.RUTA_NUMTICKETS).getClaves();
    }

    public Servicio(Cliente cliente, Calendar fecha)
    {

        this.cliente = cliente;
        this.fecha = fecha;

        numeroTicket = ++numeroTickets;

    }

    public Servicio(Cliente cliente)
    {
        this(cliente, Calendar.getInstance());
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

    public int getNumeroTicket()
    {
        return numeroTicket;
    }

    public static int getNumeroTickets()
    {
        return numeroTickets;
    }

}
