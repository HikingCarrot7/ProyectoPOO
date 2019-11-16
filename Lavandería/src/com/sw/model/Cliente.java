package com.sw.model;

import com.sw.persistence.DAO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Mohammed
 */
public class Cliente extends Persona implements Serializable
{

    private static final long serialVersionUID = 275600884532878352L;

    private int nServicios;
    private static int clave;
    private int claveCliente;
    private ArrayList<Historial> historiales;

    static
    {

        File file = new File(DAO.RUTA_CLAVECLIENTES);

        if (!file.exists())
            try
            {
                file.createNewFile();

                clave = 0;

            } catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }

        else
            try (Scanner in = new Scanner(new FileReader(new File(DAO.RUTA_CLAVECLIENTES))))
            {

                clave = in.nextInt();

            } catch (FileNotFoundException ex)
            {
                System.out.println(ex.getMessage());
            }

    }

    public Cliente(String nombre, String correo, String telefono, String direccion, int nServicios, ArrayList<Historial> historiales)
    {

        super(nombre, correo, telefono, direccion);

        this.nServicios = nServicios;
        this.historiales = historiales;

        claveCliente = ++clave;

    }

    public Cliente(String nombre, String correo, String telefono, String direccion)
    {
        this(nombre, correo, telefono, direccion, 0, new ArrayList<>());
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

    public int getClaveCliente()
    {
        return claveCliente;
    }

    public void setClaveCliente(int claveCliente)
    {
        this.claveCliente = claveCliente;
    }

    public static int getClave()
    {
        return clave;
    }

}
