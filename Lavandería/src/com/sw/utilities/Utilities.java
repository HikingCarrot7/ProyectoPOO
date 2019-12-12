package com.sw.utilities;

import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Me
 */
public class Utilities
{

    public static ImageIcon getIcon(String ruta)
    {
        return new ImageIcon(Utilities.class.getClass().getResource(ruta));
    }

    public static int[] asArray(ArrayList<Integer> lista)
    {
        int[] items = new int[lista.size()];

        for (int i = 0; i < lista.size(); i++)
            items[i] = lista.get(i);

        return items;

    }

}
