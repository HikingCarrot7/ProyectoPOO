package com.sw.persistence;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;

/**
 *
 * @author Mohammed
 */
public class ConfigDAO
{

    public static final String RUTA_PRECIOKG = "res/com/sw/data/PrecioKg.txt";
    public static final String RUTA_COLORTABLAS = "res/com/sw/data/ColorTablas.txt";
    public static final String RUTA_ORDEN = "res/com/sw/data/OrdenarPor.txt";

    public double getCostoKg()
    {

        File file = new File(RUTA_PRECIOKG);

        if (!file.exists())
            try
            {

                file.createNewFile();

                try (Formatter out = new Formatter(new FileWriter(file, false)))
                {
                    out.format("%s", "9.5");
                }

            } catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }

        else
            try (Scanner in = new Scanner(new FileReader(file)))
            {

                return in.nextDouble();

            } catch (FileNotFoundException ex)
            {
                System.out.println(ex.getMessage());
            }

        return 9.5;

    }

    public void savePrecioKg(double precio)
    {

        File file = new File(RUTA_PRECIOKG);

        try (Formatter out = new Formatter(new FileWriter(file, false)))
        {

            out.format("%s", precio);

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    public Color getColorTablas()
    {

        File file = new File(RUTA_COLORTABLAS);

        if (!file.exists())
            try
            {

                file.createNewFile();

                try (Formatter out = new Formatter(new FileWriter(file, false)))
                {
                    out.format("%s;%s;%s", "180", "180", "180");
                }

            } catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }

        else
            try (Scanner in = new Scanner(new FileReader(file)))
            {

                String[] RGB = in.nextLine().split(";");

                return new Color(Integer.parseInt(RGB[0]), Integer.parseInt(RGB[1]), Integer.parseInt(RGB[2]));

            } catch (FileNotFoundException ex)
            {
                System.out.println(ex.getMessage());
            }

        return new Color(180, 180, 180);

    }

    public void saveColorTablas(Color color)
    {

        if (color == null)
            return;

        File file = new File(RUTA_COLORTABLAS);

        try (Formatter out = new Formatter(new FileWriter(file, false)))
        {

            out.format("%s;%s;%s", color.getRed(), color.getGreen(), color.getBlue());

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    public int getOrden()
    {

        File file = new File(RUTA_ORDEN);

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

    public void saveOrden(int orden)
    {

        File file = new File(RUTA_ORDEN);

        try (Formatter out = new Formatter(new FileWriter(file, false)))
        {

            out.format("%s", orden);

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

}
