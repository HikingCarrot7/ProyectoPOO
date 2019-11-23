package com.sw.controller;

import com.sw.others.TextFieldListener;
import com.sw.persistence.ConfigDAO;
import com.sw.view.ConfiguracionInterfaz;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Mohammed
 */
public class ConfigController implements ActionListener
{

    private ConfiguracionInterfaz configuracionInterfaz;
    private TextFieldListener textFieldListener;
    private VistaPrincipalController vistaPrincipalController;

    public ConfigController(ConfiguracionInterfaz configuracionInterfaz, VistaPrincipalController vistaPrincipalController)
    {

        this.configuracionInterfaz = configuracionInterfaz;
        this.vistaPrincipalController = vistaPrincipalController;

        textFieldListener = new TextFieldListener("^[0-9]+(.?[0-9]+)?$", configuracionInterfaz.getPrecioValido(), configuracionInterfaz.getPrecio());

        configuracionInterfaz.getPrecio().getDocument().addDocumentListener(textFieldListener);

        configuracionInterfaz.getPrecio().setText(String.valueOf(new ConfigDAO().getPrecioKg()));
        configuracionInterfaz.getOrder().setSelectedIndex(new ConfigDAO().getOrden());

        configuracionInterfaz.getColorChooser().addActionListener(this);
        configuracionInterfaz.getOk().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        switch (e.getActionCommand())
        {

            case "Color":

                new ConfigDAO().saveColorTablas(JColorChooser.showDialog(configuracionInterfaz, "Selecciona un color.", Color.yellow));

                vistaPrincipalController.revalidateAllTables();

                break;

            case "ok":

                if (textFieldListener.isValido() && Double.parseDouble(configuracionInterfaz.getPrecio().getText()) != 0)
                {

                    ConfigDAO configDAO = new ConfigDAO();

                    configDAO.savePrecioKg(Double.parseDouble(configuracionInterfaz.getPrecio().getText()));
                    configDAO.saveOrden(configuracionInterfaz.getOrder().getSelectedIndex());

                    vistaPrincipalController.updateCostoKg(Double.parseDouble(configuracionInterfaz.getPrecio().getText()));

                    configuracionInterfaz.dispose();

                } else
                    mostrarMensaje("Error.", "El precio por kg. que insertó no es válido y no se tomará en cuenta.", JOptionPane.ERROR_MESSAGE);

                break;

            default:
                break;

        }

    }

    private void mostrarMensaje(String titulo, String text, int tipo)
    {
        JOptionPane.showMessageDialog(configuracionInterfaz, text, titulo, tipo);
    }

}
