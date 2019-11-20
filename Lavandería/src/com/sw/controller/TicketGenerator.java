package com.sw.controller;

import com.sw.model.Ticket;
import java.util.Calendar;

/**
 *
 * @author Mohammed
 */
public class TicketGenerator
{

    private Ticket ticket;
    private final int LONG_TICKET = 58;

    public TicketGenerator(Ticket ticket)
    {
        this.ticket = ticket;
    }

    public String generarTicket()
    {

        String textoTicket = "";
        String lineSeparator = System.getProperty("line.separator");

        textoTicket += String.format("%s%3$s%s%3$s%3$s", centrarLinea("Lavandería."), centrarLinea("Las burbujas mágicas."), lineSeparator);

        textoTicket += String.format("%s%s", centrarLinea("Cliente : " + ticket.getNombreCliente()), lineSeparator);

        textoTicket += centrarLinea("Teléfono : 9992676253");

        textoTicket += String.format("%3$s%s%3$s%s%3$s%3$s", centrarLinea("Avenida constitución, 25"), centrarLinea("02520 - Chinchilla de Monte Aragón"), lineSeparator);

        textoTicket += String.format("%1$tA, %1$tB %1$td, %1$tY%2$s%tr%s", Calendar.getInstance(), lineSeparator);

        textoTicket += String.format("%-10s%03d%s", "Ticket N°", ticket.getNumeroTicket(), lineSeparator);

        textoTicket += getSeparador();

        textoTicket += String.format("PRENDAS%s%s", alinearDerechaLinea("PRENDAS", "CANTIDAD"), lineSeparator);

        textoTicket += getSeparador();

        textoTicket += getDesglocePrendas();

        textoTicket += getSeparador();

        textoTicket += alinearDerechaLinea(12, "Cantidad de prendas :") + String.format("%12d", ticket.getTotalPiezas()) + lineSeparator;

        textoTicket += alinearDerechaLinea(12, "Total de kg :") + String.format("%12.2f", ticket.getTotalKg()) + lineSeparator;

        textoTicket += getSeparador();

        textoTicket += String.format("%s%3$s%s%3$s", alinearDerechaLinea(12, "Precio por kg. :") + String.format("$%11.2f", 9.5),
                alinearDerechaLinea(12, "Precio total :") + String.format("$%11.2f", ticket.getPrecioTotal()), lineSeparator);

        textoTicket += getSeparador();

        textoTicket += String.format("%s%s", centrarLinea("Atendió : Nicolás Canul Ibarra"), lineSeparator);

        textoTicket += centrarLinea("GRACIAS POR SU PREFERENCIA");

        return textoTicket;

    }

    private String getSeparador()
    {

        String temp = "";

        for (int i = 0; i < LONG_TICKET; i++)
            temp += "-";

        temp += System.getProperty("line.separator");

        return temp;

    }

    private String centrarLinea(String linea)
    {

        String temp = "";

        for (int i = 0; i < LONG_TICKET / 2 - linea.length() / 2; i++)
            temp += " ";

        temp += linea;

        return temp;

    }

    private String alinearDerechaLinea(String linea)
    {

        String temp = "";

        for (int i = 0; i < LONG_TICKET - linea.length(); i++)
            temp += " ";

        temp += linea;

        return temp;

    }

    private String alinearDerechaLinea(String leftOffSet, String linea)
    {

        String temp = "";

        for (int i = leftOffSet.length(); i < LONG_TICKET - linea.length(); i++)
            temp += " ";

        temp += linea;

        return temp;

    }

    private String alinearDerechaLinea(int rightOffSet, String linea)
    {

        String temp = "";

        for (int i = 0; i < LONG_TICKET - linea.length() - rightOffSet; i++)
            temp += " ";

        temp += linea;

        return temp;

    }

    private String getDesglocePrendas()
    {

        String temp = "";

        for (int i = 0; i < ticket.getPrendas().size(); i++)
            temp += String.format("%s%s%s", ticket.getPrendas().get(i).getDescripcion(), alinearDerechaLinea(ticket.getPrendas().get(i).getDescripcion(),
                    ticket.getPrendas().get(i).getCantidad() + ""), System.getProperty("line.separator"));

        return temp;

    }

}
