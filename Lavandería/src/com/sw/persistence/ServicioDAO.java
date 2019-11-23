package com.sw.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

/**
 *
 * @author Mohammed
 */
public class ServicioDAO
{

    public static final String RUTA_CLIENTESREGISTRADOS = "res/com/sw/data/ClientesRegistrados.txt";
    public static final String RUTA_HISTORIALES = "res/com/sw/data/Historiales.txt";

    public static final String RUTA_SERVICIOSENCOLA = "res/com/sw/data/ServiciosEnCola.txt";
    public static final String RUTA_SERVICIOSENPROCESO = "res/com/sw/data/ServiciosEnProceso.txt";
    public static final String RUTA_SERVICIOSTERMINADOS = "res/com/sw/data/ServiciosTerminados.txt";

    private File file;

    public ServicioDAO(String ruta)
    {

        file = new File(ruta);

        if (!file.exists())
            try
            {

                file.createNewFile();

                try (Formatter out = new Formatter(new FileWriter(file, false)))
                {
                    out.format("%s", "0");
                }

            } catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }

    }

    public ArrayList<?> getObjects()
    {

        try
        {

            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file)))
            {
                return (ArrayList<?>) in.readObject();
            }

        } catch (IOException | ClassNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }

        return new ArrayList<>();

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

    public int getClaves()
    {

        try (Scanner in = new Scanner(new FileReader(file)))
        {

            return in.nextInt();

        } catch (FileNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }

        return 0;

    }

    public void saveClaves(int clave)
    {

        try (Formatter out = new Formatter(new FileWriter(file, false)))
        {

            out.format("%s", clave);

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

}
