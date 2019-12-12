package com.sw.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;

/**
 *
 * @author Me
 */
public class ClienteDAO
{

    public static final String RUTA_CLAVECLIENTES = "res/com/sw/data/ClaveCliente.txt";

    private File file;

    public ClienteDAO()
    {

        file = new File(RUTA_CLAVECLIENTES);

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

    public int getClaveClientes()
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

    public void saveClaveClientes(int clave)
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
