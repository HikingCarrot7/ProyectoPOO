package com.sw.persistence;

import com.sw.model.ClienteRegistrado;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Mohammed
 */
public class ClientesRegistradosDAO
{

    private final File file;
    private ArrayList<ClienteRegistrado> clientes;

    public ClientesRegistradosDAO(String ruta)
    {

        file = new File(ruta);

        if (file.exists())
            loadClientes();

        else
            try
            {
                file.createNewFile();

            } catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }

    }

    private void loadClientes()
    {

        try
        {

            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file)))
            {
                clientes = (ArrayList<ClienteRegistrado>) in.readObject();
            }

        } catch (IOException | ClassNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    public void guardarClientes(ArrayList<ClienteRegistrado> clientes)
    {

        try
        {

            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file)))
            {
                out.writeObject(clientes);
            }

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    public ArrayList<ClienteRegistrado> getClientes()
    {
        return clientes != null ? clientes : new ArrayList<>();
    }

}
