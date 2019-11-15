package com.sw.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Mohammed
 */
public class Cliente extends Persona implements Serializable
{

    private static final long serialVersionUID = 275600884532878352L;

    private int nServicios;
    private ArrayList<Historial> historiales;

    public Cliente(String nombre, String correo, String telefono, String direccion, int nServicios, ArrayList<Historial> historiales)
    {
        super(nombre, correo, telefono, direccion);

        this.nServicios = nServicios;
        this.historiales = historiales;

    }

    public Cliente(String nombre, String correo, String telefono, String direccion)
    {
        this(nombre, correo, telefono, direccion, new Random().nextInt(50), new ArrayList<>());
    }

    public Cliente(Persona cliente)
    {
        this(cliente.getNombre(), cliente.getCorreo(), cliente.getTelefono(), cliente.getDireccion());
    }

    public void addHistorial(Historial historial)
    {
        historiales.add(historial);
    }

    public void removeHistorial(Historial historial)
    {
        historiales.remove(historial);
    }

    public int getnServicios()
    {
        return nServicios;
    }

    public void setnServicios(int nServicios)
    {
        this.nServicios = nServicios;
    }

    public ArrayList<Historial> getHistoriales()
    {
        return historiales;
    }

    public void setHistoriales(ArrayList<Historial> historiales)
    {
        this.historiales = historiales;
    }

}
