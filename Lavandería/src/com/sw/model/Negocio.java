package com.sw.model;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.Serializable;

/**
 *
 * @author Mohammed
 */
public class Negocio implements Serializable
{

    private static final long serialVersionUID = 2231606698466060108L;

    public static final String NOMBRENEGOCIO = "Las burbujas mágicas";
    public static final String DIRECCION = "C. 60...";

    private String nombreEncargado;
    private Image logo;

    public Negocio(String nombreEncargado, Image logo)
    {
        this.nombreEncargado = nombreEncargado;
        this.logo = logo;
    }

    public Negocio(String nombreEncargado)
    {
        this.nombreEncargado = nombreEncargado;

        logo = Toolkit.getDefaultToolkit().getImage(" ");

    }

    public String getNombreEncargado()
    {
        return nombreEncargado;
    }

    public void setNombreEncargado(String nombreEncargado)
    {
        this.nombreEncargado = nombreEncargado;
    }

    public Image getLogo()
    {
        return logo;
    }

    public void setLogo(Image logo)
    {
        this.logo = logo;
    }

}
