package com.sw.test;

import com.sw.controller.VistaPrincipalController;
import com.sw.model.Cliente;
import com.sw.model.Servicio;
import com.sw.persistence.ClienteDAO;
import com.sw.persistence.TicketDAO;
import com.sw.view.VistaPrincipal;
import java.awt.EventQueue;

/**
 *
 * @author Me
 */
public class Test
{

    public static void main(String[] args)
    {

        Cliente.setClave(new ClienteDAO().getClaveClientes());
        Servicio.setNumeroTickets(new TicketDAO().getClaveTickets());

        EventQueue.invokeLater(() ->
        {
            VistaPrincipal vistaPrincipal = new VistaPrincipal();

            vistaPrincipal.setVisible(true);
            vistaPrincipal.setLocationRelativeTo(null);

            new VistaPrincipalController(vistaPrincipal);
        });

    }

}
