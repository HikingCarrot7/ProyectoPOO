package com.sw.controller;

import com.sw.others.MyWindowListener;
import com.sw.others.TextFieldListener;
import com.sw.persistence.ConfigDAO;
import com.sw.view.ConfiguracionInterfaz;
import com.sw.view.TiposPrendasInterfaz;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

/**
 * Controlador de la configuración.
 *
 * @author Me
 * @since 1.0
 *
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

        initMyComponents();

    }

    /**
     * Iniciamos los componentes para esta interfaz.
     */
    private void initMyComponents()
    {

        textFieldListener = new TextFieldListener("^[0-9]+(.?[0-9]+)?$", configuracionInterfaz.getPrecioValido(), configuracionInterfaz.getPrecio());

        configuracionInterfaz.getPrecio().getDocument().addDocumentListener(textFieldListener);

        configuracionInterfaz.getPrecio().setText(String.valueOf(new ConfigDAO().getCostoKg()));
        configuracionInterfaz.getOrder().setSelectedIndex(new ConfigDAO().getOrden());

        configuracionInterfaz.getColorChooser().addActionListener(this);
        configuracionInterfaz.getTiposPrendas().addActionListener(this);
        configuracionInterfaz.getOk().addActionListener(this);

    }

    /**
     * Gestiona los eventos que ocurren cuando se presiona un botón.
     *
     * @param e El objeto de tipo ActionEvent que se crea cuando se presiona un botón en esta interfaz.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {

        switch (e.getActionCommand())
        {

            case "Color": // Se muestra la ventana para seleccionar el color.

                new ConfigDAO().saveColorTablas(JColorChooser.showDialog(configuracionInterfaz, "Selecciona un color.", Color.yellow));

                vistaPrincipalController.revalidateAllTables();

                break;

            case "tipoPrendas": // Se muestra la interfaz para los tipos de prendas.

                EventQueue.invokeLater(() ->
                {

                    TiposPrendasInterfaz tiposPrendasInterfaz = new TiposPrendasInterfaz();

                    tiposPrendasInterfaz.setVisible(true);
                    tiposPrendasInterfaz.setLocationRelativeTo(configuracionInterfaz);

                    tiposPrendasInterfaz.addWindowListener(new MyWindowListener(configuracionInterfaz));
                    configuracionInterfaz.setVisible(false);

                    new TiposPrendasController(tiposPrendasInterfaz);

                });

                break;

            case "ok": // Guardamos todos los datos. 

                if (textFieldListener.isValido() && Double.parseDouble(configuracionInterfaz.getPrecio().getText()) != 0)
                {

                    ConfigDAO configDAO = new ConfigDAO();

                    configDAO.savePrecioKg(Double.parseDouble(configuracionInterfaz.getPrecio().getText()));
                    configDAO.saveOrden(configuracionInterfaz.getOrder().getSelectedIndex());

                    vistaPrincipalController.updateCostoKg(Double.parseDouble(configuracionInterfaz.getPrecio().getText()));

                    vistaPrincipalController.updateAllTables();
                    configuracionInterfaz.dispose();
                    vistaPrincipalController.getVistaPrincipal().setVisible(true);

                } else
                    mostrarMensaje("Error.", "El precio por kg. que insertó no es válido.", JOptionPane.ERROR_MESSAGE);

                break;

            default:
                break;

        }

    }

    /**
     * Mostramos un mensaje.
     *
     * @param titulo El título.
     * @param text El texto a mostrar.
     * @param tipo El tipo de mensaje.
     *
     */
    private void mostrarMensaje(String titulo, String text, int tipo)
    {
        JOptionPane.showMessageDialog(configuracionInterfaz, text, titulo, tipo);
    }

}
