package com.sw.persistence;

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
public class DAO
{

    public static final String RUTA_CLIENTESREGISTRADOS = "res/ClientesRegistrados.txt";
    public static final String RUTA_SERVICIOSINICIALES = "res/ServiciosIniciales.txt";
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

    public ArrayList<?> getObjects()
    {
        return objects != null ? objects : new ArrayList<>();
    }

}
