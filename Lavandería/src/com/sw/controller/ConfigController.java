package com.sw.controller;

import com.sw.persistence.DAO;
import com.sw.view.ConfiguracionInterfaz;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JColorChooser;

/**
 *
 * @author Mohammed
 */
public class ConfigController implements ActionListener
{

    private ConfiguracionInterfaz configuracionInterfaz;
    private VistaPrincipalController vistaPrincipalController;

    public ConfigController(ConfiguracionInterfaz configuracionInterfaz, VistaPrincipalController vistaPrincipalController)
    {

        this.configuracionInterfaz = configuracionInterfaz;
        this.vistaPrincipalController = vistaPrincipalController;

        configuracionInterfaz.getColorChooser().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        switch (e.getActionCommand())
        {

            case "Color":

                ArrayList<Color> colorTabla = new ArrayList<>();

                colorTabla.add(JColorChooser.showDialog(configuracionInterfaz, "Selecciona un color.", Color.yellow));

                new DAO(DAO.RUTA_COLORTABLAS).saveObjects(colorTabla);

                vistaPrincipalController.revalidateAllTables();

                break;

            default:
                break;

        }

    }

}
