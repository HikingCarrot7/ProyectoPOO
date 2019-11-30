package com.sw.model;

import java.io.Serializable;

/**
 *
 * @author Mohammed
 */
public class Cliente extends Persona implements Serializable
{

    private static final long serialVersionUID = 275600884532878352L;

    private static int clave;
    private int nServicios;
    private int claveCliente;

    public Cliente(String nombre, String correo, String telefono, String direccion, int nServicios)
    {

        super(nombre, correo, telefono, direccion);

        this.nServicios = nServicios;

        claveCliente = ++clave;

    }

    public Cliente(String nombre, String correo, String telefono, String direccion)
    {
        this(nombre, correo, telefono, direccion, 0);
    }

    public Cliente(Persona cliente)
    {
        this(cliente.getNombre(), cliente.getCorreo(), cliente.getTelefono(), cliente.getDireccion());
    }

    public void aumentarNServicios()
    {
        nServicios++;
    }

    public int getnServicios()
    {
        return nServicios;
    }

    public void setnServicios(int nServicios)
    {
        this.nServicios = nServicios;
    }

    public int getClaveCliente()
    {
        return claveCliente;
    }

    public void setClaveCliente(int claveCliente)
    {
        this.claveCliente = claveCliente;
    }

    public static int getClaves()
    {
        return clave;
    }

    public static void setClave(int clave)
    {
        Cliente.clave = clave;
    }

}
