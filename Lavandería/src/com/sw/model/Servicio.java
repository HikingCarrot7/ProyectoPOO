package com.sw.model;

import java.util.Calendar;

/**
 *
 * @author Mohammed
 */
public abstract class Servicio
{

    private Cliente cliente;
    private Calendar fecha;

    public Servicio(Cliente cliente, Calendar fecha)
    {
        this.cliente = cliente;
        this.fecha = fecha;
    }

    public Servicio(Cliente cliente)
    {
        this(cliente, Calendar.getInstance());
    }

    public Cliente getCliente()
    {
        return cliente;
    }

    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }

    public Calendar getFecha()
    {
        return fecha;
    }

    public void setFecha(Calendar fecha)
    {
        this.fecha = fecha;
    }

}
