package com.sw.controller;

import com.sw.model.Ticket;
import com.sw.view.TicketInterfaz;

/**
 *
 * @author Mohammed
 */
public class VerTicketController
{

    private TicketInterfaz ticketInterfaz;
    private Ticket ticket;

    public VerTicketController(TicketInterfaz ticketInterfaz, Ticket ticket)
    {

        this.ticketInterfaz = ticketInterfaz;
        this.ticket = ticket;

    }

    public void mostrarTicket()
    {
        ticketInterfaz.getVerTicket().setText(new TicketGenerator(ticket).generarTicket());
    }

}
