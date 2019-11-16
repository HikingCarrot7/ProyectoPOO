package com.sw.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author Mohammed
 */
public class WindowsListener extends WindowAdapter
{

    private JFrame boton;

    public WindowsListener(JFrame otraFrame)
    {
        this.boton = otraFrame;
    }

    @Override
    public void windowClosing(WindowEvent e)
    {
        boton.setEnabled(true);
    }

}
