package com.sw.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Mohammed
 */
public class DAO
{

    public static final String RUTA_CLIENTESREGISTRADOS = "res/ClientesRegistrados.txt";
    public static final String RUTA_CLAVECLIENTES = "res/ClaveCliente.txt";

    public static final String RUTA_SERVICIOSENCOLA = "res/ServiciosEnCola.txt";
    public static final String RUTA_SERVICIOSENPROCESO = "res/ServiciosEnProceso.txt";
    public static final String RUTA_SERVICIOSTERMINADOS = "res/ServiciosTerminados.txt";

    private final File file;
    private ArrayList<?> objects;

    public DAO(String ruta)
    {

        file = new File(ruta);

        if (file.exists())
            loadObjects();

        else
            try
            {
                file.createNewFile();

            } catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }

    }

    private void loadObjects()
    {

        try
        {

            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file)))
            {
                objects = (ArrayList<?>) in.readObject();
            }

        } catch (IOException | ClassNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    public void saveObjects(ArrayList<?> objects)
    {

        try
        {

            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file)))
            {
                out.writeObject(objects);
            }

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    public int getClientesRegistrados()
    {

        if (!file.exists())
            try
            {
                file.createNewFile();

                return 0;

            } catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }

        else
            try (Scanner in = new Scanner(new FileReader(file)))
            {

                return in.nextInt();

            } catch (FileNotFoundException ex)
            {
                System.out.println(ex.getMessage());
            }

        return 0;

    }

    public ArrayList<?> getObjects()
    {
        return objects != null ? objects : new ArrayList<>();
    }

}
