package com.sw.utilities;

import javax.swing.ImageIcon;

/**
 *
 * @author Mohammed
 */
public class Utilities
{

    public static ImageIcon getIcon(String ruta)
    {
        return new ImageIcon(Utilities.class.getClass().getResource(ruta));
    }

}
