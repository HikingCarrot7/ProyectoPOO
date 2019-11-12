package com.sw.persistence;

import com.sw.model.Cliente;
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
public class ClientesDAO
{

    private final File file;
    private ArrayList<Cliente> clientes;

    public ClientesDAO(String ruta)
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
                clientes = (ArrayList<Cliente>) in.readObject();
            }

        } catch (IOException | ClassNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    public void guardarClientes(ArrayList<Cliente> clientes)
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

    public ArrayList<Cliente> getClientes()
    {
        return clientes != null ? clientes : new ArrayList<>();
    }

}
