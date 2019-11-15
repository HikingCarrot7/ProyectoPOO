package com.sw.model;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author Mohammed
 */
public abstract class Servicio implements Serializable
{

    private static final long serialVersionUID = 5793287335339414498L;

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

    public Persona getCliente()
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
