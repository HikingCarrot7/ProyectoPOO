package com.sw.controller;

import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * @author Mohammed
 */
public class BotonDinamico extends JButton
{

    private boolean seleccionado;

    public BotonDinamico(Icon icon)
    {
        super(icon);

    }

    public boolean isSeleccionado()
    {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado)
    {
        this.seleccionado = seleccionado;
    }

}
