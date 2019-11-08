package com.sw.main;

import com.sw.controller.TableManager;

/**
 *
 * @author Mohammed
 */
public class Main
{

    public static void main(String[] args)
    {
        Object[][] items = rellenarMatriz();

        imprimirMatriz(items);

        System.out.println("");
        System.out.println("");

        imprimirMatriz(new TableManager().recortarFilas(items, 3, 4));

    }

    public static void imprimirMatriz(Object[][] matriz)
    {
        for (Object[] objects : matriz)
        {
            for (Object object : objects)
                System.out.printf("[%02d]", object);

            System.out.println("");

        }

    }

    public static Object[][] rellenarMatriz()
    {
        Object[][] items = new Object[5][6];
        int n = 0;

        for (Object[] fila : items)
            for (int j = 0; j < fila.length; j++)
                fila[j] = ++n;

        return items;

    }

}
