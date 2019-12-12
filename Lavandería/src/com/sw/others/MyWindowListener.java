package com.sw.others;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author Me
 */
public class MyWindowListener extends WindowAdapter
{

    private JFrame ventana;

    public MyWindowListener(JFrame ventana)
    {
        this.ventana = ventana;
    }

    @Override
    public void windowClosing(WindowEvent e)
    {
        ventana.setVisible(true);
    }

}
