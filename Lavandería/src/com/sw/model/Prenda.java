package com.sw.model;

import java.io.Serializable;

/**
 *
 * @author Me
 */
public class Prenda implements Serializable
{

    private static final long serialVersionUID = -6261626867347158543L;

    private String descripcion;
    private String tipo;
    private int cantidad;

    public Prenda(String descripcion, String tipo, int cantidad)
    {
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.cantidad = cantidad;

    }

    public Prenda(String tipo, int cantidad)
    {
        this(tipo, tipo, cantidad);
    }

    public Prenda(int cantidad)
    {
        this("", cantidad);
    }

    public Prenda()
    {
        this("", 0);
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public int getCantidad()
    {
        return cantidad;
    }

    public void setCantidad(int cantidad)
    {
        this.cantidad = cantidad;
    }

    public String getTipo()
    {
        return tipo;
    }

    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }

}
