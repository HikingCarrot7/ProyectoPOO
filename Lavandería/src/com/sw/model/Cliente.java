package com.sw.model;

/**
 *
 * @author Mohammed
 */
public class Cliente
{

    private String nombre;
    private String direccion;
    private String telefono;

    public Cliente(String nombre, String direccion, String telefono)
    {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;

    }

    public Cliente(String nombre)
    {
        this(nombre, "", "");
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getDireccion()
    {
        return direccion;
    }

    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    public String getTelefono()
    {
        return telefono;
    }

    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }

    @Override
    public String toString()
    {
        return null;
    }

}
