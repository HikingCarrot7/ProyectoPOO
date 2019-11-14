package com.sw.model;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Mohammed
 */
public class ClienteRegistrado extends Cliente implements Serializable
{

    private static final long serialVersionUID = 275600884532878352L;

    private int nServicios;
    private Historial historial;

    public ClienteRegistrado(String nombre, String correo, String telefono, String direccion, int nServicios, Historial historial)
    {
        super(nombre, correo, telefono, direccion);

        this.nServicios = nServicios;
        this.historial = historial;

    }

    public ClienteRegistrado(String nombre, String correo, String telefono, String direccion)
    {
        this(nombre, correo, telefono, direccion, new Random().nextInt(50), new Historial());
    }

    public ClienteRegistrado(Cliente cliente)
    {
        this(cliente.getNombre(), cliente.getCorreo(), cliente.getTelefono(), cliente.getDireccion());
    }

    public int getnServicios()
    {
        return nServicios;
    }

    public void setnServicios(int nServicios)
    {
        this.nServicios = nServicios;
    }

    public Historial getHistorial()
    {
        return historial;
    }

    public void setHistorial(Historial historial)
    {
        this.historial = historial;
    }

}
