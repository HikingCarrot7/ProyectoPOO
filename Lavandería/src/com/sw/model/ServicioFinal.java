package com.sw.model;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author Mohammed
 */
public class ServicioFinal extends Servicio implements Serializable
{

    private static final long serialVersionUID = -6552682746396847096L;

    private Ticket ticket;

    public ServicioFinal(Cliente cliente, Calendar fecha)
    {
        super(cliente, fecha);
    }

    public Ticket getTicket()
    {
        return ticket;
    }

    public void setTicket(Ticket ticket)
    {
        this.ticket = ticket;
    }

}
