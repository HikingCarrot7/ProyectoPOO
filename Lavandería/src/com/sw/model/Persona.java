package com.sw.model;

import java.io.Serializable;

/**
 *
 * @author Mohammed
 */
public abstract class Persona implements Serializable
{

    private static final long serialVersionUID = -4223528775054366634L;

    protected String nombre;
    protected String correo;
    protected String telefono;
    protected String direccion;

    public Persona(String nombre, String correo, String telefono, String direccion)
    {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;

    }

    public Persona(String nombre)
    {
        this(nombre, "", "", "");
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getCorreo()
    {
        return correo;
    }

    public void setCorreo(String correo)
    {
        this.correo = correo;
    }

    public String getTelefono()
    {
        return telefono;
    }

    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }

    public String getDireccion()
    {
        return direccion;
    }

    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    @Override
    public String toString()
    {
        return null;
    }

}
