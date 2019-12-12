package com.sw.persistence;

import com.sw.controller.TicketGenerator;
import com.sw.model.Ticket;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;

/**
 *
 * @author Me
 */
public class TicketDAO
{

    public static final String RUTA_NUMTICKETS = "res/com/sw/data/NumTickets.txt";

    private File file;

    public TicketDAO()
    {

        file = new File(RUTA_NUMTICKETS);

        if (!file.exists())
            try
            {

                file.createNewFile();

                try (Formatter out = new Formatter(new FileWriter(file, false)))
                {
                    out.format("%s", "0");
                }

            } catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }

    }

    public int getClaveTickets()
    {

        try (Scanner in = new Scanner(new FileReader(file)))
        {

            return in.nextInt();

        } catch (FileNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }

        return 0;

    }

    public void saveClaveTickets(int clave)
    {

        try (Formatter out = new Formatter(new FileWriter(file, false)))
        {

            out.format("%s", clave);

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    public void saveTicket(Ticket ticket)
    {

        File f = new File("res/com/src/tickets/Ticket " + ticket.getNumeroTicket() + ".txt");

        if (!f.exists())
            try
            {

                f.createNewFile();

            } catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }

        try (Formatter out = new Formatter(new FileWriter(f, false)))
        {

            out.format("%s", new TicketGenerator(ticket).generarTicket());

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

}
