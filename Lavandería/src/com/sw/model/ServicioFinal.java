package com.sw.model;

import java.util.Calendar;

/**
 *
 * @author Mohammed
 */
public class ServicioFinal extends Servicio
{

    private Ticket ticket;

    public ServicioFinal(Cliente cliente, Calendar fecha)
    {
        super(cliente, fecha);
    }

    public ServicioFinal(Cliente cliente, Ticket ticket)
    {
        super(cliente);

        this.ticket = ticket;

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
