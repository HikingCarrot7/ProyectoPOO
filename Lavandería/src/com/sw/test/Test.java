package com.sw.test;

import com.sw.controller.VistaPrincipalController;
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

        EventQueue.invokeLater(() ->
        {

            VistaPrincipal vistaPrincipal = new VistaPrincipal();

            vistaPrincipal.setVisible(true);
            vistaPrincipal.setLocationRelativeTo(null);

            new VistaPrincipalController(vistaPrincipal);

        });

    }

}
