package com.sw.test;

import com.sw.controller.VistaPrincipalController;
import com.sw.model.Cliente;
import com.sw.model.Servicio;
import com.sw.persistence.DAO;
import com.sw.view.VistaPrincipal;
import java.awt.EventQueue;

/**
 *
 * @author Mohammed
 */
public class Test
{

    public static void main(String[] args)
    {

        Cliente.setClave(new DAO(DAO.RUTA_CLAVECLIENTES).getClaves());
        Servicio.setNumeroTickets(new DAO(DAO.RUTA_NUMTICKETS).getClaves());

        EventQueue.invokeLater(() ->
        {

            VistaPrincipal vistaPrincipal = new VistaPrincipal();

            vistaPrincipal.setVisible(true);
            vistaPrincipal.setLocationRelativeTo(null);

            new VistaPrincipalController(vistaPrincipal);

        });

    }

}
